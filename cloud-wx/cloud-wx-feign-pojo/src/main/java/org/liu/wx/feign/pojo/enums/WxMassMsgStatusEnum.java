package org.liu.wx.feign.pojo.enums;

/**
 * 消息发送状态(SUB_SUCCESS：提交成功，SUB_FAIL：提交失败，SEND_SUCCESS：发送成功，SENDING：发送中，SEND_FAIL：发送失败，DELETE：已删除)
 */
public enum WxMassMsgStatusEnum {
    SUB_SUCCESS,SUB_FAIL,SEND_SUCCESS,SENDING,SEND_FAIL,DELETE
}
