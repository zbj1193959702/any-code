package com.hainiu.cat.service.dto;

import java.io.Serializable;

/**
 * create by biji.zhao on 2020/12/31
 */
public class MemorandumContentDTO implements Serializable {
    private String desc;
    private String content;

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
