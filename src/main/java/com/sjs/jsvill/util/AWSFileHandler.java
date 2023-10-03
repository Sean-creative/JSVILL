package com.sjs.jsvill.util;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.sjs.jsvill.entity.Photo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AWSFileHandler {
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final AmazonS3 amazonS3; //AWS에 파일을 CRUD

    /**계약서 사진 업로드 후, photoList 반환*/
    public List<Photo> uploadFileForContractImage(List<MultipartFile> multipartFileList) {
        List<Photo> photoList = new ArrayList<>();
        multipartFileList.forEach(file -> {
            System.out.println("file.getOriginalFilename() : " + file.getOriginalFilename());
            System.out.println("file.getName() : " + file.getName());
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(file.getSize());
            objectMetadata.setContentType(file.getContentType());

            String fileKey = createFileName("contractImage", file.getOriginalFilename());
            try(InputStream inputStream = file.getInputStream()) {
                amazonS3.putObject(new PutObjectRequest(bucket, fileKey, inputStream, objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch(IOException e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "파일 업로드에 실패했습니다.");
            }
            photoList.add(new Photo(file.getOriginalFilename(), fileKey, file.getSize()));
        }); //end forEach
        return photoList;
    }

    public void deleteFile(String fileName) {
        amazonS3.deleteObject(new DeleteObjectRequest(bucket, fileName));
    }


    /**먼저 파일 업로드 시, 중복을 피하기 위해 파일명을 난수화*/
    public String createFileName(String firstFolder, String fileName) {
        // 파일명을 업로드 한 날짜로 변환하여 저장
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String currentDate = LocalDateTime.now().format(dateTimeFormatter);
        String targetResource = firstFolder + "/" + currentDate;
        String uniqueFileName = UUID.randomUUID().toString().concat(fileName);
        return targetResource + "/" + uniqueFileName;
    }

    /**file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단*/
    public String getFileExtension(String fileName) {
        try {
            return fileName.substring(fileName.lastIndexOf("."));
        } catch (StringIndexOutOfBoundsException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "잘못된 형식의 파일(" + fileName + ") 입니다.");
        }
//        // 파일의 확장자 추출
//        String originalFileExtension;
//        String contentType = multipartFile.getContentType();
//        // 확장자명이 존재하지 않을 경우 처리 x
//        if(ObjectUtils.isEmpty(contentType)) {
//            break;
//        }
//        else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
//            if(contentType.contains("image/jpeg"))
//                originalFileExtension = ".jpg";
//            else if(contentType.contains("image/png"))
//                originalFileExtension = ".png";
//            else  // 다른 확장자일 경우 처리 x
//                break;
//        }
    }

    public String changeToAwsUrl(String key) {
        return amazonS3.getUrl(bucket, key).toString();
    }
}