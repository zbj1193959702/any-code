package com.hainiu.cat.service;

import com.hainiu.cat.service.dto.LvmamaScenicSpotDTO;
import com.hainiu.cat.util.PageResult;

import java.io.IOException;

/**
 * create by biji.zhao on 2020/11/12
 */
public interface ElasticSearchService {

    void importLvmama() throws Exception;

//    void deleteAll();

    PageResult<LvmamaScenicSpotDTO> queryScenicSpot(String text, Integer pageStart, Integer pageSize) throws IOException;

    /**
     * 删除索引
     *
     * @return
     * @throws Exception
     */
    Boolean deleteIndex() throws Exception;

    /**
     * 查询索引是否存在
     *
     * @return
     * @throws Exception
     */
    Boolean searchIndex() throws Exception;

    /**
     * 创建索引
     *
     * @return
     * @throws Exception
     */
    String createIndex() throws Exception;
}
