package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author GuoJingyuan
 * @date 2019/10/9
 */
@Controller
//@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public String userList(Model model) {
        List<UserInfo> userInfos = userService.findAll();
        model.addAttribute("users", userInfos);
        return "userInfo";
    }

    @RequestMapping(value = "/toEdit", method = RequestMethod.GET)
    public String toEdit(Model model, @RequestParam Integer id) {
        UserInfo userInfo = userService.findById(id);
        model.addAttribute("user", userInfo);
        return "userInfoEdit";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String edit(HttpServletRequest request, String name, Integer uid, String password) {
        userService.editUser(uid, name, password);
        return "redirect:/list";
    }
}
