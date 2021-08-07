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
@TableName("chatinfo")
@ApiModel(value = "Chatinfo对象")
public class Chatinfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "chatid", type = IdType.AUTO)
    private Integer chatid;

    @TableField("sendid")
    private Integer sendid;

    @TableField("getterid")
    private Integer getterid;

    @TableField("content")
    private String content;

    @TableField("info")
    private String info;

}
