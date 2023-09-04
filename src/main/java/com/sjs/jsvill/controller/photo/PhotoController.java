package com.sjs.jsvill.controller.photo;

import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.dto.view.RegisterPhotoResDTO;
import com.sjs.jsvill.entity.Photo;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.service.photo.PhotoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

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
        model.addAttribute("data",
                new RegisterPhotoResDTO(contractService.get(contractRowid), photoService.getList(contractRowid)));
        return "photo/register";
    }

    @PostMapping("/register")
    public String register(List<MultipartFile> files, Long contractRowid, Model model) {
        List<Photo> photoList = photoService.register(files, contractRowid);

        model.addAttribute("data",
                new RegisterPhotoResDTO(contractService.get(contractRowid), PhotoDTO.entityToDTOList(photoList)));
//        return "redirect:/unit/read?unitRowid=" + contractService.get(contractRowid).getUnit().getUnit_rowid();
        return "photo/register";
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
