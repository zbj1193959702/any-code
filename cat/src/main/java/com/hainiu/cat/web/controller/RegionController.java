package com.hainiu.cat.web.controller;

import com.alibaba.fastjson.JSON;
import com.hainiu.cat.service.RegionService;
import com.hainiu.cat.service.dto.RegionDTO;
import com.hainiu.cat.service.dto.TreeDTO;
import com.hainiu.cat.web.vo.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * create by biji.zhao on 2020/11/27
 */
@RequestMapping("/region")
@RestController
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping("/provinceList")
    public JsonReturn getProvince() {
        List<RegionDTO> provinceList = regionService.getProvince();
        return JsonReturn.successInstance(provinceList);
    }

    @RequestMapping("/regionJson")
    public JsonReturn getRegionJson() {
        TreeDTO regionJson = regionService.getRegionJson();
        System.out.println(JSON.toJSONString(regionJson));
        return JsonReturn.successInstance(JSON.toJSONString(regionJson));
    }

    @RequestMapping("/getChildren")
    public JsonReturn getProvince(String code) {
        List<RegionDTO> provinceList = regionService.getChildrenRegion(code);
        return JsonReturn.successInstance(provinceList);
    }
}
