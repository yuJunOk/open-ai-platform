package com.example.mps;

import cn.hutool.core.util.RandomUtil;
import com.example.mps.service.UserService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import static com.example.mps.constant.UserConstant.PASSWORD_SALT;

@SpringBootTest
class MultiProSystemApplicationTests {

    @Resource
    private UserService userService;

    @Test
    void contextLoads() {
        System.out.println(userService.getById(1));
    }

}
