package com.stone.movies.user.service.impl;


import com.stone.movies.model.user.UserInfo;
import com.stone.movies.user.mapper.UserInfoMapper;
import com.stone.movies.user.service.UserInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author stone
 * @since 2022-10-26
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

}
