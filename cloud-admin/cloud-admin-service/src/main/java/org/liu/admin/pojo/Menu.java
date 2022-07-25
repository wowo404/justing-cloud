package org.liu.admin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("sys_menu")
@Accessors(chain = true)
@Data
public class Menu implements Serializable {
    /**
     * 资源ID
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 资源名称
     */
    @TableField
    private String name;
    /**
     * 类型：0-菜单；1-按钮；2-目录
     */
    @TableField
    private Integer type;
    /**
     * 资源是否可见，0-可见，1-不可见，部分资源可能是特定角色的，不可用来分配
     */
    @TableField
    private Integer isDisplay;
    /**
     * 父资源ID，如果是根节点，为0
     */
    @TableField
    private Long parentId;
    /**
     * URL地址
     */
    @TableField
    private String url;
    /**
     * 前端path
     */
    @TableField
    private String path;
    /**
     * 前端组件名
     */
    @TableField
    private String component;
    /**
     * 样式
     */
    @TableField
    private String style;
    /**
     * 顺序
     */
    @TableField
    private Integer sortNumber;
    /**
     * 状态（0-可用，1-不可用）
     */
    @TableField
    private Integer status;
    /**
     * 备注
     */
    @TableField
    private String remark;
}

