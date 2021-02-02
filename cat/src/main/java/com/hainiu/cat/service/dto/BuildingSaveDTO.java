package com.hainiu.cat.service.dto;

import java.io.Serializable;

/**
 * create by biji.zhao on 2020/12/24
 */
public class BuildingSaveDTO implements Serializable {
    private Integer projectId;
    private String name;
    private String paths;
    private Integer floorCount;
    private Double floorHeight;

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
    }

    public Double getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(Double floorHeight) {
        this.floorHeight = floorHeight;
    }
}
