package com.sanstwy27.livechatserver.dao;

import com.sanstwy27.livechatserver.entity.Guest;
import com.sanstwy27.livechatserver.entity.UserEntity;

import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */

public interface IStat {
    void pushOnlineUser(UserEntity userEntity);
    void popOnlineUser(UserEntity userEntity);
    Set getAllUserOnline();
    void pushGuestHistory(Guest guest);
    List getGuestHistory();
    Queue getGuestHistoryByQueue();
}
