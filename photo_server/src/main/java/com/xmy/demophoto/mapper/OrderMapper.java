package com.xmy.demophoto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.demophoto.entity.Order;
import org.apache.ibatis.annotations.*;

/**
 *  Mapper 接口
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {

    @Select("select * from orderinfo where id=#{id}")
    Order getOrderById(@Param("id") String id);

//    @Override
//    @Insert("insert into orderinfo(username,password,name,status) " +
//            "values (#{username},#{password},#{name},#{status})")
//    int insert(Order order);

    @Delete("delete from orderinfo where id=#{id}")
    int deleteById(@Param("id") String id);

//    @Update("update orderinfo set username=#{username},password=#{password},name=#{name},status=#{status} where id=#{id}")
//    int updateOrder(HashMap<String,Object> map);
}
