package com.xujiajun.train.member.controller;

import com.xujiajun.train.common.resp.CommonResp;
import com.xujiajun.train.member.req.MemberRegisterReq;
import com.xujiajun.train.member.req.MemberSendCodeReq;
import com.xujiajun.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xujj
 */
@RestController
@RequestMapping("/member")
public class MemberController {
    @Autowired
    private MemberService memberService;

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        return new CommonResp<>(memberService.count());
    }

    @PostMapping("/register")
    public CommonResp<Long>register(@Valid MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    @PostMapping("/send-code")
    public CommonResp<Long>sendCode(@Valid MemberSendCodeReq req) {
         memberService.sendCode(req);
         return new CommonResp<>();
    }


}
