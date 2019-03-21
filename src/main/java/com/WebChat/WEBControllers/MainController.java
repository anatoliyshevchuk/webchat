package com.WebChat.WEBControllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@SessionAttributes("user")
@Scope("session")
public class MainController {

    @GetMapping("/")
    private String MainPage() {
             return "loginPage";
    }

}
