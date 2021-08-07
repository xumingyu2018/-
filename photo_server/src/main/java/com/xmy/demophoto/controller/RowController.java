package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.Row;
import com.xmy.demophoto.service.RowService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 *  控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class RowController {

    @Autowired
    private RowService rowService;

    /**
     * 添加
     */
    @PostMapping("/addrow")
    @ApiOperation("添加")
    public boolean addrow(@RequestBody Row row) {
        return rowService.save(row);
    }

    /**
     * 修改
     */
    @PostMapping("/updateRowById")
    @ApiOperation("修改")
    public boolean updateRowById(@RequestBody Row row) {
        return rowService.updateById(row);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteRowById/{id}")
    @ApiOperation("删除")
    public boolean deleteRowById(@PathVariable("id") Long id) {
        return rowService.removeById(id);
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/getwshrow")
    @ApiOperation("详情")
    public List<Row> getRow() {
        return rowService.list(Wrappers.lambdaQuery(Row.class).eq(Row::getPrCollectionNum, 1));
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/queryRowByGoodsId")
    @ApiOperation("详情")
    public List<Row> queryRowByGoodsId(@RequestBody Map map) {
        return rowService.list(Wrappers.lambdaQuery(Row.class).eq(Row::getId, map.get("Id")));
    }

}
