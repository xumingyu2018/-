package com.xmy.demophoto.controller.back;

import com.xmy.demophoto.entity.Order;
import com.xmy.demophoto.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Api(description = "订单管理")
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class OrdersController {

    @Autowired
    OrderService orderService;

    @ApiOperation(value = "查询所有订单列表")
    @GetMapping("/order_table")
    public String orderTable(Model model){
        // list()查询所有(mybatis-plus的功能)
        List<Order> list = orderService.list();
        model.addAttribute("orders",list);
        return "table/order_table";
    }

    @ApiOperation(value = "订单删除")
    @GetMapping ("/order/delete/{id}")
    public String deletOrder(@PathVariable("id") String id){
        orderService.deleteById(id);
        return "redirect:/order_table";
    }

//    @ApiOperation(value = "订单插入")
//    @PostMapping("/order/insert")
//    public String addOrder(Order order){
//        order.setCreattime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
//        orderService.insert(order);
//        return "redirect:/order_table";
//    }

    @ApiOperation(value = "查询要修改的订单")
    @GetMapping("/order/toUpdate/{id}")
    public String toUpdateOrder(@PathVariable("id")String id,Model model){
        Order orders = orderService.getOrderById(id);
        model.addAttribute("QResult",orders);
        return "table/updateOrder_table";
    }

//    @ApiOperation(value = "根据ID修改的订单")
//    @PostMapping("/order/update")
//    public String updateOrder(@RequestParam HashMap<String,Object> map){
//        orderService.updateOrder(map);
//        return "redirect:/order_table";
//
//    }
}
