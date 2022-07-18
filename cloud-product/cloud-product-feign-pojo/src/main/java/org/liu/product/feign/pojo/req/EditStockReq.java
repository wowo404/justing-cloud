package org.liu.product.feign.pojo.req;

import lombok.Data;

/**
 * @Author lzs
 * @Date 2022/7/18 16:00
 **/
@Data
public class EditStockReq {
    private Long skuId;
    private Integer stock;
}
