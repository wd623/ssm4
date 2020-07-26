package com.zr.service;

import com.zr.bean.PageInfo;
import com.zr.bean.User;

import java.util.List;

public interface IUserService {
    int login(String username,String password);

    PageInfo<User> findAll(int currentPage,String username);

    void deleteById(int id);

    void add(User user);

    User selectUserById(int id);

    void update(User user);

    void deleteAll(List<Integer> ids);
}
