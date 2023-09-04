package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Photo;
import lombok.Builder;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class PhotoDTO {
    private String contractRowid;
    private String origFileName;
    private String filePath;
    private Long fileSize;

    public static List<PhotoDTO> entityToDTOList(List<Photo> photoList) {
        List<PhotoDTO> photoDTOList = new ArrayList<>();
        for (Photo photo : photoList) {
            photoDTOList.add(PhotoDTO.builder()
                    .origFileName(photo.getOrigFileName())
                    .filePath(photo.getFilePath())
                    .fileSize(photo.getFileSize()).build());
        }
        return photoDTOList;
    }
}

