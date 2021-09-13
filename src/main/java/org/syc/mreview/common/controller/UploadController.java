package org.syc.mreview.common.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

// 신규 작성(388p)
@RestController
@Log4j2
public class UploadController {

    // 신규 작성(394p)
    @Value("${org.syc.upload.path}")    // application.yml의 변수
    private String uploadPath;

    @PostMapping("/uploadAjax")
    public void uploadFile(MultipartFile[] uploadFiles) {

        for (MultipartFile uploadFile: uploadFiles) {

            // 추가 작성(396p), 이미지 파일만 업로드 가능
            if(uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
            }   // 추가 작성끝

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName =uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);

            // 추가 작성(396p), 날짜 폴더 생성
            String folderPath = makeFolder();

            // 추가 작성(396p), UUID
            String uuid = UUID.randomUUID().toString();

            // 추가 작성(396p), 저장할 파일 이름 중간에 "_"를 이용해서 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try{
                uploadFile.transferTo(savePath);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            // 추가 작성끝(396p)

        }   // end for


    }   // end uploadFile

    // 추가 작성(397p), makeFolder() 메서드 정의
    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace("/", File.separator);

        // make folder ------------------------------------------------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if(uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdir();
        }

        return folderPath;
    }

}