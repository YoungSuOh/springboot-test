package org.example.springjpa.domain.s3;


import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class S3Controller {
    private final AmazonS3 amazonS3;
    @Value("${cloud.aws.s3.bucket-name}")
    private String bucketName;
    @Value("${cloud.aws.s3.bucket-directory}")
    private String bucketDirectory;

    @GetMapping("/test")
    public String testEndpoint() {
        return "Test Successful!";
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        System.out.println("hello");
        try {
            // MultipartFile을 임시 파일로 변환
            File file = convertMultipartFileToFile(multipartFile);
            // Object storage에 파일 업로드
            uploadFile(bucketName, bucketDirectory, file);
            return "uploadSuccess";  // 성공 시 uploadSuccess.html을 반환
        } catch (Exception e) {
            e.printStackTrace();
            return "uploadError";  // 실패 시 uploadError.html을 반환
        }
    }

    public String uploadFile(String bucketName, String directoryPath, File file) {
        // String fileName = file.getName(); - public void

        String fileName = UUID.randomUUID().toString();
        // UUID : 숫자 형식으로 파일명을 만들어줌; 같은 파일명을 올려도 다른 이름으로 계속 올라감

        FileInputStream fileIn = null;
        try {
            fileIn = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        /* 정보 파일 잡기 */
        ObjectMetadata objectMetadata = new ObjectMetadata();
        // objectMetadata.setContentType("image/jpeg"); 이러면 귀찮음 for 문 써줘야함

        Path path = Paths.get(file.getAbsolutePath());
        String contentType = null;
        try {
            contentType = Files.probeContentType(path);
        } catch (IOException e) {
            e.printStackTrace();
        }

        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(file.length());

        String key = directoryPath.endsWith("/") ? directoryPath + fileName : directoryPath + "/" + fileName;

        // putObjectRequest 생성해서 bucketName 에 directoryPath + fileName 이름으로 파일 불러들임
        PutObjectRequest putObjectRequest =
                new PutObjectRequest(bucketName,
                        key,
                        fileIn,
                        objectMetadata)
                        .withCannedAcl(CannedAccessControlList.PublicRead);
        // Acl : 리소스에 대한 접근 권한
        // 모든 사용자가 객체를 읽을 수 있지만, 수정과 삭제는 불가능

        amazonS3.putObject(putObjectRequest); // Object에 올리기

        return fileName;
    }

    private File convertMultipartFileToFile(MultipartFile multipartFile) throws IOException {
        File convFile = new File(System.getProperty("java.io.tmpdir") + "/" + multipartFile.getOriginalFilename());
        multipartFile.transferTo(convFile); // MultipartFile을 임시 파일로 변환
        return convFile;
    }
}
