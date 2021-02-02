package com.hainiu.cat.service;

import com.hainiu.cat.service.dto.BuildingSaveDTO;
import com.hainiu.cat.service.dto.ProjectDTO;
import com.hainiu.cat.util.Result;

/**
 * create by biji.zhao on 2020/11/11
 */
public interface ProjectService {

    Result<String> saveOne(BuildingSaveDTO buildingSaveDTO);

    ProjectDTO queryByProjectId(Integer id);
}
