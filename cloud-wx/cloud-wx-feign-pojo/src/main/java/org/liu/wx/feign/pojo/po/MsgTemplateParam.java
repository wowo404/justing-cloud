package org.liu.wx.feign.pojo.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@TableName("msg_template_param")
@Accessors(chain = true)
@Data
public class MsgTemplateParam implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 模板id
     */
    private String templateId;
    /**
     * 公众号AppID、小程序AppID
     */
    private String appId;
    /**
     * 参数名称
     */
    private String name;
    /**
     * 参数值
     */
    private String value;
    /**
     * 内容名称
     */
    private String content;
}

