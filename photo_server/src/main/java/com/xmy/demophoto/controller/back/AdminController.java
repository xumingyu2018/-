package com.xmy.demophoto.controller.back;

import com.xmy.demophoto.entity.Admin;
import com.xmy.demophoto.mapper.back.AdminMapper;
import com.xmy.demophoto.service.AdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

@Api(description = "管理员设置")
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    AdminMapper adminMapper;

    @ApiOperation(value = "查询所有管理员")
    @GetMapping("/admin_table")
    public String adminTable(Model model){
        List<Admin> list = adminService.list();
        model.addAttribute("admins",list);
        return "table/admin_table";
    }

    @ApiOperation(value = "删除管理员")
    @GetMapping("/admin/delete/{id}")
    public String deleteAdmin(@PathVariable("id") Integer id){
        adminService.deleteAdminById(id);
        return "redirect:/admin_table";
    }

    @ApiOperation(value = "添加管理员")
    @PostMapping("/admin/insert")
    public String addAdmin(Admin admin){
        adminService.insert(admin);
        return "redirect:/admin_table";
    }

    @ApiOperation(value = "查询要修改的管理员")
    @GetMapping("/admin/toUpdate/{id}")
    public String toUpdateAdmin(@PathVariable("id")Integer id,Model model){
        Admin admin = adminService.getAdminById(id);
        model.addAttribute("QResult",admin);
        return "table/updateAdmin_table";
    }

    @ApiOperation(value = "根据ID修改管理员")
    @PostMapping("/admin/update")
    public String updateAdmin(@RequestParam HashMap<String,Object> map){
        adminService.updateAdmin(map);
        return "redirect:/admin_table";
    }

    @ApiOperation(value = "注册管理员")
    @GetMapping("/register")
    public String register(Admin admin, Model model, HttpServletRequest req){
        if (req.getParameter("password").equals(req.getParameter("ack"))){
            adminMapper.register(admin);
            return "redirect:/login";
        }else {
            model.addAttribute("ack","输入的密码不一致，请重新输入");
            return "register";
        }
    }

}
