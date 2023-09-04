package com.sjs.jsvill.service.photo;


import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    List<Photo> register(List<MultipartFile> files, Long contractRowid);
    List<PhotoDTO> getList(Long contractRowid);
}

