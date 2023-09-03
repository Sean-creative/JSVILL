package com.sjs.jsvill.dto.view;

import com.sjs.jsvill.dto.CarDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterPhotoReqDTO {
    private Long unitRowid;
    private List<MultipartFile> files;
}