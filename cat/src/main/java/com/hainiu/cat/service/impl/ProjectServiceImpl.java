package com.hainiu.cat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hainiu.cat.dao.mapper.BuildingMapper;
import com.hainiu.cat.dao.mapper.ProjectMapper;
import com.hainiu.cat.dao.model.Building;
import com.hainiu.cat.dao.model.Project;
import com.hainiu.cat.service.ProjectService;
import com.hainiu.cat.service.dto.BuildingDTO;
import com.hainiu.cat.service.dto.BuildingSaveDTO;
import com.hainiu.cat.service.dto.ProjectDTO;
import com.hainiu.cat.service.enums.CommonStatus;
import com.hainiu.cat.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * create by biji.zhao on 2020/11/11
 */
@Service
public class ProjectServiceImpl implements ProjectService {

    @Autowired
    private ProjectMapper projectMapper;

    @Autowired
    private BuildingMapper buildingMapper;


    @Override
    public Result<String> saveOne(BuildingSaveDTO saveDTO) {
        Project project = projectMapper.selectById(saveDTO.getProjectId());
        if (project == null) {
            return Result.errorInstance("项目不存在");
        }
        Building building = new Building();
        building.setProjectId(project.getId());
        building.setName(saveDTO.getName());
        building.setPaths(saveDTO.getPaths());
        building.setFloorCount(saveDTO.getFloorCount());
        building.setFloorHeight(saveDTO.getFloorHeight());
        building.setStatus(CommonStatus.no_delete.value);
        buildingMapper.insert(building);

        return Result.successInstance();
    }

    @Override
    public ProjectDTO queryByProjectId(Integer id) {
        if (id == null) {
            return null;
        }
        Project project = projectMapper.selectById(id);
        if (project == null) {
            return null;
        }
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(id);
        projectDTO.setName(project.getName());
        projectDTO.setLng(project.getLng());
        projectDTO.setLat(project.getLat());

        LambdaQueryWrapper<Building> queryWrapper = new LambdaQueryWrapper<Building>()
                .eq(Building::getStatus, CommonStatus.no_delete.value)
                .eq(Building::getProjectId, id);
        List<Building> buildings = buildingMapper.selectList(queryWrapper);

        projectDTO.setBuildingList(
                buildings.stream().map(BuildingDTO::toDTO).collect(Collectors.toList())
        );
        return projectDTO;
    }

}
