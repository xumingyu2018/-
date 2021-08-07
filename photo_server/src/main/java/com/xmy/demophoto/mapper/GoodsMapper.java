package com.xmy.demophoto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.demophoto.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;

/**
 *  Mapper 接口
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Mapper
public interface GoodsMapper extends BaseMapper<Goods> {

    @Delete("delete from goods where id=#{id}")
    int deleteWorkById(@Param("id") Integer id);

    @Insert("insert into goods(name,posterurl,isshow,posterurl,score,actor) " +
            "values (#{name},#{posterurl},#{isshow},#{posterurl},#{score},#{actor})")
    int insert(Goods goods);

    @Select("select * from goods where id=#{id}")
    Goods getWorkById(@Param("id") Integer id);

    @Update("update goods set name=#{name},posterurl=#{posterurl},actor=#{actor},isshow=#{isshow},score=#{score},playinfo=#{playinfo} where id=#{id}")
    int updateWork(HashMap<String, Object> map);
}
