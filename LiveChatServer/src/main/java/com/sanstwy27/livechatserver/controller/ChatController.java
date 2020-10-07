package com.sanstwy27.livechatserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */

@RestController
public class ChatController {

    @GetMapping("/hello")
    public String hello() {
        return "12321321321";
    }
}
