package com.zr.service.impl;

import com.zr.bean.Role;
import com.zr.bean.UserRole;
import com.zr.dao.IRoleDao;
import com.zr.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Integer> findRoleByUserId(int id) {
        return roleDao.findRoleIdsByUserId(id);
    }

    @Override
    public List<Role> findNotRoleByUserId(int id) {
        return roleDao.findNotRoleByUserId(id);
    }

    @Override
    public void addRole(List<Integer> ids, int userId) {
        for(int i:ids){
            UserRole userRole=new UserRole();
            userRole.setRoleId(i);
            userRole.setUserId(userId);
        }
    }
}
