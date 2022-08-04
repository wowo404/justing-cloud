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

@TableName("wx_auto_reply")
@Accessors(chain = true)
@Data
public class WxAutoReply implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    /**
     * 公众号配置ID、小程序AppID
     */
    @TableField
    private String appId;
    /**
     * 类型（1、关注时回复；2、消息回复；3、关键词回复）
     */
    @TableField
    private Integer type;
    /**
     * 关键词
     */
    @TableField
    private String reqKey;
    /**
     * 请求消息类型（text：文本；image：图片；voice：语音；video：视频；shortvideo：小视频；location：地理位置）
     */
    @TableField
    private String reqType;
    /**
     * 回复消息类型（text：文本；image：图片；voice：语音；video：视频；music：音乐；news：图文）
     */
    @TableField
    private String repType;
    /**
     * 回复类型文本匹配类型（1、全匹配，2、半匹配）
     */
    @TableField
    private String repMate;
    /**
     * 回复类型文本保存文字
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
     * 图文消息的内容
     */
    @TableField
    private String content;
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
    private String tenantId;
}

