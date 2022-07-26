package org.liu.product.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("spu_detail")
@Accessors(chain = true)
@Data
public class SpuDetail implements Serializable {
    /**
     * spu id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long spuId;
    /**
     * 商品描述信息
     */
    @TableField
    private String description;
    /**
     * 通用规格参数数据
     */
    @TableField
    private String genericSpec;
    /**
     * 特有规格参数及可选值信息，json格式
     */
    @TableField
    private String specialSpec;
    /**
     * 包装清单
     */
    @TableField
    private String packingList;
    /**
     * 售后服务
     */
    @TableField
    private String afterService;
}

