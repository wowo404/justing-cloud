package org.liu.admin.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("sys_operator_post")
@Accessors(chain = true)
@Data
public class OperatorPost implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 操作员ID
     */
    @TableField
    private Long operatorId;
    /**
     * 岗位ID
     */
    @TableField
    private Long postId;
}

