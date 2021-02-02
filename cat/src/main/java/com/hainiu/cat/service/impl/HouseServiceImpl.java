package com.hainiu.cat.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.hainiu.cat.dao.mapper.HouseMapper;
import com.hainiu.cat.dao.model.House;
import com.hainiu.cat.service.HouseService;
import com.hainiu.cat.service.dto.HouseDTO;
import com.hainiu.cat.service.dto.HouseSaveDTO;
import com.hainiu.cat.service.dto.query.ScenicSpotQuery;
import com.hainiu.cat.service.enums.CommonStatus;
import com.hainiu.cat.service.enums.HouseTypeEnum;
import com.hainiu.cat.util.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

/**
 * create by biji.zhao on 2020/11/28
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;

    @Override
    public void saveOne(HouseSaveDTO saveDTO) {
        if (saveDTO == null) {
            return;
        }
        try {
            String norms = saveDTO.getNorms();
            String area = norms.substring(norms.indexOf("|") + 1, norms.indexOf("平米")).trim();
            House house = new House();
            house.setTitle(saveDTO.getTitle());
            house.setAddress(saveDTO.getAddress());
            house.setPrice(
                    Integer.parseInt(saveDTO.getPrice()
                            .replace("单价", "")
                            .replace("元/平米", ""))
            );
            house.setArea(Double.valueOf(area));
            house.setDetailUrl(saveDTO.getDetailUrl());
            house.setFirstImage(saveDTO.getFirstImage());
            house.setHouseType(HouseTypeEnum.er_shou_fang.value);
            house.setCreateTime(new Date());
            house.setStatus(CommonStatus.no_delete.value);
            house.setNorms(norms);
            houseMapper.insert(house);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveNewHouse(HouseSaveDTO saveDTO) {
        if (saveDTO == null) {
            return;
        }
        try {
            House house = new House();
            house.setTitle(saveDTO.getTitle());
            house.setFirstImage(saveDTO.getFirstImage());
            house.setHouseType(HouseTypeEnum.new_house.value);
            house.setProvince(saveDTO.getProvince());
            house.setDistrict(saveDTO.getDistrict());
            house.setDetailUrl(saveDTO.getDetailUrl());
            house.setCreateTime(new Date());
            house.setPrice(Integer.valueOf(saveDTO.getPrice()));
            String[] buildAreas = saveDTO.getNorms().trim()
                    .replace("建面", "")
                    .replace("㎡", "")
                    .split("-");
            house.setMinBuildArea(Double.valueOf(buildAreas[0]));
            house.setMaxBuildArea(Double.valueOf(buildAreas[1]));
            house.setTags(saveDTO.getTags());

            house.setNorms(String.format("%s元/平米| %s", saveDTO.getPrice(), saveDTO.getNorms()));
            houseMapper.insert(house);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageResult<HouseDTO> queryList(ScenicSpotQuery query) {
        IPage<House> scenicSpotIPage = new Page<>(query.getPageStart(), query.getPageSize());
        LambdaQueryWrapper<House> queryWrapper = new LambdaQueryWrapper<House>()
                .eq(House::getStatus, CommonStatus.no_delete.value)
                .like(StringUtils.isNotEmpty(query.getTitle()) , House::getTitle, query.getTitle());
        scenicSpotIPage = houseMapper.selectPage(scenicSpotIPage, queryWrapper);
        PageResult<HouseDTO> result = new PageResult<>();
        result.setTotalRecordCount(Math.toIntExact(scenicSpotIPage.getTotal()));
        result.setRecords(scenicSpotIPage.getRecords().stream()
                .map(HouseServiceImpl::getDTO).collect(Collectors.toList()));
        return result;
    }

    private static HouseDTO getDTO(House house) {
        if (house == null) {
            return null;
        }
        HouseDTO houseDTO = new HouseDTO();
        houseDTO.setId(house.getId());
        houseDTO.setTitle(house.getTitle());
        houseDTO.setAddress(house.getAddress());
        houseDTO.setPrice(house.getPrice());
        houseDTO.setArea(house.getArea());
        houseDTO.setDetailUrl(house.getDetailUrl());
        houseDTO.setNorms(house.getNorms());
        houseDTO.setFirstImage(house.getFirstImage());
        houseDTO.setHouseType(house.getHouseType());
        houseDTO.setCreateTime(house.getCreateTime());
        houseDTO.setStatus(house.getStatus());
        houseDTO.setProvince(house.getProvince());
        houseDTO.setDistrict(house.getDistrict());
        houseDTO.setMinBuildArea(house.getMinBuildArea());
        houseDTO.setMaxBuildArea(house.getMaxBuildArea());
        if (StringUtils.isNotEmpty(house.getTags())) {
            String replace = house.getTags().replace("|", "&");
            houseDTO.setTags(Lists.newArrayList(replace.split("&")));
        }
        return houseDTO;
    }
}
