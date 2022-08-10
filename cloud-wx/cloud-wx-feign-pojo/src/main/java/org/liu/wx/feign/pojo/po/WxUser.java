package org.liu.wx.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@TableName("wx_user")
@Accessors(chain = true)
@Data
public class WxUser implements Serializable {
    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * wx_app.app_id
     */
    private String appId;
    /**
     * 手机号码
     */
    private String phoneNumber;
    /**
     * 昵称
     */
    private String nickName;
    /**
     * 性别（0-未知，1-男性，2-女性）
     */
    private String gender;
    /**
     * 市
     */
    private String city;
    /**
     * 省
     */
    private String province;
    /**
     * 区
     */
    private String country;
    /**
     * 头像地址
     */
    private String avatarUrl;
    /**
     * 显示省市区所用的语言
     */
    private String language;
    /**
     * unionId
     */
    private String unionId;
    /**
     * openId
     */
    private String openId;
    /**
     * 地理位置纬度
     */
    private Double latitude;
    /**
     * 地理位置经度
     */
    private Double longitude;
    /**
     * 地理位置精度
     */
    @TableField(value = "`precision`")
    private Double precision;
    /**
     * 是否订阅（0：否；1：是；2：网页授权用户）
     */
    private Integer subscribe;
    /**
     * 返回用户关注的渠道来源，ADD_SCENE_SEARCH 公众号搜索，ADD_SCENE_ACCOUNT_MIGRATION 公众号迁移，ADD_SCENE_PROFILE_CARD 名片分享，ADD_SCENE_QR_CODE 扫描二维码，ADD_SCENEPROFILE LINK 图文页内名称点击，ADD_SCENE_PROFILE_ITEM 图文页右上角菜单，ADD_SCENE_PAID 支付后关注，ADD_SCENE_OTHERS 其他
     */
    private String subscribeScene;
    /**
     * 关注时间
     */
    private Date subscribeTime;
    /**
     * 关注次数
     */
    private Integer subscribeNum;
    /**
     * 取消关注时间
     */
    private Date cancelSubscribeTime;
    /**
     * 用户所在的分组ID（兼容旧的用户分组接口）
     */
    private String groupId;
    /**
     * 用户被打上的标签 ID 列表
     */
    private String tagidList;
    /**
     * 二维码扫码场景（开发者自定义）
     */
    private String qrScene;
    /**
     * 二维码扫码场景描述（开发者自定义）
     */
    private String qrSceneStr;
    /**
     * 状态（0-正常，1-冻结）
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否删除：0-存在；1-已删除
     */
    private Integer deleted;
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    /**
     * 创建者
     */
    private String createBy;
    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;
    /**
     * 更新者
     */
    private String updateBy;
    /**
     * 租户ID
     */
    private Long tenantId;
}

