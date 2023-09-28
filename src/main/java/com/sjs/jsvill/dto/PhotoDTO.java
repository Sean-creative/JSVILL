package com.sjs.jsvill.dto;

import com.sjs.jsvill.entity.Photo;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Builder
@Data
public class PhotoDTO {
    private Long contractRowid;
    private String origFileName;
    private String fileKey;
    private Long fileSize;

    public static List<PhotoDTO> entityToDTOList(List<Photo> photoList) {
        List<PhotoDTO> photoDTOList = new ArrayList<>();
        for (Photo photo : photoList) {
            photoDTOList.add(PhotoDTO.builder()
                    .origFileName(photo.getOrigFileName())
                    .fileKey(photo.getFileKey())
                    .fileSize(photo.getFileSize()).build());
        }
        return photoDTOList;
    }
}

