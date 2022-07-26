package org.liu.wx.service;

import lombok.RequiredArgsConstructor;
import org.liu.wx.feign.pojo.req.BuyingBehaviorStatisticsReq;
import org.liu.wx.feign.pojo.req.OperateAccountReq;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    public void addBuyingBehaviorStatistics(BuyingBehaviorStatisticsReq req) {

    }

    public void operateAccount(OperateAccountReq req) {

    }
}
