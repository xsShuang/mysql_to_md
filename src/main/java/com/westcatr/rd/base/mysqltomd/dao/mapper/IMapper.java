package com.westcatr.rd.base.mysqltomd.dao.mapper;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author xieshuang
 * @since 2020/4/15 18:40
 */
@Mapper
public interface IMapper {

    List<JSONObject> selectList(@Param("s") String s);
}
