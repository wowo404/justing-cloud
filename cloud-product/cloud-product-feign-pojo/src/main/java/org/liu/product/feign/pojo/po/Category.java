package org.liu.product.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("category")
@Accessors(chain = true)
@Data
public class Category implements Serializable {
    /**
     * 类目id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 父类目id,顶级类目填0
     */
    private Long parentId;
    /**
     * 是否为父节点，0为否，1为是
     */
    private Integer isParent;
    /**
     * 排序指数，越小越靠前
     */
    private Integer sort;
}

