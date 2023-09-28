package com.sjs.jsvill.util;

import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

public interface AwsS3Service {

    List<String> uploadFile(List<MultipartFile> multipartFile);
    void deleteFile(String fileName);

    List<Photo> contractPhotoRegister(List<MultipartFile> files, Long contractRowid);
    List<PhotoDTO> contractPhotogetList(Long contractRowid);
}