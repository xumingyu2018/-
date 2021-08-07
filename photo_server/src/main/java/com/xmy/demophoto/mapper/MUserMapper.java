package com.xmy.demophoto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.demophoto.entity.MUser;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;

/**
 *  Mapper 接口
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Mapper
public interface MUserMapper extends BaseMapper<MUser> {
    @Delete("delete from m_user where id=#{id}")
    int deleteById(@Param("id") Integer id);

    @Insert("insert into m_user(username,usersex,userage,useraddress,userpwd,usertype) " +
            "values (#{username},#{usersex},#{userage},#{useraddress},#{userpwd},#{usertype})")
    int insert(MUser photographers);

    @Update("update m_user set username=#{username},usersex=#{usersex},userage=#{userage},useraddress=#{useraddress},userpwd=#{userpwd} where id=#{id}")
    int updateUser(HashMap<String, Object> map);

    @Select("select * from m_user where id=#{id}")
    MUser getUserById(@Param("id") Integer id);
}
