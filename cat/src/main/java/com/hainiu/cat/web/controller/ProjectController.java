package com.hainiu.cat.web.controller;

import com.hainiu.cat.dao.model.Building;
import com.hainiu.cat.service.ProjectService;
import com.hainiu.cat.service.dto.BuildingDTO;
import com.hainiu.cat.service.dto.BuildingSaveDTO;
import com.hainiu.cat.service.dto.ProjectDTO;
import com.hainiu.cat.util.Result;
import com.hainiu.cat.web.vo.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * create by biji.zhao on 2020/11/12
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("/saveBuilding")
    @ResponseBody
    public JsonReturn saveBuilding(BuildingSaveDTO saveDTO) {
        Result<String> result = projectService.saveOne(saveDTO);
        if (result.isSuccess()) {
            return JsonReturn.successInstance();
        }
        return JsonReturn.errorInstance(result.getMsg());
    }

    @GetMapping("/getMapInfo")
    @ResponseBody
    public JsonReturn getMapInfo(Integer id) {
        ProjectDTO projectDTO = projectService.queryByProjectId(id);
        return JsonReturn.successInstance(projectDTO);
    }

}
