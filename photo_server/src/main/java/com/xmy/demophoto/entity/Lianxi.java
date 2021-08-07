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
@TableName("lianxi")
@ApiModel(value = "Lianxi对象")
public class Lianxi implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "lianxiid", type = IdType.AUTO)
    private Integer lianxiid;

    @TableField("myid")
    private Integer myid;

    @TableField("otherid")
    private Integer otherid;

    @TableField("othername")
    private String othername;

    @TableField("myname")
    private String myname;

}
