package org.liu.product.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("category_brand")
@Accessors(chain = true)
@Data
public class CategoryBrand implements Serializable {
    /**
     * 商品类目id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long categoryId;
    /**
     * 品牌id
     */
    @TableField
    private Long brandId;
}

