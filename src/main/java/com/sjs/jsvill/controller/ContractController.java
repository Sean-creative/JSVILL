package com.sjs.jsvill.controller;

import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.service.unit.UnitService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/contract")
@Log4j2
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    private final UnitService unitService;

    @GetMapping("/register")
    public void register(Long unitRowid, Model model) {
        log.info("unitRowid: " + unitRowid);
        UnitDTO unitDTO = unitService.getWithContractList(unitRowid);
        log.info(unitDTO);
        model.addAttribute("result", unitDTO);
    }
    @PostMapping("register")
    public String register(ContractDTO contractDTO, RedirectAttributes redirectAttributes) {
        log.info("ContractDTO..." + contractDTO);
//        contractService.register(contractDTO);
        return "redirect:/unit/read?unitRowid=1";
    }

    @GetMapping("/edit")
    public void edit() {log.info("contractEdit");}
}
