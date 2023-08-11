package com.xujiajun.train.member.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xujiajun.train.common.context.LoginMemberContext;
import com.xujiajun.train.common.resp.PageResp;
import com.xujiajun.train.common.util.SnowUtil;
import com.xujiajun.train.member.domain.Passenger;
import com.xujiajun.train.member.domain.PassengerExample;
import com.xujiajun.train.member.mapper.PassengerMapper;
import com.xujiajun.train.member.req.PassengerQueryReq;
import com.xujiajun.train.member.req.PassengerSaveReq;
import com.xujiajun.train.member.resp.PassengerQueryResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xujj
 */
@Service
public class PassengerService {
    @Autowired
    private PassengerMapper passengerMapper;

    public  void deleteById(Long id) {
        passengerMapper.deleteByPrimaryKey(id);
    }

    public void save(PassengerSaveReq req) {

        DateTime now = new DateTime();
        Passenger passenger = BeanUtil.copyProperties(req, Passenger.class);
        if(ObjectUtil.isNull(req.getId())) {
            passenger.setMemberId(LoginMemberContext.getId());
            passenger.setId(SnowUtil.getSnowflakeNextId());
            passenger.setCreateTime(now);
            passenger.setUpdateTime(now);
            passengerMapper.insert(passenger);


        } {
            // 更新操作
            passenger.setUpdateTime(new DateTime());
            passengerMapper.updateByPrimaryKey(passenger);
        }



    }

    public PageResp<PassengerQueryResp> queryList(PassengerQueryReq req) {
        PassengerExample passengerExample = new PassengerExample();
        PassengerExample.Criteria criteria = passengerExample.createCriteria();
        // 控制台查询 会员号会不为为空
        if(ObjectUtil.isNotNull(req.getMemberId())) {
            criteria.andMemberIdEqualTo(req.getMemberId());
        }
        PageHelper.startPage(req.getPage(),req.getSize());
        List<Passenger> list = passengerMapper.selectByExample(passengerExample);
        PageInfo<Passenger> pageinfo = new PageInfo<>(list);

        List<PassengerQueryResp> passengerQueryResps = BeanUtil.copyToList(list, PassengerQueryResp.class);

        PageResp<PassengerQueryResp> query = new PageResp<>();
        query.setTotal(pageinfo.getTotal());
        query.setList(passengerQueryResps);


        return query;

    }
}
