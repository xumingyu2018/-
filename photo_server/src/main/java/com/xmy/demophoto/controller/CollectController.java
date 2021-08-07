package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.Collect;
import com.xmy.demophoto.entity.Goods;
import com.xmy.demophoto.service.CollectService;
import com.xmy.demophoto.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  点赞控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class CollectController {

    @Autowired
    private CollectService collectService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 添加
     */
    @PostMapping("/addCollect")
    @ApiOperation("添加")
    public boolean addCollect(@RequestBody Collect collect) {
        Goods goods = goodsService.getById(collect.getGoodsid());
        goodsService.updateById(goods.setPlayinfo(goods.getPlayinfo() + 1));
        return collectService.save(collect);
    }

    /**
     * 修改
     */
    @PostMapping("/updateCollectById")
    @ApiOperation("修改")
    public boolean updateCollectById(@RequestBody Collect collect) {
        return collectService.updateById(collect);
    }

    /**
     * 删除
     */
    @PostMapping("/delCollect")
    @ApiOperation("删除")
    public boolean delCollect(@RequestBody Collect collect) {
        Goods goods = goodsService.getById(collect.getGoodsid());
        goodsService.updateById(goods.setPlayinfo(goods.getPlayinfo() - 1));
        return collectService.remove(Wrappers.lambdaQuery(Collect.class).eq(Collect::getUserid, collect.getUserid()).eq(Collect::getGoodsid, collect.getGoodsid()));
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/getCollectById/{id}")
    @ApiOperation("详情")
    public Collect getCollectById(@PathVariable("id") Long id) {
        Collect collect = collectService.getById(id);
        return collect;
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/queryCollectByGU")
    @ApiOperation("详情")
    public List<Collect> queryCollectByGU(@RequestBody Collect collect) {
        List<Collect> collects = collectService.list(Wrappers.lambdaQuery(Collect.class).eq(Collect::getUserid, collect.getUserid()).eq(Collect::getGoodsid, collect.getGoodsid()));
        return collects;
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/queryCollectByU")
    @ApiOperation("详情")
    public List<Collect> queryCollectByU(@RequestBody Collect collect) {
        List<Collect> collects = collectService.list(Wrappers.lambdaQuery(Collect.class).eq(Collect::getUserid, collect.getUserid()));
        collects.forEach(i -> {
            Goods byId = goodsService.getById(i.getGoodsid());
            i.setJindian(byId);
        });
        return collects;
    }

}
