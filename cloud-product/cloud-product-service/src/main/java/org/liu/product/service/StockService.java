package org.liu.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.product.feign.pojo.req.EditStockReq;
import org.liu.product.feign.pojo.po.Stock;

public interface StockService extends IService<Stock> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit(EditStockReq req);

    void delete(Long[] ids);
}

