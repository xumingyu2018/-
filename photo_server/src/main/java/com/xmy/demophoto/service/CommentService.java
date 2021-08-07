package com.xmy.demophoto.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xmy.demophoto.entity.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;

/**
 *  服务类
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
public interface CommentService extends IService<Comment> {

    int deleteCommentById(@Param("commentid") Integer commentid);

    int insert(Comment comment);

    Comment getCommentById(@Param("id") Integer id);

    int updateComment(HashMap<String, Object> map);
}
