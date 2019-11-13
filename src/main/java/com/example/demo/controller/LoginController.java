package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author GuoJingyuan
 * @date 2019/9/26
 */
@Controller
public class LoginController {
    @RequestMapping({"/","/index"})
    public String index(){
        return"/index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(Model model, HttpServletRequest request, String account, String password) throws Exception{
        Map<String, String> tipsMap = new HashMap<>();
        try {
            if(password.equals("")||password==null){
                model.addAttribute("message","密码不能为空！");
                return "/login";
            }
            UsernamePasswordToken token = new UsernamePasswordToken();
            token.setUsername(account);
            token.setPassword(password.toCharArray());
            SecurityUtils.getSubject().login(token);
        } catch (Exception e) {
            e.printStackTrace();
//            model.addAttribute("message", "域登陆失败(" + (6 - ((int) loginErrorInfo.get("count"))) + ")次");
            model.addAttribute("msg", "用户名或者密码错误!");
            return "/login";
        }
        return "/index";
    }

    @RequestMapping("/403")
    public String unauthorizedRole(){
        System.out.println("------没有权限-------");
        return "403";
    }
}
