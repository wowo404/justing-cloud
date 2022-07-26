package org.liu.product.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.liu.product.feign.pojo.req.EditStockReq;
import org.liu.product.mapper.StockMapper;
import org.liu.product.feign.pojo.po.Stock;
import org.liu.product.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Transactional(rollbackFor = Exception.class)
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {
    @Override
    public Page<Long> pageList() {
        return null;
    }

    @Override
    public Long detail(Long id) {
        return null;
    }

    @Override
    public Long add() {
        return null;
    }

    @Override
    public void edit(EditStockReq req) {
        super.update(Wrappers.lambdaUpdate(Stock.class).setSql("stock = stock + " + req.getStock()).eq(Stock::getSkuId, req.getSkuId()));
    }

    @Override
    public void delete(Long[] ids) {
    }
}

