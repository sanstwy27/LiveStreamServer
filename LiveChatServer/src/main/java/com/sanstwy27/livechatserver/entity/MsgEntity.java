package com.sanstwy27.livechatserver.entity;

import java.util.Calendar;

/**
 * @author Sanstwy27
 * @create 10/7/2020
 */

public class MsgEntity {
    private String creator;
    private String msgBody;
    private Calendar sTime;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getMsgBody() {
        return msgBody;
    }

    public void setMsgBody(String msgBody) {
        this.msgBody = msgBody;
    }

    public Calendar getsTime() {
        return sTime;
    }

    public void setsTime(Calendar sTime) {
        this.sTime = sTime;
    }

    @Override
    public String toString() {
        return "MsgEntity{" +
                "creator='" + creator + '\'' +
                ", msgBody='" + msgBody + '\'' +
                ", sTime=" + sTime +
                '}';
    }
}
