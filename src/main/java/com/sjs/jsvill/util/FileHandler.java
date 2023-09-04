package com.sjs.jsvill.util;

import com.sjs.jsvill.dto.PhotoDTO;
import com.sjs.jsvill.entity.Photo;
import com.sjs.jsvill.service.photo.PhotoService;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**List<MultipartFile> 을 전달받아 파일을 저장한 후 관련 정보를 List<Photo>로 변환하여 반환*/
@Component
public class FileHandler {

    public List<Photo> parseFileInfo(List<MultipartFile> multipartFiles) throws Exception {

        // 반환할 파일 리스트
        List<Photo> fileList = new ArrayList<>();

        // 전달되어 온 파일이 존재할 경우
        //Apache Commons 라이브러리 중 Null 체크를 해주는 함수 (Null일때 오류 발생하지 않음)
        if(!CollectionUtils.isEmpty(multipartFiles)) {
            // 파일명을 업로드 한 날짜로 변환하여 저장
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter =
                    DateTimeFormatter.ofPattern("yyyyMMdd");
            String currentDate = now.format(dateTimeFormatter);

            // 프로젝트 디렉터리 내의 저장을 위한 절대 경로 설정
            // 경로 구분자 File.separator 사용
            String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;

            // 파일을 저장할 세부 경로 지정
            String staticResource = "src/main/resources/static".replace("/", File.separator);
            String targetResource = "/image".replace("/", File.separator) + File.separator + currentDate;
            String fileSavePath = staticResource + targetResource;
            File file = new File(fileSavePath);

            // 디렉터리가 존재하지 않을 경우
            if(!file.exists()) {
                boolean wasSuccessful = file.mkdirs();

                // 디렉터리 생성에 실패했을 경우
                if(!wasSuccessful)
                    System.out.println("file: was not successful");
            }

            // 다중 파일 처리
            for(MultipartFile multipartFile : multipartFiles) {

                // 파일의 확장자 추출
                String originalFileExtension;
                String contentType = multipartFile.getContentType();

                // 확장자명이 존재하지 않을 경우 처리 x
                if(ObjectUtils.isEmpty(contentType)) {
                    break;
                }
                else {  // 확장자가 jpeg, png인 파일들만 받아서 처리
                    if(contentType.contains("image/jpeg"))
                        originalFileExtension = ".jpg";
                    else if(contentType.contains("image/png"))
                        originalFileExtension = ".png";
                    else  // 다른 확장자일 경우 처리 x
                        break;
                }

                // 파일명 중복 피하고자 나노초까지 얻어와 지정
                String new_file_name = System.nanoTime() + originalFileExtension;

                // 업로드 한 파일 데이터를 지정한 파일에 저장
                file = new File(absolutePath + fileSavePath + File.separator + new_file_name);
                multipartFile.transferTo(file);

                // 파일 권한 설정(쓰기, 읽기)
                file.setWritable(true);
                file.setReadable(true);

                String fileEntityPath = targetResource + File.separator + new_file_name;
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
}