package com.sjs.jsvill.controller.sean;

import com.sjs.jsvill.dto.sean.ContractDTO;
import com.sjs.jsvill.dto.sean.UnitDTO;
import com.sjs.jsvill.service.sean.contract.ContractService;
import com.sjs.jsvill.service.sean.unit.UnitService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/contract")
@Log4j2
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    private final UnitService unitService;

    @GetMapping("/register")
    public void register(Long unitRowid, Model model) {
        UnitDTO unitDTO = unitService.getWithContractList(unitRowid);
        model.addAttribute("result", unitDTO);
    }

    @PostMapping("/register")
    public String register(ContractDTO contractDTO, RedirectAttributes redirectAttributes) {
        Json.stringToJson(contractDTO);
        contractService.register(contractDTO);
        return "redirect:/unit/read?unitRowid=" + contractDTO.getUnitRowid();
    }

    @GetMapping("/edit")
    public void edit(Long contractRowid, Model model) {
        ContractDTO contractDTO = contractService.get(contractRowid);
        UnitDTO unitDTO = unitService.getWithContractList(contractDTO.getUnitRowid());
        model.addAttribute("contractDTO", contractDTO);
        model.addAttribute("unitDTO", unitDTO);
        Json.stringToJson(contractDTO);
    }

    @PostMapping("/phoneValiCheck")
    @ResponseBody
    public String phoneValiCheck(@RequestParam(value = "phoneList[]") List<String> phoneList) {
        return contractService.phoneCheck((phoneList));
    }

    @PostMapping("/remove")
    public String remove(long contractRowid, RedirectAttributes redirectAttributes){
        contractService.remove(contractRowid);
        redirectAttributes.addFlashAttribute("msg", contractRowid);
        return "redirect:/unit/read?unitRowid=1";
    }
}