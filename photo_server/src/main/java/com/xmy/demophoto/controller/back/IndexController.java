package com.xmy.demophoto.controller.back;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xmy.demophoto.entity.Admin;
import com.xmy.demophoto.mapper.back.AdminMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Api(description = "登录主页" )
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class IndexController {

    @Autowired
    AdminMapper adminMapper;

//    登录页
    @GetMapping(value = {"/","/login"})
    public String loginPage(){
        return "login";
    }

    @PostMapping("/login")
    public String main(Admin admin, HttpSession session, Model model){
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username",admin.getUsername());
        wrapper.eq("password",admin.getPassword());

        Admin selectOne = adminMapper.selectOne(wrapper);

        if (selectOne==null){
            model.addAttribute("msg","账户密码错误");
            return "login";
        }else {
            session.setAttribute("loginAdmin",admin);
            String name = selectOne.getName();
            model.addAttribute("Name",name);
            return "main";
        }
    }

//    main页面
    @GetMapping("/main.html")
    public String mainPage(HttpSession session,Model model){
        Object loginUser = session.getAttribute("loginAdmin");
        if (loginUser!=null){
            return "main";
        }else{
            model.addAttribute("msg","请重新登录");
            return "login";
        }
    }

    @GetMapping(value = {"/register.html"})
    public String registerPage(){
        return "register";
    }

    @GetMapping(value = {"/back_up"})
    public String backupTable(){
        return "table/back_up";
    }

    @GetMapping(value = {"/restore"})
    public String restoreTable(){
        return "table/restore";
    }

    @ApiOperation(value = "备份数据库")
    @PostMapping("/backupSql")
    public String backup(){
        try {
            Runtime.getRuntime().exec("cmd /c D:\\MySQL\\mysql-8.0.24-winx64\\bin\\mysqldump -h localhost -uroot -p123456 yuepai > D:\\backup.sql");
            System.out.println("结束备份");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/back_up";
    }

    @ApiOperation(value = "还原数据库")
    @PostMapping("/restoreSql")
    public String restore(){
        try {
            Runtime.getRuntime().exec("cmd /c D:\\MySQL\\mysql-8.0.24-winx64\\bin\\mysql -uroot -p123456 restore < d:\\backup.sql");
            System.out.println("结束还原");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("还原数据库失败");
        }
        return "redirect:/restore";
    }

}
