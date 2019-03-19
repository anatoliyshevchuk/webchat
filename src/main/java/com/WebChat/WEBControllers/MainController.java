package com.WebChat.WEBControllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @GetMapping("/")
    private String MainPage()
    {
             return "loginPage";
    }

    @RequestMapping(value="/sendMessage", method= RequestMethod.POST)
    private String sendMessage(@RequestParam("toUser")String userName,@RequestParam("message")String message)
    {
        
        return "main";
    }
}
