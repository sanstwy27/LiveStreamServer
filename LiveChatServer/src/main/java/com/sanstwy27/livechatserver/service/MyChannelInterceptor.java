package com.sanstwy27.livechatserver.service;

import com.sanstwy27.livechatserver.dao.StatMemoDao;
import com.sanstwy27.livechatserver.entity.Guest;
import com.sanstwy27.livechatserver.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */

public class MyChannelInterceptor implements ChannelInterceptor {

    @Autowired
    private StatMemoDao statDao;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public boolean preReceive(MessageChannel channel) {
        return ChannelInterceptor.super.preReceive(channel);
    }

    @Override
    public Message<?> preSend(Message<?> message, MessageChannel channel) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        // check SUBSCRIBE
        if (StompCommand.SUBSCRIBE.equals(command)) {

            // if SUBSCRIBE topic/channel in database (simulated by Set here)
            Set<String> subedChannelInDB = new HashSet<>();
            subedChannelInDB.add("/topic/group");
            subedChannelInDB.add("/topic/online_user");

            if (subedChannelInDB.contains(accessor.getDestination())) {
                // valid
                return ChannelInterceptor.super.preSend(message, channel);
            } else {
                // invalid
                return null;
            }
        } else {
            return ChannelInterceptor.super.preSend(message, channel);
        }

    }

    @Override
    public void afterSendCompletion(Message<?> message, MessageChannel channel, boolean sent, Exception ex) {
        StompHeaderAccessor accessor = StompHeaderAccessor.wrap(message);
        StompCommand command = accessor.getCommand();

        if (StompCommand.CONNECT.equals(command)) {
            Map<String, UserEntity> map = (Map<String, UserEntity>) accessor.getHeader("simpSessionAttributes");
            UserEntity user = map.get("user");
            // is connected?
            if (user != null) {
                // could be stored by Set, Redis, etc.
                statDao.pushOnlineUser(user);
                Guest guest = new Guest();
                guest.setUserEntity(user);
                guest.setAccessTime(Calendar.getInstance().getTimeInMillis());
                statDao.pushGuestHistory(guest);
                // return online users
                this.simpMessagingTemplate.convertAndSend("/topic/online_user", statDao.getAllUserOnline());
            }

        }

        if (StompCommand.DISCONNECT.equals(command)) {
            Map<String, UserEntity> map = (Map<String, UserEntity>) accessor.getHeader("simpSessionAttributes");
            UserEntity user = map.get("user");
            if (user != null) {
                statDao.popOnlineUser(user);
                simpMessagingTemplate.convertAndSend("/topic/online_user", statDao.getAllUserOnline());
            }

        }

        ChannelInterceptor.super.afterSendCompletion(message, channel, sent, ex);
    }
}
