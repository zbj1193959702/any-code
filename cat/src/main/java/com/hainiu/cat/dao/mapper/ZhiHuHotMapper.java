package com.hainiu.cat.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hainiu.cat.dao.model.ZhiHuHot;

import java.util.List;

/**
 * create by biji.zhao on 2020/12/24
 */
public interface ZhiHuHotMapper extends BaseMapper<ZhiHuHot> {

    List<ZhiHuHot> queryList();
}
