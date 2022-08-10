package org.liu.admin.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("sys_operate_log")
@Accessors(chain = true)
@Data
public class OperateLog implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 操作员姓名
     */
    private String operatorName;
    /**
     * 操作动作名称，如：新增订单
     */
    private String actionName;
    /**
     * 操作对应业务表的ID
     */
    private Integer bizTableId;
    /**
     * 请求参数
     */
    private String requestArgs;
    /**
     * 来源IP
     */
    private String ip;
    /**
     * 浏览器
     */
    private String browser;
    /**
     * 操作系统
     */
    private String os;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 租户ID
     */
    private Long tenantId;
}

