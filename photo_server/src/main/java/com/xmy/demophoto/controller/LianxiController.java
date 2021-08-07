package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.Chatinfo;
import com.xmy.demophoto.entity.Lianxi;
import com.xmy.demophoto.service.LianxiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 联系控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class LianxiController {

    @Autowired
    private LianxiService lianxiService;

    /**
     * 添加
     */
    @PostMapping("/saveLianxi")
    @ApiOperation("添加")
    public boolean saveLianxi(@RequestBody Lianxi lianxi) {
        return lianxiService.save(lianxi);
    }

    /**
     * 修改
     */
    @PostMapping("/updateLianxiById")
    @ApiOperation("修改")
    public boolean updateLianxiById(@RequestBody Lianxi lianxi) {
        return lianxiService.updateById(lianxi);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteLianxiById/{id}")
    @ApiOperation("删除")
    public boolean deleteLianxiById(@PathVariable("id") Long id) {
        return lianxiService.removeById(id);
    }

    /**
     * 获取详情
     *
     * @return
     */
    @PostMapping("/getmychatinfo")
    @ApiOperation("详情")
    public List<Lianxi> getmychatinfo(@RequestBody Chatinfo chatinfo) {
        return lianxiService.list(Wrappers.lambdaQuery(Lianxi.class).eq(Lianxi::getMyid, chatinfo.getSendid()));
    }

}
