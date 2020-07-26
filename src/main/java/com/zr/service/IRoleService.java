package com.zr.service;

import com.zr.bean.Role;

import java.util.List;

public interface IRoleService {

    List<Integer> findRoleByUserId(int id);

    List<Role> findNotRoleByUserId(int id);

    void addRole(List<Integer> ids, int userId);
}
