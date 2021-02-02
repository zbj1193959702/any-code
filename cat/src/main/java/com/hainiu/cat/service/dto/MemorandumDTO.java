package com.hainiu.cat.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * create by biji.zhao on 2020/12/31
 */
public class MemorandumDTO implements Serializable {

    private Integer id;

    private String title;

    private List<MemorandumContentDTO> contentList;

    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<MemorandumContentDTO> getContentList() {
        return contentList;
    }

    public void setContentList(List<MemorandumContentDTO> contentList) {
        this.contentList = contentList;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
