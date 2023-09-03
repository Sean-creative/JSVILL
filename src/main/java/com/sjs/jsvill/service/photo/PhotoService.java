package com.sjs.jsvill.service.photo;


import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoService {

    Long register(List<MultipartFile> files, Long contractRowid);
}

