package com.xmy.demophoto.controller.back;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.MUser;
import com.xmy.demophoto.service.MUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


@Api(description = "摄影师管理")
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class UserController {

    @Autowired
    MUserService mUserService;

    @ApiOperation(value = "查询所有摄影师")
    @GetMapping("/photographer_table")
    public String photographerTable(Model model){
        List<MUser> list = mUserService.list(Wrappers.lambdaQuery(MUser.class).eq(MUser::getUsertype, 2));
        model.addAttribute("photographers",list);
        return "table/photographer_table";
    }

    @ApiOperation(value = "删除摄影师")
    @GetMapping("/photographer/delete/{id}")
    public String deletePhotographer(@PathVariable("id") Integer id){
        mUserService.deleteById(id);
        return "redirect:/photographer_table";
    }

    @ApiOperation(value = "添加摄影师")
    @PostMapping("/photographer/insert")
    public String addPhotographer(MUser photographer){
        photographer.setUsertype(2);
        mUserService.insert(photographer);
        return "redirect:/photographer_table";
    }

    @ApiOperation(value = "查询要修改的摄影师")
    @GetMapping("/photographer/toUpdate/{id}")
    public String toUpdatePhotographer(@PathVariable("id")Integer id,Model model){
        MUser photographer = mUserService.getUserById(id);
        model.addAttribute("QResult",photographer);
        return "table/updatePhotographer_table";
    }

    @ApiOperation(value = "根据ID修改摄影师")
    @PostMapping("/photographer/update")
    public String updatePhotographer(@RequestParam HashMap<String,Object> map){
        mUserService.updateUser(map);
        return "redirect:/photographer_table";
    }

    @ApiOperation(value = "查询所有用户")
    @GetMapping("/user_table")
    public String userTable(Model model){
        List<MUser> list = mUserService.list(Wrappers.lambdaQuery(MUser.class).eq(MUser::getUsertype, 1));
        model.addAttribute("users",list);
        return "table/user_table";
    }

    @ApiOperation(value = "删除用户")
    @GetMapping("/user/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        mUserService.deleteById(id);
        return "redirect:/user_table";
    }

    @ApiOperation(value = "用户插入")
    @PostMapping("/user/insert")
    public String addUser(MUser users){
        users.setUsertype(1);
        mUserService.insert(users);
        return "redirect:/user_table";
    }

    @ApiOperation(value = "查询要修改的用户")
    @GetMapping("/user/toUpdate/{id}")
    public String toUpdateUser(@PathVariable("id")Integer id,Model model){
        MUser user = mUserService.getUserById(id);
        model.addAttribute("QResult",user);
        return "table/updateUser_table";
    }

    @ApiOperation(value = "根据ID修改用户")
    @PostMapping("/user/update")
    public String updateUser(@RequestParam HashMap<String,Object> map){
        mUserService.updateUser(map);
        return "redirect:/user_table";
    }

}
