package com.sanstwy27.livechatserver.dao;

import com.sanstwy27.livechatserver.entity.Guest;
import com.sanstwy27.livechatserver.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */
@Repository
public class StatMemoDao implements IStat {

    Set<UserEntity> onlineUsers = new ConcurrentSkipListSet<UserEntity>();
    Queue<Guest> onlineGuests = new ConcurrentLinkedQueue<Guest>();

    @Override
    public void pushOnlineUser(UserEntity userEntity) {
        System.out.println(userEntity);
        onlineUsers.add(userEntity);
    }

    @Override
    public void popOnlineUser(UserEntity userEntity) {
        onlineUsers.remove(userEntity);
    }

    @Override
    public Set getAllUserOnline() {
        return onlineUsers;
    }

    @Override
    public void pushGuestHistory(Guest guest) {
        if(onlineGuests.size() >= 2000l) {
            onlineGuests.remove();
        }
        onlineGuests.add(guest);
    }

    @Override
    public List getGuestHistory() {
        return null;
    }

    @Override
    public Queue getGuestHistoryByQueue() {
        return onlineGuests;
    }
}
