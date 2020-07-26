package com.zr.service.impl;

import com.zr.bean.PageInfo;
import com.zr.bean.User;
import com.zr.dao.IUserDao;
import com.zr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private IUserDao userDao;

    @Override
    public int login(String username, String password) {
        User user=userDao.findUserByUsername(username);
        if(user!=null&&user.getPassword().equals(password)){
            return user.getId();
        }
        else {
            return -1;  //如果是正数，代表不存在
        }

    }

    @Override
    public PageInfo<User> findAll(int currentPage,String username) {
        PageInfo pageInfo = new PageInfo();

        int totalCount = userDao.getTotalCount(username);
        pageInfo.setTotalCount(totalCount);
        double d=totalCount/5.0;
        int tp = (int) Math.ceil(d);
        pageInfo.setTotalPage(tp);
        pageInfo.setSize(5);
        if(currentPage<1){
            pageInfo.setCurrentPage(1);
        }else if(currentPage>tp){
            pageInfo.setCurrentPage(tp);
        }else {
            pageInfo.setCurrentPage(currentPage);
        }
        int start=(pageInfo.getCurrentPage()-1)*5;
        List<User> userList = userDao.findAll(start,5,username);
        pageInfo.setList(userList);
        return pageInfo;
    }

    @Override
    public void deleteById(int id) {
        userDao.deleteById(id);
    }

    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    public User selectUserById(int id) {
        return userDao.selectUserById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteAll(List<Integer> ids) {
        userDao.deleteAll(ids);
    }

}
