package org.liu.storage.feign.pojo;

import lombok.Data;

@Data
public class OperateStorageReq {
    private Long categoryId;
    private Long goodsId;
    private Integer count;
}
