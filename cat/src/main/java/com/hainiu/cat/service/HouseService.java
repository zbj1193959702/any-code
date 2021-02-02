package com.hainiu.cat.service;

import com.hainiu.cat.service.dto.HouseDTO;
import com.hainiu.cat.service.dto.HouseSaveDTO;
import com.hainiu.cat.service.dto.query.ScenicSpotQuery;
import com.hainiu.cat.util.PageResult;

/**
 * create by biji.zhao on 2020/11/28
 */
public interface HouseService {

    void saveOne(HouseSaveDTO saveDTO);

    void saveNewHouse(HouseSaveDTO saveDTO);

    PageResult<HouseDTO> queryList(ScenicSpotQuery query);
}
