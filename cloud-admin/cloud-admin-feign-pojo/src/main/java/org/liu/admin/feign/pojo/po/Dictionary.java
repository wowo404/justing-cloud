package org.liu.admin.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
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
    private String type;
    /**
     * 字典key，同一type下的key不能相同
     */
    private String key;
    /**
     * 字典key对应的值
     */
    private String value;
    /**
     * 备注
     */
    private String remark;
    /**
     * 同一个type下的排序
     */
    private Integer sortNumber;
}

