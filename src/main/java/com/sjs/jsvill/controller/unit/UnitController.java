package com.sjs.jsvill.controller.unit;

import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.service.unit.UnitService;
import com.sjs.jsvill.service.sms.NaverSmsService;
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
@RequestMapping("/unit")
@Log4j2
@RequiredArgsConstructor
public class UnitController {

    private final UnitService unitService;
    private final ContractService contractService;
    private final NaverSmsService naverSmsService;

    @GetMapping("/read")
    public String String(Long unitRowid, Model model) {
        UnitDTO unitDTO = unitService.getWithContractList(unitRowid);
        Json.stringToJson(unitDTO, "UnitController-read/GET-unitDTO");
        model.addAttribute("unitDTO", unitDTO);
        return "unit/read";
    }

    @GetMapping("/register")
    public String register(int groupRowid, Model model) {
        model.addAttribute("groupRowid", groupRowid);
        return "unit/register";
    }

    @PostMapping("/register")
    public String register(UnitDTO dto) {
        //TODO 로그인이 유지가 되면 컨트롤러에서 넣어줘도 상관없음

//        // Member id로 조회하는 메소드 존재한다고 가정하에 진행
//        Member member = memberService.searchMemberById(
//                Long.parseLong(boardFileVO.getId()));

        Long gno = unitService.register(dto);
        return "redirect:/group/list";
    }

    @GetMapping("/edit")
    public String edit(long unitRowid, Model model) {
        model.addAttribute("result" , unitService.get(unitRowid));
        return "unit/edit";
    }

    @PostMapping("/edit")
    public String edit(UnitDTO unitDTO, RedirectAttributes redirectAttributes) {
        unitService.modify(unitDTO);
        return "redirect:/unit/read?unitRowid="+unitDTO.getUnitRowid();
    }

    @GetMapping("/communityWrite")
    public String communityWrite() { return "unit/communityWrite";}

    @PostMapping("/remove")
    public String remove(long unitRowid, RedirectAttributes redirectAttributes){
        unitService.remove(unitRowid);
        redirectAttributes.addFlashAttribute("msg", unitRowid);
        return "redirect:/group/list";
    }

    @GetMapping("/previousContractHistory")
    public String previousContractHistory(long unitRowid, boolean isAsc, Model model) {
        System.out.println("unitRowid :" + unitRowid);
        System.out.println("isAsc :" + isAsc);
        model.addAttribute("data" , contractService.getPreviousContractHistoryList(unitRowid, isAsc));
        return "unit/previousContractHistory";
    }
}