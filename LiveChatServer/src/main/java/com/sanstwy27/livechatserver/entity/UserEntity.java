package com.sanstwy27.livechatserver.entity;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */
public class UserEntity implements Serializable, Comparable<UserEntity> {
    private String ip;
    private String name;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        int result = ip != null ? ip.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        UserEntity that = (UserEntity) obj;

        if (!Objects.equals(ip, that.ip)) return false;
        if (!Objects.equals(name, that.name)) return false;

        return true;
    }

    @Override
    public int compareTo(UserEntity o) {
        return this.equals(o) ? 0 : 1;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "ip='" + ip + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
