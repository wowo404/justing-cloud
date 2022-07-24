package org.liu.admin.feign.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("sys_dictionary")
@Accessors(chain = true)
@Data
public class Dictionary implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 字典类型
     */
    @TableField
    private String type;
    /**
     * 字典key，同一type下的key不能相同
     */
    @TableField
    private String key;
    /**
     * 字典key对应的值
     */
    @TableField
    private String value;
    /**
     * 同一个type下的排序
     */
    @TableField
    private Integer sortNumber;
}

