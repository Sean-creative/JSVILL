package com.sjs.jsvill.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Contract;
import com.sjs.jsvill.entity.Photo;
import com.sjs.jsvill.repository.ContractRepository;
import com.sjs.jsvill.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class AwsS3ServiceImpl implements AwsS3Service {

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    private final PhotoRepository photoRepository;
    private final ContractRepository contractRepository;

    private final AmazonS3 amazonS3; //AWS에 파일을 CRUD
    private final AWSFileHandler awsFileHandler;

    @Override
    public List<String> uploadFile(List<MultipartFile> multipartFileList) {
        List<String> fileNameList = new ArrayList<>();

        // forEach 구문을 통해 multipartFile로 넘어온 파일들 하나씩 fileNameList에 추가
        multipartFileList.forEach(file -> {
            String fileName = awsFileHandler.createFileName(file.getOriginalFilename());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch(IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }

            fileNameList.add(fileName);
        });
        return fileNameList;
    }
    @Override
    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }

    @SneakyThrows
    @Override
    public List<Photo> contractPhotoRegister(List<MultipartFile> files, Long contractRowid) {
        List<Photo> photoList = awsFileHandler.parseFileInfo(files);
        // 파일이 존재할 때에만 처리
        if(!photoList.isEmpty()) {
            for(Photo photo : photoList) {
                photo.setContract(contractRepository.getById(contractRowid));
                // 파일을 DB에 저장
                photoRepository.save(photo);
            }
        }
        return photoList;
    }
    @Override
    public List<PhotoDTO> contractPhotogetList(Long contractRowid) {
        Contract contract = Contract.builder().contract_rowid(contractRowid).build();
        return PhotoDTO.entityToDTOList(photoRepository.findByContract(contract));
    }
}