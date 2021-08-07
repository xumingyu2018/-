package com.xmy.demophoto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.demophoto.entity.Carts;
import com.xmy.demophoto.mapper.CartsMapper;
import com.xmy.demophoto.service.CartsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *  服务实现类
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@Service
public class CartsServiceImpl extends ServiceImpl<CartsMapper, Carts> implements CartsService {

}
