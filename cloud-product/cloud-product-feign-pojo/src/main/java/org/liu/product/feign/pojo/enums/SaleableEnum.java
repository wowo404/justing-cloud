package org.liu.product.feign.pojo.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否上架，0下架，1上架
 */
@Getter
@AllArgsConstructor
public enum SaleableEnum {
    NOT_SALE(0, "下架"), ON_SALE(1, "上架");

    private final Integer code;
    private final String msg;
}
