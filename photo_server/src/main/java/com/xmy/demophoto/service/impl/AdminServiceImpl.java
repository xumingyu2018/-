package com.xmy.demophoto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.demophoto.entity.Admin;
import com.xmy.demophoto.mapper.back.AdminMapper;
import com.xmy.demophoto.service.AdminService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Service
@Transactional(rollbackFor=Exception.class)
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

    @Autowired
    AdminMapper adminMapper;

    @Override
    public int deleteAdminById(@Param("id") Integer id) {
        return adminMapper.deleteById(id);
    }

    @Override
    public int insert(Admin admin) {
        return adminMapper.insert(admin);
    }

    @Override
    public Admin getAdminById(@Param("id") Integer id) {
        return adminMapper.getAdminById(id);
    }

    @Override
    public int updateAdmin(HashMap<String, Object> map) {
        return adminMapper.updateAdmin(map);
    }

    //springsecurity认证权限
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        QueryWrapper<com.xmy.demophoto.entities.Admin> wrapper = new QueryWrapper<>();
//        wrapper.eq("username",username);
//        Admin admin = adminMapper.selectOne(wrapper);
//        if (admin==null){
//            System.out.println("用户名不存在");
//        }
//        //授予权限
//        List<GrantedAuthority> auths= AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
//        return new User(admin.getUsername(),new BCryptPasswordEncoder().encode(admin.getPassword()),auths);
//    }
}
