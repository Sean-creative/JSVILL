package com.sjs.jsvill.service.awsS3;

import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Photo;
import com.sjs.jsvill.repository.ContractRepository;
import com.sjs.jsvill.repository.PhotoRepository;
import com.sjs.jsvill.util.AWSFileHandler;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.IntStream;

@Service
@Log4j2
@RequiredArgsConstructor
//계약서 사진 등과 같은 S3 관련 작업 처리
public class AwsS3ServiceImpl implements AwsS3Service {
    
    private final PhotoRepository photoRepository;
    private final ContractRepository contractRepository;
    private final AWSFileHandler awsFileHandler;

    @SneakyThrows
    @Override
    public List<Photo> contractPhotoRegister(List<MultipartFile> multipartFileList, List<Boolean> bookMarks, Long contractRowid) {
        System.out.println("bookMarks: " + bookMarks);
        bookMarks.forEach(i -> {
            System.out.println("i :" + i);
        });
        List<Photo> photoList = awsFileHandler.uploadFileForContractImage(multipartFileList);
        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            Contract contract = contractRepository.getById(contractRowid);
            IntStream.range(0, photoList.size())
                    .forEach(idx -> {
                        Photo photo = photoList.get(idx);
                        photo.setContract(contract);
                        photo.setBookMark(bookMarks.get(idx));
                    });

            photoRepository.saveAll(photoList);
        }
        return photoList;
    }
    @Override
    public List<PhotoDTO> contractPhotogetList(Long contractRowid) {
        Contract contract = Contract.builder().contract_rowid(contractRowid).build();
        List<Photo> photoList = photoRepository.findByContract(contract);
        photoList.forEach(photo -> {
            String awsFileUrl = awsFileHandler.changeToAwsUrl(photo.getFileKey());
            photo.setFileKey(awsFileUrl);
        });
        return PhotoDTO.entityToDTOList(photoList);
    }

    @Override
    public void deleteFile(String fileName) {
        awsFileHandler.deleteFile(fileName);
    }
}