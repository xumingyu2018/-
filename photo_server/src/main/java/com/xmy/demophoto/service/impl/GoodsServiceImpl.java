package com.xmy.demophoto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.demophoto.entity.Goods;
import com.xmy.demophoto.mapper.GoodsMapper;
import com.xmy.demophoto.service.GoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 *  服务实现类
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    @Override
    public int deleteWorkById(Integer id) {
        return goodsMapper.deleteWorkById(id);
    }

    @Override
    public int insert(Goods goods) {
        return goodsMapper.insert(goods);
    }

    @Override
    public Goods getWorkById(Integer id) {
        return goodsMapper.getWorkById(id);
    }

    @Override
    public int updateWork(HashMap<String, Object> map) {
        return goodsMapper.updateWork(map);
    }

}
