package com.sjs.jsvill.controller.memo;

import com.sjs.jsvill.dto.MemoDTO;
import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.dto.view.RegisterMemoResDTO;
import com.sjs.jsvill.entity.Memo;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.service.memo.MemoService;
import com.sjs.jsvill.service.photo.PhotoService;
import com.sjs.jsvill.service.unit.UnitService;
import com.sjs.jsvill.service.util.SmsService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/memo")
@Log4j2
@RequiredArgsConstructor
public class MemoController {

    private final UnitService unitService;
    private final MemoService memoService;

    @GetMapping("/register")
    public String register(Long unitRowid, Model model) {
        model.addAttribute("data",
                new RegisterMemoResDTO(unitService.get(unitRowid), memoService.getList(unitRowid)));
        return "memo/register";
    }

    @PostMapping("/register")
    public String register(MemoDTO memoDTO, RedirectAttributes redirectAttributes) {
        memoService.register(memoDTO);
        redirectAttributes.addAttribute("unitRowid", memoDTO.getUnitRowid());
        return "redirect:/memo/register";
    }

//    @GetMapping("/edit")
//    public String edit(long unitRowid, Model model) {
//        model.addAttribute("result" , unitService.get(unitRowid));
//        return "unit/edit";
//    }
//
//    @PostMapping("/edit")
//    public String edit(UnitDTO unitDTO, RedirectAttributes redirectAttributes) {
//        unitService.modify(unitDTO);
//        return "redirect:/unit/read?unitRowid="+unitDTO.getUnitRowid();
//    }
//
//    @GetMapping("/communityWrite")
//    public String communityWrite() { return "unit/communityWrite";}
//
//    @PostMapping("/remove")
//    public String remove(long unitRowid, RedirectAttributes redirectAttributes){
//        unitService.remove(unitRowid);
//        redirectAttributes.addFlashAttribute("msg", unitRowid);
//        return "redirect:/group/list";
//    }
}