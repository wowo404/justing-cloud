package org.liu.wx.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("wx_mp_menu")
@Accessors(chain = true)
@Data
public class WxMpMenu implements Serializable {
    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 菜单是否开启，0代表未开启，1代表开启
     */
    private Integer isMenuOpen;
    /**
     * 排序值
     */
    private Integer sort;
    /**
     * 父菜单ID
     */
    private String parentId;
    /**
     * 公众号appId
     */
    private String appId;
    /**
     * 菜单类型click、view、miniprogram、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、pic_weixin、location_select、media_id、view_limited等
     */
    private String type;
    /**
     * 菜单名
     */
    private String name;
    /**
     * 官网上设置的自定义菜单： Text:保存文字到value； Img、voice：保存 mediaID 到value； Video：保存视频下载链接到value； News：保存图文消息到news_info，同时保存 mediaID 到value； View：保存链接到url
     */
    private String value;
    /**
     * 使用 API 设置的自定义菜单： click、scancode_push、scancode_waitmsg、pic_sysphoto、pic_photo_or_album、 pic_weixin、location_select：保存值到key；；view：保存链接到url
     */
    private String menuKey;
    /**
     * view、miniprogram保存链接
     */
    private String url;
    /**
     * 小程序的appid
     */
    private String maAppId;
    /**
     * 小程序的页面路径
     */
    private String maPagePath;
    /**
     * 旧版微信客户端无法支持小程序，用户点击菜单时将会打开备用网页
     */
    private String backupPagePath;
    /**
     * 回复消息类型（text：文本；image：图片；voice：语音；video：视频；music：音乐；news：图文）
     */
    private String repType;
    /**
     * Text:保存文字
     */
    private String repContent;
    /**
     * imge、voice、news、video：mediaID
     */
    private String repMediaId;
    /**
     * 素材名、视频和音乐的标题
     */
    private String repName;
    /**
     * 视频和音乐的描述
     */
    private String repDesc;
    /**
     * 链接
     */
    private String repUrl;
    /**
     * 高质量链接
     */
    private String repHqUrl;
    /**
     * 缩略图的媒体id
     */
    private String repThumbMediaId;
    /**
     * 缩略图url
     */
    private String repThumbUrl;
    /**
     * 图文消息的内容
     */
    private String content;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否删除：0-存在；1-已删除
     */
    private Integer deleted;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 所属租户
     */
    private Long tenantId;
}

