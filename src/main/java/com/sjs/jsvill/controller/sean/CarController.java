package com.sjs.jsvill.controller.sean;

import com.sjs.jsvill.service.sean.car.CarService;
import com.sjs.jsvill.service.sean.contract.ContractService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/car")
@Log4j2
@RequiredArgsConstructor
public class CarController {
    private final ContractService contractService;
    private final CarService carService;

    @GetMapping("/register")
    public void register(Long unitRowid, Model model) {
//        UnitDTO unitDTO = unitService.getWithContractList(unitRowid);
//        model.addAttribute("result", unitDTO);
    }

//    @PostMapping("/register")
//    public String register(ContractDTO contractDTO, RedirectAttributes redirectAttributes) {
//        Json.stringToJson(contractDTO);
//        contractService.register(contractDTO);
//        return "redirect:/unit/read?unitRowid=" + contractDTO.getUnitRowid();
//    }
//
//    @GetMapping("/edit")
//    public void edit(Long contractRowid, Model model) {
//        ContractDTO contractDTO = contractService.get(contractRowid);
//        UnitDTO unitDTO = unitService.getWithContractList(contractDTO.getUnitRowid());
//        model.addAttribute("contractDTO", contractDTO);
//        model.addAttribute("unitDTO", unitDTO);
//        Json.stringToJson(contractDTO);
//    }
//
//    @PostMapping("/remove")
//    public String remove(long contractRowid, RedirectAttributes redirectAttributes){
//        String unitRowid = contractService.get(contractRowid).getUnitRowid().toString();
//        contractService.remove(contractRowid);
//        redirectAttributes.addFlashAttribute("msg", contractRowid);
//        return "redirect:/unit/read?unitRowid="+unitRowid;
//    }
}
