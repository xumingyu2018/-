package com.xmy.demophoto.controller;

import com.xmy.demophoto.entity.Orderdetail;
import com.xmy.demophoto.service.OrderdetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  订单详情控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class OrderdetailController {

    @Autowired
    private OrderdetailService orderdetailService;

    /**
     * 添加
     */
    @PostMapping("/saveOrderdetail")
    @ApiOperation("添加")
    public boolean saveOrderdetail(@RequestBody Orderdetail orderdetail) {
        return orderdetailService.save(orderdetail);
    }

    /**
     * 修改
     */
    @PostMapping("/updateOrderdetailById")
    @ApiOperation("修改")
    public boolean updateOrderdetailById(@RequestBody Orderdetail orderdetail) {
        return orderdetailService.updateById(orderdetail);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteOrderdetailById/{id}")
    @ApiOperation("删除")
    public boolean deleteOrderdetailById(@PathVariable("id") Long id) {
        return orderdetailService.removeById(id);
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/getOrderdetailById/{id}")
    @ApiOperation("详情")
    public Orderdetail getOrderdetailById(@PathVariable("id") Long id) {
        Orderdetail orderdetail = orderdetailService.getById(id);
        return orderdetail;
    }

}
