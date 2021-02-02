package com.hainiu.cat.service;

import com.hainiu.cat.service.dto.LvmamaScenicSpotDTO;
import com.hainiu.cat.service.dto.query.ScenicSpotQuery;
import com.hainiu.cat.util.PageResult;
import com.hainiu.cat.util.Result;

/**
 * create by biji.zhao on 2020/11/13
 */
public interface LvmamaScenicSpotService {

    Result<String> saveOne(LvmamaScenicSpotDTO lvmamaScenicSpotDTO);

    PageResult<LvmamaScenicSpotDTO> queryList(ScenicSpotQuery query);
}
