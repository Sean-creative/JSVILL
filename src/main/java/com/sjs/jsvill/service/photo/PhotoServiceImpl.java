package com.sjs.jsvill.service.photo;

import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Photo;
import com.sjs.jsvill.repository.ContractRepository;
import com.sjs.jsvill.repository.PhotoRepository;
import com.sjs.jsvill.util.FileHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final ContractRepository contractRepository;
    private final FileHandler fileHandler;

    @SneakyThrows
    @Override
    public Long register(List<MultipartFile> files, Long contractRowid) {
        List<Photo> photoList = fileHandler.parseFileInfo(files);
        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            for(Photo photo : photoList) {
                photo.setContract(contractRepository.getById(contractRowid));
                // 파일을 DB에 저장
                photoRepository.save(photo);
            }
        }
        return null;
    }
}
