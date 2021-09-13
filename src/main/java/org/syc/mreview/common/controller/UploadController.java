package org.syc.mreview.common.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName =uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);
        }   // end for

    }   // end uploadFile

}