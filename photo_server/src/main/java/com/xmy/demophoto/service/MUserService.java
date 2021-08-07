package com.xmy.demophoto.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.demophoto.entity.MUser;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 *  服务类
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
public interface MUserService extends IService<MUser> {

    int deleteById(@Param("id") Integer id);

    int insert(MUser photographers);

    int updateUser(HashMap<String, Object> map);

    MUser getUserById(@Param("id") Integer id);
}
