package com.xmy.demophoto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xmy.demophoto.entity.Comment;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;

/**
 *  Mapper 接口
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

    @Delete("delete from comment where commentid=#{commentid}")
    int deleteCommentById(@Param("commentid") Integer commentid);

    @Override
    @Insert("insert into comment(commentid,username,content,goodsid,creattime) " +
            "values (#{commentid},#{username},#{content},#{goodsid},#{creattime})")
    int insert(Comment comment);

    @Select("select * from comment where commentid=#{commentid}")
    Comment getCommentById(@Param("commentid") Integer commentid);

    @Update("update comment set username=#{username},content=#{content},goodsid=#{goodsid} where commentid=#{commentid}")
    int updateComment(HashMap<String, Object> map);
}
