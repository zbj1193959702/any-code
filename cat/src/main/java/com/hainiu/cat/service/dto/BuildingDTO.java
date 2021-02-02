package com.hainiu.cat.service.dto;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.hainiu.cat.dao.model.Building;

import java.io.Serializable;

/**
 * create by biji.zhao on 2020/12/24
 */
public class BuildingDTO implements Serializable {

    private Integer id;
    private String name;
    private Integer projectId;
    private Double floorHeight;
    private Integer floorCount;
    private String paths;

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

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public Double getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(Double floorHeight) {
        this.floorHeight = floorHeight;
    }

    public Integer getFloorCount() {
        return floorCount;
    }

    public void setFloorCount(Integer floorCount) {
        this.floorCount = floorCount;
    }

    public String getPaths() {
        return paths;
    }

    public void setPaths(String paths) {
        this.paths = paths;
    }

    public static BuildingDTO toDTO(Building building) {
        if (building == null) {
            return null;
        }
        BuildingDTO buildingDTO = new BuildingDTO();
        buildingDTO.setId(building.getId());
        buildingDTO.setName(building.getName());
        buildingDTO.setProjectId(building.getProjectId());
        buildingDTO.setFloorHeight(building.getFloorHeight());
        buildingDTO.setFloorCount(building.getFloorCount());
        buildingDTO.setPaths(building.getPaths());
        return buildingDTO;
    }
}

