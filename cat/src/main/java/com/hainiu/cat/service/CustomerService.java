package com.hainiu.cat.service;

import com.hainiu.cat.service.dto.CustomerDTO;
import com.hainiu.cat.service.dto.query.CustomerQuery;
import com.hainiu.cat.util.PageResult;
import com.hainiu.cat.util.Result;

/**
 * create by biji.zhao on 2020/11/12
 */
public interface CustomerService {

    Result saveOne(CustomerDTO customerDTO);

    PageResult<CustomerDTO> queryList(CustomerQuery customerQuery);
}
