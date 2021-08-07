package com.xmy.demophoto.controller;

import com.xmy.demophoto.entity.Useradrress;
import com.xmy.demophoto.service.UseradrressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class UseradrressController {

    @Autowired
    private UseradrressService useradrressService;

    /**
     * 添加
     */
    @PostMapping("/saveUseradrress")
    @ApiOperation("添加")
    public boolean saveUseradrress(@RequestBody Useradrress useradrress) {
        return useradrressService.save(useradrress);
    }

    /**
     * 修改
     */
    @PostMapping("/updateUseradrressById")
    @ApiOperation("修改")
    public boolean updateUseradrressById(@RequestBody Useradrress useradrress) {
        return useradrressService.updateById(useradrress);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteUseradrressById/{id}")
    @ApiOperation("删除")
    public boolean deleteUseradrressById(@PathVariable("id") Long id) {
        return useradrressService.removeById(id);
    }

    /**
     * 获取详情
     *
     * @return
     */
    @PostMapping("/getUseradrressById/{id}")
    @ApiOperation("详情")
    public Useradrress getUseradrressById(@PathVariable("id") Long id) {
        Useradrress useradrress = useradrressService.getById(id);
        return useradrress;
    }

}
