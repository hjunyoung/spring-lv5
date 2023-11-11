/*
package com.example.springlv5.aws;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/file")
public class S3Controller {
    private final S3Service s3Service;

    @PostMapping("/uploadFile")
    public ResponseEntity<List<String>> uploadFile(List<MultipartFile> multipartFiles){
        return ResponseEntity.ok(s3Service.uploadFile(multipartFiles));
    }

    @DeleteMapping("/deleteFile")
    public ResponseEntity<String> deleteFile(@RequestParam String fileName){
        s3Service.deleteFile(fileName);
        return ResponseEntity.ok(fileName);
    }
}
*/
