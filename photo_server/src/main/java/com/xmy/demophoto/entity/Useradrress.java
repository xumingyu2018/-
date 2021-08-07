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
@TableName("useradrress")
@ApiModel(value = "Useradrress对象")
public class Useradrress implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;

    @TableField("jieshouname")
    private String jieshouname;

    @TableField("jieshouaddress")
    private String jieshouaddress;

    @TableField("jieshoutel")
    private String jieshoutel;

    @TableField("userid")
    private String userid;

    @TableField("status")
    private Integer status;

}
