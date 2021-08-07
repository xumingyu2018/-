package com.xmy.demophoto.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.demophoto.entity.Goods;

import java.util.HashMap;

/**
 *  服务类
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
public interface GoodsService extends IService<Goods> {

    int deleteWorkById(Integer id);

    int insert(Goods goods);

    Goods getWorkById(Integer id);

    int updateWork(HashMap<String, Object> map);
}
