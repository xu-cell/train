package com.xujiajun.train.member.service;

import cn.hutool.core.collection.CollUtil;
import com.xujiajun.train.member.domain.Member;
import com.xujiajun.train.member.domain.MemberExample;
import com.xujiajun.train.member.mapper.MemberMapper;
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
    public Integer count() {
        return Math.toIntExact(memberMapper.countByExample(null));
    }

    public Long register(String mobile) {
        MemberExample memberExample = new MemberExample();
        memberExample.createCriteria().andMobileEqualTo(mobile);

        List<Member> list =  memberMapper.selectByExample(memberExample);
        if(CollUtil.isNotEmpty(list)) {
             throw new RuntimeException("手机号已经被注册了");
        }

        Member member = new Member();
        member.setId(System.currentTimeMillis());
        member.setMobile(mobile);
        memberMapper.insert(member);
        return member.getId();
    }
}
