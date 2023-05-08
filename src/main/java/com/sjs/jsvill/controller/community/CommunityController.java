package com.sjs.jsvill.controller.community;

import com.sjs.jsvill.entity.Community;
import com.sjs.jsvill.service.community.CommunityService;
import com.sjs.jsvill.service.community.CommunityServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Log4j2
@Controller
@RequestMapping("/community")
@RequiredArgsConstructor /* Todo kjs @RequiredArgsConstructor 의 역할조사 */
public class CommunityController {
    /* Todo kjs @Autowired 로 Service 선언하는 방법과 private final 로 Service 선언하는 방식의 차이 */
    private final CommunityService communityService;

    /*@Autowired
    CommunityServiceImpl communityServiceImpl;*/
    @GetMapping("/community")
    public String community(Model model, @PageableDefault(sort = "comRowid", direction = Sort.Direction.DESC) Pageable pageable,
                            HttpServletRequest req, @Param("searchKey") String searchKey, @Param("searchTxt") String searchTxt) {
        /* todo kjs sort에 언더바가 들어가면 안되는 것 같음 @PageableDefault 어노케이션에 대해서 더 자세히 알아봐야 함*/
        log.info("controller");
//        String searchKey = (String) req.getAttribute("searchKey");
//        String searchTxt = (String) req.getAttribute("searchTxt");

        if(searchKey == null) {
            searchKey = "";
        }
        if(searchTxt == null) {
            searchTxt = "";
        }
        log.info("searchKey >>>>>>>>>>>>>>> " + searchKey);
        log.info("searchTxt >>>>>>>>>>>>>>> " + searchTxt);

        Page<Community> list = communityService.findAll(pageable, searchKey, searchTxt);

        model.addAttribute("list", list);
        model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());
        model.addAttribute("next", pageable.next().getPageNumber());
        model.addAttribute("hasNext", list.hasNext());
        model.addAttribute("hasPrev", list.hasPrevious());
        model.addAttribute("pages", list.getTotalPages());

        return "/community/community.html";
    }

}
