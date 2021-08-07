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
@TableName("rowinfo")
@ApiModel(value = "Row对象")
public class Row implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer id;

    @TableField("rowname")
    private String rowname;

    @TableField("content")
    private String content;

    @TableField("url")
    private String url;

    @TableField("address")
    private String address;

    @TableField("prCollectionNum")
    private Integer prCollectionNum;

    @TableField("place")
    private String place;

    @TableField("userid")
    private Integer userid;

}
