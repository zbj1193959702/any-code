package com.hainiu.cat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.common.collect.Lists;
import com.hainiu.cat.dao.mapper.ZhiHuHotMapper;
import com.hainiu.cat.dao.model.House;
import com.hainiu.cat.dao.model.ZhiHuHot;
import com.hainiu.cat.service.ZhiHuHotService;
import com.hainiu.cat.service.dto.ZhiHuHotDTO;
import com.hainiu.cat.service.enums.CommonStatus;
import com.hainiu.cat.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * create by biji.zhao on 2021/1/8
 */
@Service
public class ZhiHuHotServiceImpl implements ZhiHuHotService {
    @Autowired
    private ZhiHuHotMapper zhiHuHotMapper;

    @Override
    public Result<ZhiHuHotDTO> saveOne(ZhiHuHotDTO huHotDTO) {
        if (huHotDTO == null) {
            return Result.errorInstance("非法入参");
        }
        ZhiHuHot zhiHuHot = coverToModel(huHotDTO);
        LambdaQueryWrapper<ZhiHuHot> queryWrapper = new LambdaQueryWrapper<ZhiHuHot>()
                .eq(ZhiHuHot::getRanking, zhiHuHot.getRanking());

        ZhiHuHot old = zhiHuHotMapper.selectOne(queryWrapper);
        if (old != null) {
            zhiHuHotMapper.deleteById(old.getId());
        }
        zhiHuHotMapper.insert(zhiHuHot);

        LambdaQueryWrapper<ZhiHuHot> wrapper = new LambdaQueryWrapper<ZhiHuHot>()
                .eq(ZhiHuHot::getRanking, zhiHuHot.getRanking());
        ZhiHuHot huHot = zhiHuHotMapper.selectOne(wrapper);
        return Result.successInstance(getDTO(huHot));
    }

    @Override
    public List<ZhiHuHotDTO> queryList() {
        List<ZhiHuHot> zhiHuHots = zhiHuHotMapper.queryList();
        return zhiHuHots.stream().map(this::getDTO).collect(Collectors.toList());
    }

    private ZhiHuHotDTO getDTO(ZhiHuHot model) {
        ZhiHuHotDTO dto = new ZhiHuHotDTO();
        dto.setId(model.getId());
        dto.setTitle(model.getTitle());
        dto.setContent(model.getContent());
        if (model.getRanking() == null) {
            dto.setRanking(StringUtils.EMPTY);
        }else {
            dto.setRanking(model.getRanking().toString());
        }
        dto.setImage(model.getImage());
        dto.setHot(model.getHot());
        return dto;
    }

    private static ZhiHuHot coverToModel(ZhiHuHotDTO huHotDTO) {
        if (huHotDTO == null) {
            return null;
        }
        ZhiHuHot zhiHuHot = new ZhiHuHot();
        zhiHuHot.setId(huHotDTO.getId());
        zhiHuHot.setTitle(huHotDTO.getTitle());
        zhiHuHot.setContent(huHotDTO.getContent());
        zhiHuHot.setHot(huHotDTO.getHot());
        String ranking = huHotDTO.getRanking();
        int length = ranking.length();
        String target = ranking;
        for (int i = 0; i < length; i++) {
            if (ranking.startsWith("0")) {
                target = ranking.substring(1, length);
            }else {
                break;
            }
        }
        zhiHuHot.setRanking(Integer.parseInt(target));
        zhiHuHot.setImage(huHotDTO.getImage());
        zhiHuHot.setStatus(CommonStatus.no_delete.value);
        zhiHuHot.setCreateTime(new Date());
        zhiHuHot.setUpdateTime(new Date());
        return zhiHuHot;
    }
}
