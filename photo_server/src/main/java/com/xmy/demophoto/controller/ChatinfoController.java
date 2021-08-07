package com.xmy.demophoto.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xmy.demophoto.entity.Chatinfo;
import com.xmy.demophoto.service.ChatinfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *  聊天控制器
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Slf4j
@RestController
@Api(value = "API", tags = {""})
public class ChatinfoController {

    @Autowired
    private ChatinfoService chatinfoService;

    /**
     * 添加
     */
    @PostMapping("/saveChatinfo")
    @ApiOperation("添加")
    public boolean saveChatinfo(@RequestBody Chatinfo chatinfo) {
        return chatinfoService.save(chatinfo);
    }

    /**
     * 修改
     */
    @PostMapping("/updateChatinfoById")
    @ApiOperation("修改")
    public boolean updateChatinfoById(@RequestBody Chatinfo chatinfo) {
        return chatinfoService.updateById(chatinfo);
    }

    /**
     * 删除
     */
    @PostMapping("/deleteChatinfoById/{id}")
    @ApiOperation("删除")
    public boolean deleteChatinfoById(@PathVariable("id") Long id) {
        return chatinfoService.removeById(id);
    }

    /**
     * 获取详情()
     * @return条件查询
     */
    @PostMapping("/getchatsinfo")
    @ApiOperation("详情")
    public List<Chatinfo> getchatsinfo(@RequestBody Chatinfo chatinfo) {
        Integer getterid = chatinfo.getGetterid();
        Integer sendid = chatinfo.getSendid();
        return chatinfoService.list(Wrappers.lambdaQuery(Chatinfo.class).eq(Chatinfo::getSendid, sendid).eq(Chatinfo::getGetterid, getterid).or().eq(Chatinfo::getGetterid, sendid).eq(Chatinfo::getSendid, getterid));
    }

}
