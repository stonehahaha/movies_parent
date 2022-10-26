package com.stone.movies.vo.live;


import com.stone.movies.model.live.LiveCourse;
import com.stone.movies.model.vod.Actor;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LiveCourseVo extends LiveCourse {

	@ApiModelProperty(value = "主要演员")
	private Actor actor;

	private Integer liveStatus;

	@ApiModelProperty(value = "直播开始时间")
	private String startTimeString;

	@ApiModelProperty(value = "直播结束时间")
	private String endTimeString;

}

