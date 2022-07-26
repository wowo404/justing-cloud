package org.liu.wx.feign.pojo.req;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class OperateAccountReq {
    private Long userId;
    private BigDecimal amount;
}
