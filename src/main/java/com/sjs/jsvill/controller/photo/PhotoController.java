package com.sjs.jsvill.controller.photo;

import com.sjs.jsvill.dto.view.RegisterPhotoResDTO;
import com.sjs.jsvill.service.contract.ContractService;
import com.sjs.jsvill.service.photo.PhotoService;
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
    private final PhotoService photoService;

    @GetMapping("/register")
    public String register(Long contractRowid, Model model) {
        log.info("GetMapping-register-Get");
        model.addAttribute("data",
                new RegisterPhotoResDTO(contractService.get(contractRowid), photoService.contractPhotogetList(contractRowid)));
        return "photo/register";
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(List<MultipartFile> uploadFiles, @RequestParam("bookMarks") List<Boolean> bookMarks, Long contractRowid, RedirectAttributes redirectAttributes) {
        log.info("GetMapping-register-Post");
        photoService.contractPhotoRegister(uploadFiles, bookMarks, contractRowid);
        redirectAttributes.addAttribute("contractRowid", contractRowid);
        return ResponseEntity.ok("파일 업로드 및 데이터 처리 완료");
    }

    @PostMapping("/delete")
    public ResponseEntity<Void> deletePhoto(String fileKey) {
        photoService.deletePhoto(fileKey);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/bookmark")
    public ResponseEntity<Void> bookMarkPhoto(Long photoRowid, Boolean bookmark) {
        photoService.bookmark(photoRowid, bookmark);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}