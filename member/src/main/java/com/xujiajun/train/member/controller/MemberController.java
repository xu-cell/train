package com.xujiajun.train.member.controller;

import com.xujiajun.train.common.resp.CommonResp;
import com.xujiajun.train.common.resp.MemberLoginResp;
import com.xujiajun.train.member.req.MemberLoginReq;
import com.xujiajun.train.member.req.MemberRegisterReq;
import com.xujiajun.train.member.req.MemberSendCodeReq;
import com.xujiajun.train.member.service.MemberService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public CommonResp<Long>register(@RequestBody @Valid MemberRegisterReq req) {
        long register = memberService.register(req);
        return new CommonResp<>(register);
    }

    @PostMapping("/send-code")
    public CommonResp<Long>sendCode(@RequestBody @Valid MemberSendCodeReq req) {
         memberService.sendCode(req);
         return new CommonResp<>();
    }

    @PostMapping("/login")
    public CommonResp<MemberLoginResp>login(@RequestBody @Valid MemberLoginReq req) {
        MemberLoginResp resp = memberService.login(req);
        return new CommonResp<>(resp);
    }


}
