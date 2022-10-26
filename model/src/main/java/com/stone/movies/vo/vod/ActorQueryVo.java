package com.stone.movies.vo.vod;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class ActorQueryVo {
	
	@ApiModelProperty(value = "演员姓名")
	private String name;

	@ApiModelProperty(value = "头衔 1巨星 2大明星")
	private Integer level;

	@ApiModelProperty(value = "入驻时间")
	private String joinDateBegin;

	@ApiModelProperty(value = "入驻时间")
	private String joinDateEnd;


}

