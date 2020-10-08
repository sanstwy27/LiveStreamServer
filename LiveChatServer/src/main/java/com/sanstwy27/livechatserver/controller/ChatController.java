package com.sanstwy27.livechatserver.controller;

import com.github.javafaker.Faker;
import com.sanstwy27.livechatserver.dao.StatMemoDao;
import com.sanstwy27.livechatserver.dao.UserMemoDao;
import com.sanstwy27.livechatserver.entity.MsgEntity;
import com.sanstwy27.livechatserver.entity.UserEntity;
import com.sanstwy27.livechatserver.util.IpUtil;
import com.sanstwy27.livechatserver.util.UserAgentUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */

@RestController
public class ChatController {

    @Autowired
    private UserMemoDao userDao;

    @Autowired
    private StatMemoDao statDao;

    @RequestMapping(value = "/live_room", method = RequestMethod.GET)
    public String auth(HttpServletRequest request, Model model) {
        String ip = IpUtil.getIp(request);
        Random random = new Random(20);
        HttpSession session = request.getSession();
        UserEntity user;
        if (userDao.findOne(ip) != null) {
            // visited
            user = userDao.findOne(ip);
        } else {
            // not visited
            user = new UserEntity();
            user.setIp(ip);
            Faker faker = new Faker();
            user.setName(faker.name().firstName() + " " + faker.name().lastName());
            userDao.save(user);
        }

        session.setAttribute("user", user);
        if (UserAgentUtil.JudgeIsMoblie(request)) {
            return "live_m";
        } else {
            model.addAttribute("online_guests", getOnlineUser());
            model.addAttribute("history_guests", getHistoryGuests());
            return "live";
        }
    }

    @RequestMapping(value = "/online_guests", method = RequestMethod.GET)
    @ResponseBody
    public Set getOnlineUser() {
        return statDao.getAllUserOnline();
    }

    @RequestMapping(value = "/history_guests", method = RequestMethod.GET)
    @ResponseBody
    public List getHistoryGuests() {
        List l = new ArrayList();
        l.addAll(statDao.getGuestHistoryByQueue());
        return l;
    }

    @MessageMapping(value = "/chat")
    @SendTo("/topic/group")
    public MsgEntity testWst(String message, @Header(value = "simpSessionAttributes") Map<String, Object> session) {
        UserEntity user = (UserEntity) session.get("user");
        String username = user.getName();
        MsgEntity msg = new MsgEntity();
        msg.setCreator(username);
        msg.setsTime(Calendar.getInstance());
        msg.setMsgBody(message);
        System.out.println("Receive message: " + message);
        return msg;
    }
}
