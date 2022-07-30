package org.liu.admin.feign.pojo.req;

import lombok.Data;
import org.justing.commons.model.PageReq;

/**
 * @Author lzs
 * @Date 2022/7/30 9:43
 **/
@Data
public class RoleListReq extends PageReq {
    private String name;
    private String code;
}
