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
    private Long contractRowid;
    private String origFileName;
    private String fileKey;
    private Long fileSize;
    private Boolean bookmark;

    public static List<PhotoDTO> entityToDTOList(List<Photo> photoList) {
        List<PhotoDTO> photoDTOList = new ArrayList<>();
        for (Photo photo : photoList) {
            photoDTOList.add(PhotoDTO.builder()
                    .origFileName(photo.getOrigFileName())
                    .fileKey(photo.getFileKey())
                    .fileSize(photo.getFileSize())
                    .bookmark(photo.getBookMark())
                    .build());
        }
        return photoDTOList;
    }
}

