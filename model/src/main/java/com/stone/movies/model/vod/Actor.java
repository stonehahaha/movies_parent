package com.stone.movies.model.vod;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.stone.movies.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 演员
 * </p>
 *
 * @author stone
 * @since 2022-10-19
 */
@Data
@ApiModel(description = "Actor")
@TableName("actor")
public class Actor extends BaseEntity {

    private static final long serialVersionUID=1L;

    @ApiModelProperty(value = "演员姓名")
    @TableField("name")
    private String name;

    @ApiModelProperty(value = "演员简介")
    @TableField("intro")
    private String intro;

    @ApiModelProperty(value = "演员资历,一句话说明演员")
    @TableField("career")
    private String career;

    @ApiModelProperty(value = "头衔 1巨明星 2大明星")
    @TableField("level")
    private Integer level;

    @ApiModelProperty(value = "演员头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty(value = "排序")
    @TableField("sort")
    private Integer sort;

    @ApiModelProperty(value = "入驻时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    @TableField("join_date")
    private Date joinDate;


}
