package com.hainiu.cat.web.controller;

import com.alibaba.fastjson.JSON;
import com.hainiu.cat.service.HouseService;
import com.hainiu.cat.service.dto.HouseDTO;
import com.hainiu.cat.service.dto.HouseSaveDTO;
import com.hainiu.cat.service.dto.query.ScenicSpotQuery;
import com.hainiu.cat.util.PageResult;
import com.hainiu.cat.web.vo.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * create by biji.zhao on 2020/11/28
 */
@RequestMapping("/house")
@Controller
public class HouseController {

    @Autowired
    private HouseService houseService;

    @RequestMapping("/saveOne")
    @ResponseBody
    public JsonReturn saveOne(HouseSaveDTO saveDTO) {
        if (saveDTO == null) {
            return JsonReturn.errorInstance("empty");
        }
        houseService.saveOne(saveDTO);
        return JsonReturn.successInstance();
    }

    @RequestMapping("/saveNewHouse")
    @ResponseBody
    public JsonReturn saveNewHouse(HouseSaveDTO saveDTO) {
        if (saveDTO == null) {
            return JsonReturn.errorInstance("empty");
        }
        houseService.saveNewHouse(saveDTO);
        return JsonReturn.successInstance();
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public JsonReturn queryList(String json)  {
        ScenicSpotQuery query = JSON.parseObject(json, ScenicSpotQuery.class);
        PageResult<HouseDTO> result = houseService.queryList(query);
        return JsonReturn.successInstance(result);
    }

    @RequestMapping("table")
    public String customerPage() {
        return "house";
    }

    @RequestMapping("map3d")
    public String map3d(Integer id, ModelMap modelMap) {
        modelMap.addAttribute("id", id);
        return "map3d";
    }

    @RequestMapping("map2d")
    public String map2d() {
        return "map2d";
    }
}
