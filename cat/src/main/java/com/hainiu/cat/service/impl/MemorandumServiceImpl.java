package com.hainiu.cat.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.hainiu.cat.dao.mapper.MemorandumMapper;
import com.hainiu.cat.dao.model.Memorandum;
import com.hainiu.cat.service.MemorandumService;
import com.hainiu.cat.service.dto.MemorandumContentDTO;
import com.hainiu.cat.service.dto.MemorandumDTO;
import com.hainiu.cat.service.enums.CommonStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * create by biji.zhao on 2020/12/31
 */
@Service
public class MemorandumServiceImpl implements MemorandumService {

    @Autowired
    private MemorandumMapper memorandumMapper;


    @Override
    public void save(MemorandumDTO memorandumDTO) {
        Memorandum memorandum = new Memorandum();
        memorandum.setStatus(CommonStatus.no_delete.value);
        memorandum.setTitle(memorandumDTO.getTitle());
        memorandum.setContentJson(JSON.toJSONString(memorandumDTO.getContentList()));

        if (memorandumDTO.getId() != null) {
            memorandum.setId(memorandumDTO.getId());
            memorandumMapper.updateById(memorandum);
            return;
        }
        memorandumMapper.insert(memorandum);
    }

    @Override
    public List<MemorandumDTO> queryList() {
        LambdaQueryWrapper<Memorandum> queryWrapper = new LambdaQueryWrapper<Memorandum>()
                .eq(Memorandum::getStatus, CommonStatus.no_delete.value)
                .orderByDesc(Memorandum::getId);

        List<Memorandum> memorandums = memorandumMapper.selectList(queryWrapper);
        return memorandums.stream()
                .map(this::toDTO).collect(Collectors.toList());
    }

    @Override
    public MemorandumDTO getById(Integer id) {
        return toDTO(memorandumMapper.selectById(id));
    }

    private MemorandumDTO toDTO(Memorandum memorandum) {
        if (memorandum == null) {
            return null;
        }
        MemorandumDTO memorandumDTO = new MemorandumDTO();
        memorandumDTO.setId(memorandum.getId());
        memorandumDTO.setTitle(memorandum.getTitle());
        memorandumDTO.setContentList(JSON.parseArray(memorandum.getContentJson(), MemorandumContentDTO.class));
        memorandumDTO.setStatus(memorandum.getStatus());
        return memorandumDTO;
    }
}
