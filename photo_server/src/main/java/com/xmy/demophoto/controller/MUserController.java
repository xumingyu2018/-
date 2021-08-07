package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.MUser;
import com.xmy.demophoto.service.MUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  用户控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class MUserController {

    @Autowired
    private MUserService mUserService;

    /**
     * 用户注册
     */
    @PostMapping("/regUser")
    @ApiOperation("添加")
    public boolean regUser(@RequestBody MUser mUser) {
        return mUserService.save(mUser.setUsertype(1));
    }

    /**
     * 修改用户信息
     * @return
     */
    @PostMapping("/updateUser")
    @ApiOperation("修改")
    public boolean updateUser(@RequestBody MUser mUser) {
        return mUserService.updateById(mUser);
    }

    /**
     * 删除信息
     */
    @PostMapping("/deleteMUserById/{id}")
    @ApiOperation("删除")
    public boolean deleteMUserById(@PathVariable("id") Long id) {
        return mUserService.removeById(id);
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/loginUser")
    @ApiOperation("详情")
    public List<MUser> loginUser(@RequestBody MUser mUser) {
        return mUserService.list(Wrappers.lambdaQuery(MUser.class).eq(MUser::getUsername, mUser.getUsername()).eq(MUser::getUserpwd, mUser.getUserpwd()));
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/getUserByid")
    @ApiOperation("详情")
    public List<MUser> getUserByid(@RequestBody MUser mUser) {
        return mUserService.list(Wrappers.lambdaQuery(MUser.class).eq(MUser::getId, mUser.getId()));
    }
    /**
     * 获取详情
     * @return
     */
    @PostMapping("/seachtakeperson")
    @ApiOperation("详情")
    public List<MUser> seachtakeperson(@RequestBody MUser mUser) {
        return mUserService.list(Wrappers.lambdaQuery(MUser.class).eq(MUser::getUsertype, 2).like(MUser::getUsername, mUser.getUsername()));
    }
    /**
     * 获取详情
     * @return
     */
    @PostMapping("/gettakeperson")
    @ApiOperation("详情")
    public List<MUser> gettakeperson() {
        return mUserService.list(Wrappers.lambdaQuery(MUser.class).eq(MUser::getUsertype, 2));
    }

}
