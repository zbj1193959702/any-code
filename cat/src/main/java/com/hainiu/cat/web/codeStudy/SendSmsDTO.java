package com.hainiu.cat.web.codeStudy;

import java.io.Serializable;

/**
 * create by biji.zhao on 2020/12/9
 */
public class SendSmsDTO implements Serializable {
    private String phone;
    private String content;
    private Integer primaryChannelId;
    private String sign;
    private Integer sendChannel;
    private AliYunSmsDTO aliYunSms;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getPrimaryChannelId() {
        return primaryChannelId;
    }

    public void setPrimaryChannelId(Integer primaryChannelId) {
        this.primaryChannelId = primaryChannelId;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Integer getSendChannel() {
        return sendChannel;
    }

    public void setSendChannel(Integer sendChannel) {
        this.sendChannel = sendChannel;
    }

    public AliYunSmsDTO getAliYunSms() {
        return aliYunSms;
    }

    public void setAliYunSms(AliYunSmsDTO aliYunSms) {
        this.aliYunSms = aliYunSms;
    }
}
