package com.xmy.demophoto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.demophoto.entity.MUser;
import com.xmy.demophoto.mapper.MUserMapper;
import com.xmy.demophoto.service.MUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
public class MUserServiceImpl extends ServiceImpl<MUserMapper, MUser> implements MUserService {

    @Autowired
    MUserMapper mUserMapper;

    @Override
    public int deleteById(@Param("id") Integer id) {
        return mUserMapper.deleteById(id);
    }

    @Override
    public int insert(MUser photographers) {
        return mUserMapper.insert(photographers);
    }

    @Override
    public int updateUser(HashMap<String, Object> map) {
        return mUserMapper.updateUser(map);
    }

    @Override
    public MUser getUserById(@Param("id") Integer id) {
        return mUserMapper.getUserById(id);
    }
}
