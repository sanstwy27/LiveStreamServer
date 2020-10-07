package com.sanstwy27.server.info.config;

/**
 * @author Sanstwy27
 * @create 10/1/2020
 */

public class YmlTwitchUrl {

    private String getstreams;
    private String token;
    private String users;

    public String getGetstreams() {
        return getstreams;
    }

    public void setGetstreams(String getstreams) {
        this.getstreams = getstreams;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsers() {
        return users;
    }

    public void setUsers(String users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "YmlTwitchUrl{" +
                "getstreams='" + getstreams + '\'' +
                ", token='" + token + '\'' +
                ", users='" + users + '\'' +
                '}';
    }
}
