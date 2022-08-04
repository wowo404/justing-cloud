package org.liu.wx.feign.pojo.po;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("wx_msg")
@Accessors(chain = true)
@Data
public class WxMsg implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 公众号配置ID、小程序AppID
     */
    @TableField
    private String appId;
    /**
     * 公众号名称
     */
    @TableField
    private String appName;
    /**
     * 公众号logo
     */
    @TableField
    private String appLogo;
    /**
     * 微信用户ID
     */
    @TableField
    private Long wxUserId;
    /**
     * 微信用户昵称
     */
    @TableField
    private String nickName;
    /**
     * 微信用户头像
     */
    @TableField
    private String headimgUrl;
    /**
     * 消息分类（1、用户发给公众号；2、公众号发给用户；）
     */
    @TableField
    private Integer type;
    /**
     * 消息类型（text：文本；image：图片；voice：语音；video：视频；shortvideo：小视频；location：地理位置；music：音乐；news：图文；event：推送事件）
     */
    @TableField
    private String repType;
    /**
     * 事件类型（subscribe：关注；unsubscribe：取关；CLICK、VIEW：菜单事件）
     */
    @TableField
    private String repEvent;
    /**
     * 回复类型文本保存文字、地理位置信息
     */
    @TableField
    private String repContent;
    /**
     * 回复类型imge、voice、news、video的mediaID或音乐缩略图的媒体id
     */
    @TableField
    private String repMediaId;
    /**
     * 回复的素材名、视频和音乐的标题
     */
    @TableField
    private String repName;
    /**
     * 视频和音乐的描述
     */
    @TableField
    private String repDesc;
    /**
     * 链接
     */
    @TableField
    private String repUrl;
    /**
     * 高质量链接
     */
    @TableField
    private String repHqUrl;
    /**
     * 图文消息的内容
     */
    @TableField
    private String content;
    /**
     * 缩略图的媒体id
     */
    @TableField
    private String repThumbMediaId;
    /**
     * 缩略图url
     */
    @TableField
    private String repThumbUrl;
    /**
     * 地理位置维度
     */
    @TableField
    private Double repLocationX;
    /**
     * 地理位置经度
     */
    @TableField
    private Double repLocationY;
    /**
     * 地图缩放大小
     */
    @TableField
    private Double repScale;
    /**
     * 已读标记（0：否；1：是）
     */
    @TableField
    private Integer readFlag;
    /**
     * 备注
     */
    @TableField
    private String remark;
    /**
     * 是否删除：0-存在；1-已删除
     */
    @TableField
    private Integer deleted;
    /**
     * 创建者
     */
    @TableField
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date createTime;
    /**
     * 更新者
     */
    @TableField
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @TableField
    private Date updateTime;
    /**
     * 所属租户
     */
    @TableField
    private Long tenantId;
}

