package com.sjs.jsvill.controller.photo;

import com.sjs.jsvill.dto.view.RegisterPhotoResDTO;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.util.AwsS3Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/photo")
@Log4j2
@RequiredArgsConstructor
public class PhotoController {
    private final ContractService contractService;
    private final AwsS3Service awsS3Service;

    @GetMapping("/register")
    public String register(Long contractRowid, Model model) {
        log.info("GetMapping-register-Get");
        model.addAttribute("data",
                new RegisterPhotoResDTO(contractService.get(contractRowid), awsS3Service.contractPhotogetList(contractRowid)));
        return "photo/register";
    }

    @PostMapping("/register")
    public String register(List<MultipartFile> files, Long contractRowid, RedirectAttributes redirectAttributes) {
        log.info("GetMapping-register-Post");
        awsS3Service.contractPhotoRegister(files, contractRowid);

        redirectAttributes.addAttribute("contractRowid", contractRowid);
        return "redirect:/photo/register";
    }

    @DeleteMapping("/file")
    public ResponseEntity<Void> deleteFile(@RequestParam String fileName) {
        awsS3Service.deleteFile(fileName);
        return new ResponseEntity<>(HttpStatus.OK);
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
