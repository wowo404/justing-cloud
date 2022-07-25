package org.liu.admin.feign.pojo;

import lombok.Data;

@Data
public class MenuDetailResp {
    /**
     * 资源ID
     */
    private Long id;
    /**
     * 资源名称
     */
    private String name;
    /**
     * 类型：0-菜单；1-按钮；2-目录
     */
    private Integer type;
    /**
     * 资源是否可见，0-可见，1-不可见，部分资源可能是特定角色的，不可用来分配
     */
    private Integer isDisplay;
    /**
     * 父资源ID，如果是根节点，为0
     */
    private Long parentId;
    /**
     * URL地址
     */
    private String url;
    /**
     * 前端path
     */
    private String path;
    /**
     * 前端组件名
     */
    private String component;
    /**
     * 样式
     */
    private String style;
    /**
     * 顺序
     */
    private Integer sortNumber;
    /**
     * 状态（0-可用，1-不可用）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
}
