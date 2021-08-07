package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.Rowcomment;
import com.xmy.demophoto.service.RowcommentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  约拍评论控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class RowcommentController {

    @Autowired
    private RowcommentService rowcommentService;

    /**
     * 添加
     */
    @PostMapping("/saveRowcomment")
    @ApiOperation("添加")
    public boolean saveRowcomment(@RequestBody Rowcomment rowcomment) {
        return rowcommentService.save(rowcomment);
    }

    /**
     * 修改
     */
    @PostMapping("/updateRowcommentById")
    @ApiOperation("修改")
    public boolean updateRowcommentById(@RequestBody Rowcomment rowcomment) {
        return rowcommentService.updateById(rowcomment);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteRowcommentById/{id}")
    @ApiOperation("删除")
    public boolean deleteRowcommentById(@PathVariable("id") Long id) {
        return rowcommentService.removeById(id);
    }

    /**
     * 获取详情
     * @return
     */
    @PostMapping("/queryCommentByGoodsId2")
    @ApiOperation("详情")
    public List<Rowcomment> getRowcommentById(@RequestBody Rowcomment rowcomment) {
        return rowcommentService.list(Wrappers.lambdaQuery(Rowcomment.class).eq(Rowcomment::getGoodsid, rowcomment.getGoodsid()));
    }

}
