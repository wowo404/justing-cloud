package org.liu.publicbase.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.justing.commons.model.Response;
import org.liu.publicbase.service.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @Author lzs
 * @Date 2022/2/18 16:25
 **/
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("file")
public class FileController {

    private final FileService fileService;

    @PostMapping("upload")
    public Response<Void> upload(@RequestParam("file") MultipartFile file) throws IOException {
        fileService.upload(file);
        return Response.ok();
    }

}
