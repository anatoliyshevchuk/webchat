package com.WebChat.WEBControllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("user")
@Scope("session")
public class MainController {

    @GetMapping("/")
    private String MainPage() {
             return "loginPage";
    }

    @RequestMapping("/menu")
    private ModelAndView menu(ModelAndView modelAndView)
    {
        modelAndView.setViewName("main");
        return modelAndView;
    }

}
