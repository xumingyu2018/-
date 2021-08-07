package com.xmy.demophoto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.demophoto.entity.Row;
import com.xmy.demophoto.mapper.RowMapper;
import com.xmy.demophoto.service.RowService;
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
public class RowServiceImpl extends ServiceImpl<RowMapper, Row> implements RowService {

}
