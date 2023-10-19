package com.sjs.jsvill.service.photo;


import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {
    List<Photo> contractPhotoRegister(List<MultipartFile> files, List<Boolean> bookMarks, Long contractRowid);
    List<PhotoDTO> contractPhotogetList(Long contractRowid);
    void deletePhoto(String fileName);
    void bookmark(Long photoRowid, Boolean bookmark);
}
