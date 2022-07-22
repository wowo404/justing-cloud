package org.liu.product.feign.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author lzs
 * @Date 2022/7/18 16:00
 **/
@Data
public class EditStockReq {
    @NotNull
    private Long skuId;
    /**
     * 注意：减库存用负数
     */
    @NotNull
    private Integer stock;
}
