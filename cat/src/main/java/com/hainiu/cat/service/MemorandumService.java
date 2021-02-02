package com.hainiu.cat.service;

import com.hainiu.cat.service.dto.MemorandumDTO;

import java.util.List;

/**
 * create by biji.zhao on 2020/12/31
 */
public interface MemorandumService {

    void save(MemorandumDTO memorandumDTO);

    List<MemorandumDTO> queryList();

    MemorandumDTO getById(Integer id);
}
