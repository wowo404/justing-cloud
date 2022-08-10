package org.liu.product.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("sku")
@Accessors(chain = true)
@Data
public class Sku implements Serializable {
    /**
     * sku id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * spu id
     */
    private Long spuId;
    /**
     * 商品标题
     */
    private String title;
    /**
     * 商品的图片，多个图片以‘,’分割
     */
    private String images;
    /**
     * 销售价格
     */
    private Long price;
    /**
     * 特有规格属性在spu属性模板中的对应下标组合
     */
    private String indexes;
    /**
     * sku的特有规格参数，json格式
     */
    private String ownSpec;
    /**
     * 是否有效，0无效，1有效
     */
    private Integer enable;
    /**
     * 添加时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
}

