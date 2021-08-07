package com.xmy.demophoto.controller.back;

import com.xmy.demophoto.entity.Comment;
import com.xmy.demophoto.service.CommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Api(description = "评论管理")
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class CommentsController {

    @Autowired
    CommentService commentService;

    /**
     * 查询后台评论信息
     *
     */
    @ApiOperation(value = "查询所有评论")
    @GetMapping("/comment_table")
    public String adminTable(Model model){
        List<Comment> list = commentService.list();
        model.addAttribute("Comments",list);
        return "table/comment_table";
    }
    /**
     * 删除后台评论
     *
     */
    @ApiOperation(value = "删除评论")
    @GetMapping("/comment/delete/{id}")
    public String deleteComment(@PathVariable("id") Integer id){
        commentService.deleteCommentById(id);
        return "redirect:/comment_table";
    }

    /**
     * 添加后台评论信息
     *
     */
    @ApiOperation(value = "添加评论")
    @PostMapping("/comment/insert")
    public String addComment(Comment comment){
        comment.setCreattime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

        commentService.insert(comment);
        return "redirect:/comment_table";
    }

    /**
     * 查询后台要修改的评论
     *
     */
    @ApiOperation(value = "查询要修改的评论")
    @GetMapping("/comment/toUpdate/{id}")
    public String toUpdateComment(@PathVariable("id")Integer id,Model model){
        Comment comment = commentService.getCommentById(id);
        model.addAttribute("QResult",comment);
        return "table/updateComment_table";
    }
    /**
     * 根据id修改后台评论
     *
     */
    @ApiOperation(value = "根据ID修改评论")
    @PostMapping("/comment/update")
    public String updateComment(@RequestParam HashMap<String,Object> map){
        commentService.updateComment(map);
        return "redirect:/comment_table";
    }
}
