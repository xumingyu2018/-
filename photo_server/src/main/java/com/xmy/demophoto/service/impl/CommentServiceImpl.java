package com.xmy.demophoto.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xmy.demophoto.entity.Comment;
import com.xmy.demophoto.mapper.CommentMapper;
import com.xmy.demophoto.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 *  服务实现类
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public int deleteCommentById(@Param("commentid") Integer commentid) {
        return commentMapper.deleteCommentById(commentid) ;
    }

    @Override
    public int insert(Comment comment) {
        return commentMapper.insert(comment);
    }

    @Override
    public Comment getCommentById(@Param("commentid") Integer commentid) {
        return commentMapper.getCommentById(commentid);
    }

    @Override
    public int updateComment(HashMap<String, Object> map) {
        return commentMapper.updateComment(map);
    }

}
