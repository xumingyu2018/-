package com.xmy.demophoto.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.demophoto.entity.Order;
import org.apache.ibatis.annotations.Param;

/**
 *  服务类
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
public interface OrderService extends IService<Order> {

    Order getOrderById(@Param("id") String id);

    int deleteById(@Param("id") String id);

//    int insert(Order order);
//
//    int updateOrder(HashMap<String,Object> map);
}
