package org.liu.product.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.liu.product.feign.pojo.EditStockReq;
import org.liu.product.pojo.Stock;

public interface StockService extends IService<Stock> {
    Page<Long> pageList();

    Long detail(Long id);

    Long add();

    void edit(EditStockReq req);

    void delete(Long[] ids);
}

