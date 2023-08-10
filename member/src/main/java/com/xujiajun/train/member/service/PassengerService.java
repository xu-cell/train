package com.xujiajun.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import com.xujiajun.train.common.util.SnowUtil;
import com.xujiajun.train.member.domain.Passenger;
import com.xujiajun.train.member.mapper.PassengerMapper;
import com.xujiajun.train.member.req.PassengerSaveReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author xujj
 */
@Service
public class PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;
    public void save(PassengerSaveReq req) {

        DateTime now = new DateTime();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        passenger.setId(SnowUtil.getSnowflakeNextId());
        passenger.setCreateTime(now);
        passenger.setUpdateTime(now);
        passengerMapper.insert(passenger);



    }
}