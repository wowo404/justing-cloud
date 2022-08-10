package org.liu.product.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("spec_param")
@Accessors(chain = true)
@Data
public class SpecParam implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 商品分类id
     */
    private Long categoryId;
    /**
     * 规格组ID
     */
    private Long specGroupId;
    /**
     * 参数名
     */
    private String name;
    /**
     * 是否是数字类型参数（0-false，1-true）
     */
    private Integer numeric;
    /**
     * 数字类型参数的单位，非数字类型可以为空
     */
    private String unit;
    /**
     * 是否是sku通用属性（0-false，1-true）
     */
    private Integer generic;
    /**
     * 是否用于搜索过滤（0-false，1-true）
     */
    private Integer searching;
    /**
     * 数值类型参数，如果需要搜索，则添加分段间隔值，如CPU频率间隔：0.5-1.0
     */
    private String segments;
}

