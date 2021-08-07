package com.xmy.demophoto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.demophoto.entity.Order;
import com.xmy.demophoto.mapper.OrderMapper;
import com.xmy.demophoto.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    OrderMapper orderMapper;

//    @Override
//    public int insert(Order order) {
//        return orderMapper.insert(order);
//    }

    @Override
    public Order getOrderById(String id){
        return orderMapper.getOrderById(id);
    }

    @Override
    public int deleteById(String id) {
        return orderMapper.deleteById(id);
    }

//    @Override
//    public int updateOrder(HashMap<String,Object> map){
//        return orderMapper.updateOrder(map);
//    }

}
