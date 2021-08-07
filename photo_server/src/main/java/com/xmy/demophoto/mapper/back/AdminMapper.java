package com.xmy.demophoto.mapper.back;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.demophoto.entity.Admin;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;

/**
 *  Mapper 接口
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

    @Delete("delete from admin where id=#{id}")
    int deleteById(@Param("id") Integer id);

    @Override
    @Insert("insert into admin(username,password,name,status) " +
            "values (#{username},#{password},#{name},#{status})")
    int insert(Admin admin);

    @Update("update admin set username=#{username},password=#{password},name=#{name},status=#{status} where id=#{id}")
    int updateAdmin(HashMap<String, Object> map);

    @Select("select * from admin where id=#{id}")
    Admin getAdminById(@Param("id") Integer id);

    @Insert("insert into admin(username,password,name,status) " +
            "values (#{username},#{password},#{name},'普通管理员')")
    int register(Admin admin);




}
