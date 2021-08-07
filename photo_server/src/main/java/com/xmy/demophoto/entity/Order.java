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
import java.util.List;

/**
 *
 *
 * @author 胥明毓
 * @since 2021-05-24
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@TableName("orderinfo")
@ApiModel(value = "Order对象")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.INPUT)
    private String id;

    @TableField("ordernumber")
    private String ordernumber;

    @TableField("total")
    private Integer total;

    @TableField("type")
    private Integer type;

    @TableField("goodsid")
    private String goodsid;

    @TableField("creattime")
    private String creattime;

    @TableField("info")
    private String info;

    @TableField("takeuserid")
    private Integer takeuserid;

    @TableField("ordertype")
    private Integer ordertype;

    @TableField(exist = false)
    private List<Goods> orderDetail;

}
