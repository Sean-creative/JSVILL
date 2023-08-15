package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Calendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Data
public class UnitFileDTO {
    private String memberId;
    private String title;
    private String content;
    private List<MultipartFile> files;
}