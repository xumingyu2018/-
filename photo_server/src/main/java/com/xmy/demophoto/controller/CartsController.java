package com.xmy.demophoto.controller;

import com.xmy.demophoto.entity.Carts;
import com.xmy.demophoto.service.CartsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *  控制器(null)
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class CartsController {

    @Autowired
    private CartsService cartsService;

    /**
     * 添加
     */
    @PostMapping("/saveCarts")
    @ApiOperation("添加")
    public boolean saveCarts(@RequestBody Carts carts) {
        return cartsService.save(carts);
    }

    /**
     * 修改
     */
    @PostMapping("/updateCartsById")
    @ApiOperation("修改")
    public boolean updateCartsById(@RequestBody Carts carts) {
        return cartsService.updateById(carts);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteCartsById/{id}")
    @ApiOperation("删除")
    public boolean deleteCartsById(@PathVariable("id") Long id) {
        return cartsService.removeById(id);
    }

    /**
     * 获取详情
     */
    @PostMapping("/getCartsById/{id}")
    @ApiOperation("详情")
    public Carts getCartsById(@PathVariable("id") Long id) {
        Carts carts = cartsService.getById(id);
        return carts;
    }

}
