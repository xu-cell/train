package com.xujiajun.train.member.controller;

import com.xujiajun.train.common.context.LoginMemberContext;
import com.xujiajun.train.common.resp.CommonResp;
import com.xujiajun.train.member.req.PassengerQueryReq;
import com.xujiajun.train.member.req.PassengerSaveReq;
import com.xujiajun.train.member.resp.PassengerQueryResp;
import com.xujiajun.train.member.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xujj
 */
@RestController
@RequestMapping("/passenger")
public class PassengerController {
    @Autowired
    private PassengerService passengerService;


    @PostMapping("/save")
    public CommonResp<Object> save(@RequestBody @Valid PassengerSaveReq req) {
         passengerService.save(req);
        return new CommonResp<>();
    }


    @GetMapping("/query-list")
    public CommonResp<List<PassengerQueryResp>> save( @Valid PassengerQueryReq req) {
        req.setMemberId(LoginMemberContext.getId());
        List<PassengerQueryResp> list = passengerService.queryLisst(req);
        return new CommonResp<>(list);
    }

}
