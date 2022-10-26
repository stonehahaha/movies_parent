package com.stone.movies.vo.vod;

import lombok.Data;
import io.swagger.annotations.ApiModelProperty;

@Data
public class CourseQueryVo {
	
	@ApiModelProperty(value = "视频演员ID")
	private Long actorId;

	@ApiModelProperty(value = "电影分类ID")
	private Long subjectId;

	@ApiModelProperty(value = "电影分类父级ID")
	private Long subjectParentId;

	@ApiModelProperty(value = "电影标题")
	private String title;

}

