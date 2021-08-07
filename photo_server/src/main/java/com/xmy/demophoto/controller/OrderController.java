package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.Goods;
import com.xmy.demophoto.entity.MUser;
import com.xmy.demophoto.entity.Order;
import com.xmy.demophoto.entity.Orderdetail;
import com.xmy.demophoto.service.GoodsService;
import com.xmy.demophoto.service.OrderService;
import com.xmy.demophoto.service.OrderdetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 订单控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderdetailService orderdetailService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 添加
     *
     * @return
     */
    @PostMapping("/addOrder")
    @ApiOperation("添加")
    public Map addOrder(@RequestBody Map map) {
        Map goods = (Map) ((ArrayList) map.get("jsonparm")).get(0);
        int score = Integer.valueOf(goods.get("score").toString());
        int screen = Integer.valueOf(goods.get("screen").toString());
        String idstr = goods.get("id") + ",";
        int total = score * screen;
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String uuid1 = UUID.randomUUID().toString();
        Order order = new Order()
                .setId(UUID.randomUUID().toString())
                .setOrdernumber(uuid1)
                .setTotal(total)
                .setType(1)
                .setGoodsid(idstr)
                .setCreattime(createTime)
                .setInfo(goods.get("wish").toString())
                .setTakeuserid(Integer.valueOf(goods.get("takeuserid").toString()))
                .setOrdertype(Integer.valueOf(goods.get("ordertype").toString()));
        orderService.save(order);
        Orderdetail orderdetail = new Orderdetail()
                .setOrdernumber(uuid1)
                .setNumber(screen)
                .setGoodsid(Integer.valueOf(goods.get("id").toString()))
                .setScreen(screen)
                .setScore(score)
                .setPosterurl(goods.get("posterurl").toString())
                .setName(goods.get("name").toString());
        orderdetailService.save(orderdetail);
        Map res = new HashMap();
        res.put("id", UUID.randomUUID().toString());
        res.put("ordernumber", uuid1);
        res.put("total", total);
        res.put("type", 1);
        res.put("goodsid", idstr);
        res.put("creattime", createTime);
        res.put("info", goods.get("wish").toString());
        res.put("newOrderDetatil", Arrays.asList(orderdetail));
        return res;
    }

    /**
     * 修改
     */
    @PostMapping("/paymoney")
    @ApiOperation("修改")
    public boolean paymoney(@RequestBody Order order) {
        return orderService.update(Wrappers.lambdaUpdate(Order.class).set(Order::getOrdertype, order.getOrdertype()).eq(Order::getId, order.getId()));
    }

    /**
     * 删除
     */
    @PostMapping("/delOrder")
    @ApiOperation("删除")
    public boolean delOrder(@RequestBody Order order) {
        return orderService.removeById(order.getId());
    }

    /**
     * 获取详情
     *
     * @return
     */
    @PostMapping("/getOrderById/{id}")
    @ApiOperation("详情")
    public Order getOrderById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);
        return order;
    }

    /**
     * 获取详情
     *
     * @return
     */
    @PostMapping("/queryOrderByName")
    @ApiOperation("详情")
    public List<Order> queryOrderByName(@RequestBody MUser mUser) {
        List<Order> order = orderService.list(Wrappers.lambdaQuery(Order.class).eq(Order::getInfo, mUser.getUsername()));
        order.forEach(i -> {
            String goodsId = i.getGoodsid().split(",")[0];
            Goods byId = goodsService.getById(goodsId);
            i.setOrderDetail(Arrays.asList(byId));
        });
        return order;
    }

}
