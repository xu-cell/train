package com.xujiajun.train.member.req;

import com.xujiajun.train.common.req.PageReq;

/**
 * @author xujj
 */
public class PassengerQueryReq extends PageReq {


    private Long memberId;

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }


    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PassengerQueryReq{");
        sb.append("memberId=").append(memberId);
        sb.append('}');
        return sb.toString();
    }
}