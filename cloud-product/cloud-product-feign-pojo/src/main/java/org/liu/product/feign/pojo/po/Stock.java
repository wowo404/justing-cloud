package org.liu.product.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("stock")
@Accessors(chain = true)
@Data
public class Stock implements Serializable {
    /**
     * 库存对应的商品sku id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long skuId;
    /**
     * 可秒杀库存
     */
    private Integer seckillStock;
    /**
     * 秒杀总数量
     */
    private Integer seckillTotal;
    /**
     * 库存数量
     */
    private Integer stock;
}

