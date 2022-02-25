package org.liu.user.service;

import lombok.RequiredArgsConstructor;
import org.liu.user.feign.pojo.BuyingBehaviorStatisticsReq;
import org.liu.user.feign.pojo.OperateAccountReq;
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
