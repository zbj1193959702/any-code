package com.hainiu.cat.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hainiu.cat.service.ElasticSearchService;
import com.hainiu.cat.service.LvmamaScenicSpotService;
import com.hainiu.cat.service.dto.LvmamaScenicSpotDTO;
import com.hainiu.cat.service.dto.query.ScenicSpotQuery;
import com.hainiu.cat.util.PageResult;
import com.hainiu.cat.util.Result;
import com.hainiu.cat.web.vo.JsonReturn;
import com.hainiu.cat.web.vo.LvmamaScenicSpotVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

/**
 * create by biji.zhao on 2020/11/13
 */
@Controller
@RequestMapping("/scenicSpot")
public class LvmamaScenicSpotController {

    @Autowired
    ElasticSearchService elasticSearchService;
    @Autowired
    LvmamaScenicSpotService lvmamaScenicSpotService;

    @RequestMapping("save")
    @ResponseBody
    public JsonReturn saveOne(LvmamaScenicSpotDTO spotDTO) {
        Result<String> result = lvmamaScenicSpotService.saveOne(spotDTO);
        if (result.isSuccess()) {
            return JsonReturn.successInstance();
        }
        return JsonReturn.errorInstance(result.getMsg());
    }

    @RequestMapping("table")
    public String customerPage() {
        return "scenicSpot";
    }


    @RequestMapping(value = "list")
    @ResponseBody
    public JsonReturn queryList(String json) throws IOException {
        ScenicSpotQuery query = JSON.parseObject(json, ScenicSpotQuery.class);
        PageResult<LvmamaScenicSpotVO> result = new PageResult<>();
        PageResult<LvmamaScenicSpotDTO> pageResult;
        if (StringUtils.isNotBlank(query.getTitle())) {
            pageResult =  elasticSearchService.queryScenicSpot(query.getTitle(),query.getPageStart(), query.getPageSize());
        }else {
            pageResult = lvmamaScenicSpotService.queryList(query);
        }
        result.setTotalRecordCount(pageResult.getTotalRecordCount());
        List<LvmamaScenicSpotVO> voList = Lists.newArrayList();
        pageResult.getRecords().forEach(r -> voList.add(LvmamaScenicSpotVO.dto2VO(r)));
        result.setRecords(voList);
        return JsonReturn.successInstance(result);
    }
}
