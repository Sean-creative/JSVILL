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
    private Long photoRowid;
    private String origFileName;
    private String fileKey;
    private String fileUrl;
    private Long fileSize;
    private Boolean bookmark;

    public static List<PhotoDTO> entityToDTOList(List<Photo> photoList) {
        List<PhotoDTO> photoDTOList = new ArrayList<>();
        for (Photo photo : photoList) {
            photoDTOList.add(PhotoDTO.builder()
                    .photoRowid(photo.getPhotoRowid())
                    .origFileName(photo.getOrigFileName())
                    .fileKey(photo.getFileKey())
                    .fileUrl(photo.getFileUrl())
                    .fileSize(photo.getFileSize())
                    .bookmark(photo.getBookmark())
                    .build());
        }
        return photoDTOList;
    }
}

