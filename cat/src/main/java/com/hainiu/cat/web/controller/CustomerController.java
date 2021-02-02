package com.hainiu.cat.web.controller;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.hainiu.cat.service.CustomerService;
import com.hainiu.cat.service.dto.CustomerDTO;
import com.hainiu.cat.service.dto.query.CustomerQuery;
import com.hainiu.cat.util.PageResult;
import com.hainiu.cat.util.Result;
import com.hainiu.cat.web.vo.CustomerVO;
import com.hainiu.cat.web.vo.JsonReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * create by biji.zhao on 2020/11/12
 */
@RequestMapping("/customer")
@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping("save")
    @ResponseBody
    public JsonReturn saveOne(String phone) {
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setPhone(phone);
        Result result = customerService.saveOne(customerDTO);
        if (result.isSuccess()) {
            return JsonReturn.successInstance();
        }
        return JsonReturn.errorInstance(result.getMsg());
    }

    @RequestMapping("table")
    public String customerPage() {
        return "brandForm";
    }

    @RequestMapping(value = "list")
    @ResponseBody
    public JsonReturn queryList(String json) {
        CustomerQuery query = JSON.parseObject(json, CustomerQuery.class);
        PageResult<CustomerDTO> pageResult = customerService.queryList(query);

        PageResult<CustomerVO> result = new PageResult<>();
        result.setTotalRecordCount(pageResult.getTotalRecordCount());
        List<CustomerVO> voList = Lists.newArrayList();
        pageResult.getRecords().forEach(r -> voList.add(CustomerVO.dto2VO(r)));
        result.setRecords(voList);
        return JsonReturn.successInstance(result);
    }
}
