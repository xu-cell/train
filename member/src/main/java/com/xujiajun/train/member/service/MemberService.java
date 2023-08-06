package com.xujiajun.train.member.service;

import com.xujiajun.train.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
