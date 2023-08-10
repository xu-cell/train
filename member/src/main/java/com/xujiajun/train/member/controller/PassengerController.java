package com.xujiajun.train.member.controller;

import com.xujiajun.train.common.resp.CommonResp;
import com.xujiajun.train.member.req.PassengerSaveReq;
import com.xujiajun.train.member.service.PassengerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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



}
