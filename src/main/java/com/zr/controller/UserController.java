package com.zr.controller;

import com.zr.bean.PageInfo;
import com.zr.bean.Role;
import com.zr.bean.User;
import com.zr.dao.IUserDao;
import com.zr.service.IRoleService;
import com.zr.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IRoleService roleService;

    @RequestMapping("login.do")
    public ModelAndView login(User user,HttpSession session){
        int id = userService.login(user.getUsername(),user.getPassword());
        ModelAndView modelAndView=new ModelAndView();
        if(id != -1){
            List<Integer> roleIds=roleService.findRoleByUserId(id);
            session.setAttribute("user",user);
            session.setAttribute("roleIds",roleIds);
            modelAndView.setViewName("main");
        }
        else {
            modelAndView.setViewName("../failer");
        }
        return modelAndView;
    }

    @RequestMapping("findAll.do")
    public ModelAndView findAll(@RequestParam(defaultValue = "1") int currentPage, String username,
                                @RequestParam(defaultValue = "0") int type,
                                HttpSession session){
        if(type==1){
            session.setAttribute("searchname",username);
        }else if(type==0){
            username=(String) session.getAttribute("searchname");
        }else if(type==2){
            session.removeAttribute("searchname");
        }

        PageInfo<User> pageInfo = userService.findAll(currentPage,username);
        ModelAndView mv=new ModelAndView();
        mv.setViewName("user-list");
        mv.addObject("pageInfo",pageInfo);
        return mv;
    }

    @RequestMapping("deleteById.do")
    public String delete(int id){
        userService.deleteById(id);
        return "redirect:findAll.do";  //重定向
    }

    @RequestMapping("add.do")
    public String add(User user){
        userService.add(user);
        return "redirect:findAll.do";
    }

    @RequestMapping("toUpdate.do")
    public ModelAndView toUpdate(int id){
        User user=userService.selectUserById(id);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("user",user);
        modelAndView.setViewName("user-update");
        return modelAndView;
    }

    @RequestMapping("update.do")
    public String update(User user){
        userService.update(user);
        return "redirect:findAll.do";
    }
    @RequestMapping("logout.do")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "../login";
    }

    //传值方法：使用ModelAndView
    //当url处于这个时，由listCategory方法来处理请求
    @RequestMapping("toAddRole.do")
    public ModelAndView toAddRole(int id){
        //创建一个模型视图对象
        ModelAndView modelAndView = new ModelAndView();
        //获取到查询的数据
        List<Role> roleList = roleService.findNotRoleByUserId(id);
        //将数据放置到ModelAndView对象view中，第二个参数可以是任何java类型
        modelAndView.addObject("roles",roleList);
        //放入jsp路径
        modelAndView.setViewName("user-role-add");
        modelAndView.addObject("id",id);
        //返回ModelAndView对象
        return modelAndView;
    }

    @RequestMapping("addRole.do")
    public String addRole(String roleIds,String userId){
        String[] strs = roleIds.split(",");
        List<Integer> ids = new ArrayList<>();
        for(String s:strs){
            ids.add(Integer.parseInt(s));
        }

        roleService.addRole(ids,Integer.parseInt(userId));
        return "redirect:findAll.do";
    }
    @PostMapping("deleteAll.do")
    @ResponseBody
    public String deleteAll(String userList){
        String[] strings = userList.split(",");
        List<Integer> ids=new ArrayList<>();
        for(String s:strings){
            ids.add(Integer.parseInt(s));
        }
        userService.deleteAll(ids);
        return "";
    }

}
