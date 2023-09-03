package com.sjs.jsvill.controller.photo;

import com.sjs.jsvill.controller.util.UserDuplicateCheck;
import com.sjs.jsvill.dto.ContractDTO;
import com.sjs.jsvill.dto.UnitDTO;
import com.sjs.jsvill.dto.view.RegisterCarReqDTO;
import com.sjs.jsvill.dto.view.RegisterCarResDTO;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.service.photo.PhotoService;
import com.sjs.jsvill.service.unit.UnitService;
import com.sjs.jsvill.util.Json;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/photo")
@Log4j2
@RequiredArgsConstructor
public class PhotoController {
    private final ContractService contractService;
    private final PhotoService photoService;

    @GetMapping("/register")
    public String register(Long contractRowid, Model model) {
        //view단에 해당 viewDTO를 보낼건데, 이 떄의 생성자에는 DTO를 넣을지 or Entity를 넣을지 정하지는 못함 -> 일단은 귀찮으니 Entity를 넣어둠
        model.addAttribute("data", new RegisterCarResDTO(contractService.get(contractRowid), tenantService.getTenantList(contractRowid)));
        return "photo/register";
    }

    @PostMapping("/register")
    public String register(List<MultipartFile> files, Long contractRowid) {
        photoService.register(files, contractRowid);
        return "redirect:/unit/read?unitRowid=" + registerCarReqDTO.getUnitRowid();
    }

//    @GetMapping("/edit")
//    public String edit(Long contractRowid, Model model) {
//        ContractDTO contractDTO = contractService.getDTO(contractRowid);
//        UnitDTO unitDTO = unitService.getWithContractList(contractDTO.getUnitRowid());
//        model.addAttribute("contractDTO", contractDTO);
//        model.addAttribute("unitDTO", unitDTO);
//        Json.stringToJson(contractDTO, "ContractController-edit/get");
//        return "/contract/edit";
//    }
//    @PostMapping("/edit")
//    public String edit(ContractDTO contractDTO, RedirectAttributes redirectAttributes){
//        Json.stringToJson(contractDTO, "ContractController-edit/post");
//        contractService.modify(contractDTO);
//        return "redirect:/unit/read?unitRowid=" + contractDTO.getUnitRowid();
//    }
//    @PostMapping("/remove")
//    public String remove(long contractRowid, RedirectAttributes redirectAttributes){
//        String unitRowid = contractService.getDTO(contractRowid).getUnitRowid().toString();
//        contractService.remove(contractRowid);
//        redirectAttributes.addFlashAttribute("msg", contractRowid);
//        return "redirect:/unit/read?unitRowid="+unitRowid;
//    }
}
