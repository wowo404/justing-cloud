package org.liu.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.admin.feign.pojo.Post;

public interface PostService extends IService<Post> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit();

    void delete(Long[] ids);
}

