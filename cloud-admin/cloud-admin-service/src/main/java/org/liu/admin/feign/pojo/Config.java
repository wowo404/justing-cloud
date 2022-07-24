package org.liu.admin.feign.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
    @TableField
    private String name;
    /**
     * 键，唯一
     */
    @TableField
    private String key;
    /**
     * 值
     */
    @TableField
    private String value;
    /**
     * 租户ID
     */
    @TableField
    private Long tenantId;
}

