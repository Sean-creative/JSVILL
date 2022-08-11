package com.sjs.jsvill.controller.sean;

import com.sjs.jsvill.dto.sean.UnitDTO;
import com.sjs.jsvill.service.sean.contract.ContractService;
import com.sjs.jsvill.service.sean.unit.UnitService;
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

    @GetMapping("/register")
    public void register(int groupRowid, Model model) {
        model.addAttribute("groupRowid", groupRowid);
    }

    @PostMapping("/register")
    public String register(UnitDTO dto, RedirectAttributes redirectAttributes) {
        //TODO 로그인이 유지가 되면 컨트롤러에서 넣어줘도 상관없음
        Long gno = unitService.register(dto);
        return "redirect:/group/list";
    }

    @GetMapping("/edit")
    public void edit(long unitRowid, Model model) {
        model.addAttribute("result" , unitService.get(unitRowid));
    }

    @PostMapping("/edit")
    public String edit(UnitDTO unitDTO, RedirectAttributes redirectAttributes) {
        unitService.modify(unitDTO);
        return "redirect:/unit/read?unitRowid="+unitDTO.getUnitRowid();
    }

    @GetMapping("/communityWrite")
    public void communityWrite() {log.info("communityWrite");}

    @PostMapping("/remove")
    public String remove(long unitRowid, RedirectAttributes redirectAttributes){
        unitService.remove(unitRowid);
        redirectAttributes.addFlashAttribute("msg", unitRowid);
        return "redirect:/group/list";
    }

    @GetMapping("/read")
    public void read(Long unitRowid, Model model){
        UnitDTO unitDTO = unitService.getWithContractList(unitRowid);
        Json.stringToJson(unitDTO, "UnitController-read/GET");
        model.addAttribute("unitDTO", unitDTO);
    }

    @GetMapping("/previousContractHistory")
    public void previousContractHistory(long unitRowid, Model model) {
        System.out.println("unitRowid :" + unitRowid);
        model.addAttribute("data" , contractService.getPreviousContractHistoryList(unitRowid));
    }
}
