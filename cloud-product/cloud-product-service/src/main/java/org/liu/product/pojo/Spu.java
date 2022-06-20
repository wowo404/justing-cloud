package org.liu.product.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("spu")
@Accessors(chain = true)
@Data
public class Spu implements Serializable {
    /**
     * spu id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 标题
     */
    @TableField
    private String title;
    /**
     * 子标题
     */
    @TableField
    private String subTitle;
    /**
     * 1级类目id
     */
    @TableField
    private Long categoryId1;
    /**
     * 2级类目id
     */
    @TableField
    private Long categoryId2;
    /**
     * 3级类目id
     */
    @TableField
    private Long categoryId3;
    /**
     * 商品所属品牌id
     */
    @TableField
    private Long brandId;
    /**
     * 是否上架，0下架，1上架
     */
    @TableField
    private Integer saleable;
    /**
     * 是否有效，0已删除，1有效
     */
    @TableField
    private Integer valid;
}

