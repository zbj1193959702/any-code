package com.hainiu.cat.service.dto;

import java.io.Serializable;
import java.util.List;

/**
 * create by biji.zhao on 2020/12/24
 */
public class ProjectDTO implements Serializable {

    private Integer id;
    private String name;
    private Double lng;
    private Double lat;
    private List<BuildingDTO> buildingList;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public List<BuildingDTO> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<BuildingDTO> buildingList) {
        this.buildingList = buildingList;
    }
}
