package com.xmy.demophoto.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("goods")
@ApiModel(value = "Goods对象")
public class Goods implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("name")
    private String name;

    @TableField("posterurl")
    private String posterurl;

    @TableField("type")
    private String type;

    @TableField("score")
    private String score;

    @TableField("actor")
    private String actor;

    @TableField("screen")
    private String screen;

    @TableField("isshow")
    private String isshow;

    @TableField("wish")
    private Integer wish;

    @TableField("playinfo")
    private Integer playinfo;

    @TableField(exist = false)
    private MUser userinfo;
}
