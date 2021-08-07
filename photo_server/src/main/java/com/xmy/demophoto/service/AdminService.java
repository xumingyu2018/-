package com.xmy.demophoto.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.demophoto.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

public interface AdminService extends IService<Admin> {

    int deleteAdminById(@Param("id") Integer id);

    int insert(Admin admin);

    Admin getAdminById(@Param("id") Integer id);

    int updateAdmin(HashMap<String, Object> map);


}
