package com.xujiajun.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.xujiajun.train.common.exception.BusinessException;
import com.xujiajun.train.common.exception.BusinessExceptionEnum;
import com.xujiajun.train.common.util.SnowUtil;
import com.xujiajun.train.member.domain.Member;
import com.xujiajun.train.member.domain.MemberExample;
import com.xujiajun.train.member.mapper.MemberMapper;
import com.xujiajun.train.member.req.MemberRegisterReq;
import com.xujiajun.train.member.req.MemberSendCodeReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xujj
 */
@Service
public class MemberService {
    @Autowired
    private MemberMapper memberMapper;

    private static final Logger LOG = LoggerFactory.getLogger(MemberService.class);
    public Integer count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public Long register(MemberRegisterReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        List<Member> list =  memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(list)) {
             throw new BusinessException(BusinessExceptionEnum.MEMBER_MOBILE_EXIST);
        }

        Member member = new Member();
        member.setId(SnowUtil.getSnowflakeNextId());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }

    public void sendCode(MemberSendCodeReq req) {
        String mobile = req.getMobile();
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);
        List<Member> list =  memberMapper.selectByExample(memberExample);
        if(CollUtil.isEmpty(list)) {
            LOG.info("手机号未注册，插入一条记录");
            Member member = new Member();
            member.setId(SnowUtil.getSnowflakeNextId());
            member.setMobile(mobile);
            memberMapper.insert(member);
        } else {
            LOG.info("手机号已经注册，未插入记录");
        }

        String code = "8888";
        LOG.info("生成验证码，{}",code);
        // 保存短信记录表：手机号，短信验证码，有效期，是否已使用，业务类型，发送时间，使用时间
        LOG.info("存储信息到短信表");

        LOG.info("对接短信平台,发送短信");

    }
}
