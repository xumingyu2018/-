package com.xmy.demophoto.controller.back;

import com.xmy.demophoto.entity.Goods;
import com.xmy.demophoto.service.GoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Api(description = "摄影作品管理")
@CrossOrigin(origins = "*",maxAge = 3600)
@Controller
public class WorksController {

    @Autowired
    GoodsService goodsService;

    @ApiOperation(value = "显示所有作品")
    @GetMapping("/works_table")
    public String worksTable(Model model){
        List<Goods> list = goodsService.list();
        model.addAttribute("works",list);
        return "table/works_table";
    }

    @ApiOperation(value = "删除作品")
    @GetMapping("/work/delete/{id}")
    public String deleteWorks(@PathVariable("id") Integer id){
        goodsService.deleteWorkById(id);
        return "redirect:/works_table";
    }

    @ApiOperation(value = "添加作品")
    @PostMapping("/work/insert")
    public String addWorks(Goods goods){
        goodsService.insert(goods);
        return "redirect:/works_table";
    }

    @ApiOperation(value = "查询要修改的作品")
    @GetMapping("/work/toUpdate/{id}")
    public String toUpdateWorks(@PathVariable("id")Integer id,Model model){
        Goods goods = goodsService.getWorkById(id);
        model.addAttribute("QResult",goods);
        return "table/updateWork_table";
    }

    @ApiOperation(value = "根据ID修改作品")
    @PostMapping("/work/update")
    public String updateWorks(@RequestParam HashMap<String,Object> map){
        int i = goodsService.updateWork(map);
        System.out.println(i);
        return "redirect:/works_table";
    }
}
