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
@TableName("orderdetail")
@ApiModel(value = "Orderdetail对象")
public class Orderdetail implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @TableField("ordernumber")
    private String ordernumber;

    @TableField("number")
    private Integer number;

    @TableField("goodsid")
    private Integer goodsid;

    @TableField("screen")
    private Integer screen;

    @TableField("score")
    private Integer score;

    @TableField("posterurl")
    private String posterurl;

    @TableField("name")
    private String name;

}
