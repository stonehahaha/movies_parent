package com.stone.movies.vod.service.impl;

import com.stone.movies.model.vod.Actor;
import com.stone.movies.vod.mapper.ActorMapper;
import com.stone.movies.vod.service.ActorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 演员 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-10-19
 */
@Service
public class ActorServiceImpl extends ServiceImpl<ActorMapper, Actor> implements ActorService {

}
