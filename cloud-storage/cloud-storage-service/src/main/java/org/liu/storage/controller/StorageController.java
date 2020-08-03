package org.liu.storage.controller;

import org.justing.commons.model.Response;
import org.liu.storage.feign.pojo.OperateStorageReq;
import org.liu.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("storage")
@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("operate")
    public Response<Void> operate(@RequestBody OperateStorageReq req){
        storageService.operate(req);
        return Response.ok();
    }

}
