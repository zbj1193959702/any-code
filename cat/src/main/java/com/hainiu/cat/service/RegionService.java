package com.hainiu.cat.service;

import com.hainiu.cat.service.dto.RegionDTO;
import com.hainiu.cat.service.dto.TreeDTO;

import java.util.List;

/**
 * create by biji.zhao on 2020/11/24
 */
public interface RegionService {

    void saveProvince();

    void saveCity();

    void saveDistrict();

    List<RegionDTO> getProvince();

    List<RegionDTO> getChildrenRegion(String code);

    TreeDTO getRegionJson();
}
