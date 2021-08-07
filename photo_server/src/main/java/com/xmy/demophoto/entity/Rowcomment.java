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
@TableName("rowcomment")
@ApiModel(value = "Rowcomment对象")
public class Rowcomment implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "commentid", type = IdType.AUTO)
    private Integer commentid;

    @TableField("username")
    private String username;

    @TableField("content")
    private String content;

    @TableField("creattime")
    private String creattime;

    @TableField("goodsid")
    private String goodsid;

    @TableField("info")
    private String info;

}
