package com.sjs.jsvill.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Builder
@Data
public class PhotoDTO {
    private String origFileName;
    private String filePath;
    private Long fileSize;
}

