package com.sjs.jsvill.service.awsS3;

import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AwsS3Service {
    List<Photo> contractPhotoRegister(List<MultipartFile> files, List<Boolean> bookMarks, Long contractRowid);
    List<PhotoDTO> contractPhotogetList(Long contractRowid);
    void deleteFile(String fileName);
}