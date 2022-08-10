package org.liu.product.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

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
    private String title;
    /**
     * 子标题
     */
    private String subTitle;
    /**
     * 1级类目id
     */
    private Long categoryId1;
    /**
     * 2级类目id
     */
    private Long categoryId2;
    /**
     * 3级类目id
     */
    private Long categoryId3;
    /**
     * 商品所属品牌id
     */
    private Long brandId;
    /**
     * 是否上架，0下架，1上架
     */
    private Integer saleable;
    /**
     * 是否有效，0已删除，1有效
     */
    private Integer valid;
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

