package org.liu.storage.service;

import org.liu.storage.feign.pojo.OperateStorageReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class StorageService {
    public void operate(OperateStorageReq req) {

    }
}
