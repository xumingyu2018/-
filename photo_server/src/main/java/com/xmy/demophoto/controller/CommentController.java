package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.Comment;
import com.xmy.demophoto.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 评论控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * 添加
     */
    @PostMapping("/addComent")
    @ApiOperation("添加")
    public boolean saveComment(@RequestBody Comment comment) {
        return commentService.save(comment);
    }

    /**
     * 修改
     */
    @PostMapping("/updateCommentById")
    @ApiOperation("修改")
    public boolean updateCommentById(@RequestBody Comment comment) {
        return commentService.updateById(comment);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteCommentById/{id}")
    @ApiOperation("删除")
    public boolean deleteCommentById(@PathVariable("id") Long id) {
        return commentService.removeById(id);
    }

    /**
     * 获取详情
     *
     */
    @PostMapping("/queryCommentByGoodsId")
    @ApiOperation("详情")
    public List<Comment> getComments(@RequestBody Comment comment) {
        return commentService.list(Wrappers.lambdaQuery(Comment.class).eq(Comment::getGoodsid, comment.getGoodsid()));
    }

}
