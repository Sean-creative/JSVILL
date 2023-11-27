package com.sjs.jsvill.controller.contract;

import com.sjs.jsvill.controller.util.UserDuplicateCheck;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.service.unit.UnitService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/contract")
@Log4j2
@RequiredArgsConstructor
public class ContractController {
    private final ContractService contractService;
    private final UnitService unitService;

    @GetMapping("/register")
    public String register(Long unitRowid, Model model) {
        UnitDTO unitDTO = unitService.getWithContractList(unitRowid);
        model.addAttribute("result", unitDTO);
        return "contract/register";
    }

    @PostMapping("/register")
    public String register(ContractDTO contractDTO, RedirectAttributes redirectAttributes) {
        contractService.register(contractDTO);
        redirectAttributes.addAttribute("unitRowid", contractDTO.getUnitRowid());
        return "redirect:/unit/read";
    }

    @GetMapping("/edit")
    public String edit(Long contractRowid, Model model) {
        ContractDTO contractDTO = contractService.getDTO(contractRowid);
        UnitDTO unitDTO = unitService.getWithContractList(contractDTO.getUnitRowid());
        model.addAttribute("contractDTO", contractDTO);
        model.addAttribute("unitDTO", unitDTO);
        Json.stringToJson(contractDTO, "ContractController-edit/get");
        return "contract/edit";
    }
    @PostMapping("/edit")
    public String edit(ContractDTO contractDTO, RedirectAttributes redirectAttributes){
        Json.stringToJson(contractDTO, "ContractController-edit/post");
        contractService.modify(contractDTO);
        redirectAttributes.addAttribute("unitRowid", contractDTO.getUnitRowid());
        return "redirect:/unit/read";
    }
    @PostMapping("/remove")
    public String remove(long contractRowid, RedirectAttributes redirectAttributes){
        String unitRowid = contractService.getDTO(contractRowid).getUnitRowid().toString();
        contractService.remove(contractRowid);
        redirectAttributes.addFlashAttribute("msg", contractRowid);
        return "redirect:/unit/read?unitRowid="+unitRowid;
    }
    @PostMapping("/phoneValiCheck")
    @ResponseBody
    public String phoneValiCheck(@RequestParam(value = "titleList[]") List<String> titleList, @RequestParam(value = "phoneList[]") List<String> phoneList) {
        List<UserDuplicateCheck> duplicateCheckList = new ArrayList<>();
        for(int i=0; i<titleList.size(); i++) duplicateCheckList.add(new UserDuplicateCheck(titleList.get(i), phoneList.get(i)));
        return contractService.phoneCheck(duplicateCheckList);
    }
    @PostMapping("/expire")  //TODO 어떤껄 POST하고 어떤걸 GET으로 해야할까? - service쪽에서 조금이라도 save, modify가 있으면 POST로??
    public String expire(long contractRowid) {
        String unitRowid = contractService.getDTO(contractRowid).getUnitRowid().toString();
        contractService.expire(contractRowid);
        return "redirect:/unit/read?unitRowid="+unitRowid;
    }
}
