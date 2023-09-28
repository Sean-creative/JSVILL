package com.sjs.jsvill.util;

import com.sjs.jsvill.entity.Photo;
import com.sjs.jsvill.service.photo.PhotoService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**List<MultipartFile> 을 전달받아 파일을 저장한 후 관련 정보를 List<Photo>로 변환하여 반환*/
@Component
public class AWSFileHandler {

    //파일의 경로를 지정하고 Photo 엔티티 형태로 반환
    public List<Photo> parseFileInfo(List<MultipartFile> multipartFiles) throws Exception {

        // 반환할 파일 리스트
        List<Photo> fileList = new ArrayList<>();

        // 전달되어 온 파일이 존재할 경우
        //Apache Commons 라이브러리 중 Null 체크를 해주는 함수 (Null일때 오류 발생하지 않음)
        if(!CollectionUtils.isEmpty(multipartFiles)) {

            // 파일명을 업로드 한 날짜로 변환하여 저장
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String currentDate = LocalDateTime.now().format(dateTimeFormatter);
            String targetResource = "/contractImage".replace("/", File.separator) + File.separator + currentDate;

            // 다중 파일 처리
            for(MultipartFile multipartFile : multipartFiles) {




                String fileEntityPath = targetResource + File.separator + createFileName(multipartFile.getOriginalFilename());
                // Photo 엔티티 생성
                Photo photo = new Photo(
                        multipartFile.getOriginalFilename(),
                        fileEntityPath,
                        multipartFile.getSize()
                );
                // 생성 후 리스트에 추가
                fileList.add(photo);
            }
        }
        return fileList;
    }

    // 먼저 파일 업로드 시, 파일명을 난수화하기 위해 random으로 돌립니다.
    public String createFileName(String fileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(fileName));
    }

    // file 형식이 잘못된 경우를 확인하기 위해 만들어진 로직이며, 파일 타입과 상관없이 업로드할 수 있게 하기 위해 .의 존재 유무만 판단
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
}