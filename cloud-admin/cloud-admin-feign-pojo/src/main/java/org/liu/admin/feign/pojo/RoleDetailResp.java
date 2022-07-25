package org.liu.admin.feign.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class RoleDetailResp {
    /**
     * 角色ID
     */
    private Long id;
    /**
     * 角色编码
     */
    private String code;
    /**
     * 角色名称
     */
    private String name;
    /**
     * 数据范围（0-全部数据权限，1-自定数据权限，2-本部门数据权限，3-本部门及以下数据权限，4-自定义数据权限）
     */
    private Integer dataScope;
    /**
     * 是否删除：0-存在；1-已删除
     */
    private Integer deleted;
    /**
     * 备注
     */
    private String remark;
    /**
     * 状态（0-正常，1-注销）
     */
    private Integer status;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 修改者
     */
    private String updateBy;
    /**
     * 修改时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 租户ID
     */
    private Long tenantId;
}
