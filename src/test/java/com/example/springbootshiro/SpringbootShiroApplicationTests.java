package com.example.springbootshiro;

import com.example.springbootshiro.pojo.User;
import com.example.springbootshiro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroApplicationTests {
    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        User user = userService.queryUserByName("杨剑");
        System.out.println(user);
    }

}
