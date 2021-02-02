package com.hainiu.cat.service.dto;

import java.io.Serializable;

/**
 * create by biji.zhao on 2020/12/24
 */
public class ZhiHuHotDTO implements Serializable {
    private static final long serialVersionUID = -8207334497235887083L;
    private Integer id;
    private String title;
    private String content;
    private String hot;
    private String ranking;
    private String image;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHot() {
        return hot;
    }

    public void setHot(String hot) {
        this.hot = hot;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
