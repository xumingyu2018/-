package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.Goods;
import com.xmy.demophoto.service.GoodsService;
import com.xmy.demophoto.service.MUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * 作品控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @Autowired
    private MUserService mUserService;

    /**
     * 添加
     */
    @PostMapping("/saveGoods")
    @ApiOperation("添加")
    public boolean saveGoods(@RequestBody Goods goods) {
        return goodsService.save(goods);
    }

    /**
     * 修改
     */
    @PostMapping("/updateGoodsById")
    @ApiOperation("修改")
    public boolean updateGoodsById(@RequestBody Goods goods) {
        return goodsService.updateById(goods);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteGoodsById/{id}")
    @ApiOperation("删除")
    public boolean deleteGoodsById(@PathVariable("id") Long id) {
        return goodsService.removeById(id);
    }

    /**
     * 获取详情
     *
     * @return
     */
    @PostMapping("/getGoods")
    @ApiOperation("详情")
    public List<Goods> getGoods() {
        return goodsService.list();
    }

    /**
     * 获取详情
     *
     * @return
     */
    @PostMapping("/getGoodsById")
    @ApiOperation("详情")
    public List<Goods> getGoodsById(@RequestBody Goods goods) {
        Goods byId = goodsService.getById(goods.getId());
        return Arrays.asList(byId.setUserinfo(mUserService.getById(byId.getWish())));
    }
    /**
     * 获取详情
     *
     * @return
     */
    @PostMapping("/getzuopingbyuserid")
    @ApiOperation("详情")
    public List<Goods> getzuopingbyuserid(@RequestBody Goods goods) {
        return goodsService.list(Wrappers.lambdaQuery(Goods.class).eq(Goods::getWish, goods.getWish()));
    }
    /**
     * 获取详情
     *
     * @return
     */
    @PostMapping("/queryGoodsByName")
    @ApiOperation("详情")
    public List<Goods> queryGoodsByName(@RequestBody Goods goods) {
        return goodsService.list(Wrappers.lambdaQuery(Goods.class).like(Goods::getName, goods.getName()));
    }

}
