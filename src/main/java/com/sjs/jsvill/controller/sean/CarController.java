package com.sjs.jsvill.controller.sean;

import com.sjs.jsvill.dto.sean.view.RegisterCarDTO;
import com.sjs.jsvill.service.sean.car.CarService;
import com.sjs.jsvill.service.sean.contract.ContractService;
import com.sjs.jsvill.service.sean.tenant.TenantService;
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
    private final TenantService tenantService;

    @GetMapping("/register")
    public void register(Long contractRowid, Model model) {
        //view단에 해당 viewDTO를 보낼건데, 이 떄의 생성자에는 DTO를 넣을지 or Entity를 넣을지 정하지는 못함 -> 일단은 귀찮으니 Entity를 넣어둠
        model.addAttribute("data", new RegisterCarDTO(contractService.get(contractRowid), tenantService.getTenantList(contractRowid)));
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
