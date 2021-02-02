package com.hainiu.cat.web.controller;

import com.alibaba.fastjson.JSON;
import com.hainiu.cat.service.MemorandumService;
import com.hainiu.cat.service.dto.MemorandumDTO;
import com.hainiu.cat.web.vo.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * create by biji.zhao on 2020/12/31
 */
@Controller
@RequestMapping("/memorandum")
public class MemorandumController {

    @Autowired
    private MemorandumService memorandumService;

    @RequestMapping("/index")
    public String index(){
        return "memorandum";
    }

    @RequestMapping("/save")
    @ResponseBody
    public JsonReturn save(String memorandumJson){
        MemorandumDTO memorandumDTO = JSON.parseObject(memorandumJson, MemorandumDTO.class);
        memorandumService.save(memorandumDTO);
        return JsonReturn.successInstance();
    }

    @RequestMapping("/queryList")
    @ResponseBody
    public JsonReturn queryList(){
        List<MemorandumDTO> memorandumDTOS = memorandumService.queryList();
        return JsonReturn.successInstance(memorandumDTOS);
    }

    @RequestMapping("/getById")
    @ResponseBody
    public JsonReturn getById(Integer id){
        return JsonReturn.successInstance(memorandumService.getById(id));
    }

}
