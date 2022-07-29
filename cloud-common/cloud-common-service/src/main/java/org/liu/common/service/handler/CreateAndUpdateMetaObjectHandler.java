package org.liu.common.service.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.liu.common.security.base.util.SecurityUtils;

/**
 * MP注入处理器
 *
 * @author Lion Li
 * @date 2021/4/25
 */
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        //根据属性名字设置要填充的值
        if (metaObject.hasGetter("createBy")) {
            if (metaObject.getValue("createBy") == null) {
                this.setFieldValByName("createBy", SecurityUtils.getDisplayName(), metaObject);
            }
        }
        this.updateFill(metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        if (metaObject.hasGetter("updateBy")) {
            if (metaObject.getValue("updateBy") == null) {
                this.setFieldValByName("updateBy", SecurityUtils.getDisplayName(), metaObject);
            }
        }
    }

}
