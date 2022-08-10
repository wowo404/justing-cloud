package org.liu.admin.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("sys_config")
@Accessors(chain = true)
@Data
public class Config implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    private String name;
    /**
     * 键，唯一
     */
    private String key;
    /**
     * 值
     */
    private String value;
    /**
     * 备注
     */
    private String remark;
    /**
     * 租户ID
     */
    private Long tenantId;
}

