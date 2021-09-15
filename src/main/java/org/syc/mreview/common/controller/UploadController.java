package org.syc.mreview.common.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.syc.mreview.common.dto.UploadResultDTO;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 신규 작성(388p)
@RestController
@Log4j2
public class UploadController {

    public static final String SEPERATOR = "/";
    // 신규 작성(394p)
    @Value("${org.syc.upload.path}")    // application.yml의 변수
    private String uploadPath;

//    @PostMapping("/uploadAjax")
//    public void uploadFile(MultipartFile[] uploadFiles) {
//
//        for (MultipartFile uploadFile: uploadFiles) {
//
//            // 추가 작성(396p), 이미지 파일만 업로드 가능
//            if(uploadFile.getContentType().startsWith("image") == false) {
//                log.warn("this file is not image type");
//            }   // 추가 작성끝
//
//            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
//            String originalName =uploadFile.getOriginalFilename();
//            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);
//
//            log.info("fileName: " + fileName);
//
//            // 추가 작성(396p), 날짜 폴더 생성
//            String folderPath = makeFolder();
//
//            // 추가 작성(396p), UUID
//            String uuid = UUID.randomUUID().toString();
//
//            // 추가 작성(396p), 저장할 파일 이름 중간에 "_"를 이용해서 구분
//            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
//
//            Path savePath = Paths.get(saveName);
//
//            try{
//                uploadFile.transferTo(savePath);
//            }
//            catch (IOException e) {
//                e.printStackTrace();
//            }
//            // 추가 작성끝(396p)
//
//        }   // end for
//
//
//    }   // end uploadFile

    // 추가 수정(399p)
    @PostMapping("/uploadAjax")
    public ResponseEntity<List<UploadResultDTO>> uploadFile(MultipartFile[] uploadFiles) {

        List<UploadResultDTO> resultDTOList = new ArrayList<>();

        for (MultipartFile uploadFile: uploadFiles) {

            // 추가 작성(396p), 이미지 파일만 업로드 가능
            if(uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }   // 추가 작성끝

            // 실제 파일 이름 IE나 Edge는 전체 경로가 들어오므로
            String originalName =uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName: " + fileName);

            // 추가 작성(399p), 날짜 폴더 생성
            String folderPath = makeFolder();

            // 추가 작성(399p), UUID
            String uuid = UUID.randomUUID().toString();

            // 추가 작성(399p), 저장할 파일 이름 중간에 "_"를 이용해서 구분
            String saveName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(saveName);

            try{
                // 원본 파일 저장
                uploadFile.transferTo(savePath);

                // 추가 작성(406p), 섬네일 생성
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator +"s_" + uuid +"_" + fileName;

                // 추가 작성(406p), 섬네일 파일 이름은 중간에 s_로 시작하도록
                File thumbnailFile = new File(thumbnailSaveName);

                // 추가 작성(406p), 섬네일 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile,100,100 );   // 가로, 세로 100px 사이즈의 섬네일
                resultDTOList.add(new UploadResultDTO(fileName, uuid, folderPath));
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        }   // end for

        return new ResponseEntity<>(resultDTOList, HttpStatus.OK);
    }   // end uploadFile

    // 추가 작성(397p), makeFolder() 메서드 정의
    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));

        String folderPath = str.replace(SEPERATOR, File.separator);

        // make folder ------------------------------------------------
        File uploadPathFolder = new File(uploadPath, folderPath);

        if(uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }

        return folderPath;
    }

    // 업로드 이미지 출력하기, 신규 작성(401p)
    @GetMapping("/display")
    public ResponseEntity<byte[]>getFile(String fileName) {

        ResponseEntity<byte[]> result = null;

        try{
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");

            log.info("fileName: " + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);

            log.info("file: " + file);

            HttpHeaders header = new HttpHeaders();

            // MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);
        }
        catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return result;
    }
}