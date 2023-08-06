package com.xujiajun.train.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author xujj
 */
@Mapper
public interface MemberMapper {

    @Select("select * from member")
    Integer count();
}
