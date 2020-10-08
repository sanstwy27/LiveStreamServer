package com.sanstwy27.livechatserver.entity;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */

public class Guest {
    private UserEntity userEntity;
    private long accessTime;

    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    public long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(long accessTime) {
        this.accessTime = accessTime;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "userEntity=" + userEntity +
                ", accessTime=" + accessTime +
                '}';
    }
}
