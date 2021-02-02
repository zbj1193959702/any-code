package com.hainiu.cat.service;

import com.hainiu.cat.service.dto.ZhiHuHotDTO;
import com.hainiu.cat.util.Result;

import java.util.List;

/**
 * create by biji.zhao on 2021/1/8
 */
public interface ZhiHuHotService {

    Result<ZhiHuHotDTO> saveOne(ZhiHuHotDTO huHotDTO);

    List<ZhiHuHotDTO> queryList();
}
