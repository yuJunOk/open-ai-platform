package com.example.interfacemock.controller;

import com.example.multicommon.pojo.domain.UserDo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.*;

/**
 * @author pengYuJun
 */
@RestController
@RequestMapping("/name")
public class NameController {

    @GetMapping("/get")
    public String getNameByGet(String name, HttpServletRequest request) {
        //System.out.println(request.getHeader("yujun"));
        return "GET 你的名字是" + name;
    }

    @PostMapping("/post")
    public String getNameByPost(@RequestParam String name) {
        return "POST 你的名字是" + name;
    }

    @PostMapping("/user")
    public String getUsernameByPost(@RequestBody UserDo user, HttpServletRequest request) {
//        String accessKey = request.getHeader("accessKey");
//        String nonce = request.getHeader("nonce");
//        String timestamp = request.getHeader("timestamp");
//        String sign = request.getHeader("sign");
//        String body = request.getHeader("body");
//        // todo 实际情况应该是去数据库中查是否已分配给用户
//        if (!accessKey.equals("yupi")) {
//            throw new RuntimeException("无权限");
//        }
//        if (Long.parseLong(nonce) > 10000) {
//            throw new RuntimeException("无权限");
//        }
        // todo 时间和当前时间不能超过 5 分钟
//        if (timestamp) {
//
//        }
        // todo 实际情况中是从数据库中查出 secretKey
//        String serverSign = SignUtils.genSign(body, "abcdefgh");
//        if (!sign.equals(serverSign)) {
//            throw new RuntimeException("无权限");
//        }
        // todo 调用次数 + 1 invokeCount
        return "POST 用户名字是" + user.getUserName();
    }
}
