package com.WebChat;
import com.WebChat.Entity.*;
import com.WebChat.Service.UserService;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class start {

    public start() {
    }
/*
      public static void main(String[] args) {

        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(ConfigBeansTests.class);
        annotationConfigApplicationContext.refresh();
        UserService us = annotationConfigApplicationContext.getBean(UserService.class);

        System.out.println(us.toString());
        List<Message> msg = us.getMessagesByUserId((long) 4);
        System.out.println(msg.size());
    }
*/
}