package org.liu.publicbase.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @Author lzs
 * @Date 2022/2/18 16:29
 **/
@RequiredArgsConstructor
@Transactional
@Service
public class FileService {
    public void upload(MultipartFile file) throws IOException {
        Files.copy(file.getInputStream(), Paths.get("e:/home/cloud/file/" + file.getOriginalFilename()));
    }
}
