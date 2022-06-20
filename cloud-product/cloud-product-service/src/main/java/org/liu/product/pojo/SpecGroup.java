package org.liu.product.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("spec_group")
@Accessors(chain = true)
@Data
public class SpecGroup implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品分类id，一个分类下有多个规格组
     */
    @TableField
    private Long categoryId;
    /**
     * 规格组的名称
     */
    @TableField
    private String name;
}

