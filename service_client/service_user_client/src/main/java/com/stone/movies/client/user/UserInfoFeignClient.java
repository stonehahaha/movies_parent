package com.stone.movies.client.user;

import com.stone.movies.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author stonestart
 * @create 2022/10/26 - 17:33
 */
@FeignClient(value = "service-user")
public interface UserInfoFeignClient {

    @GetMapping("/admin/user/userInfo/inner/getById/{id}")
    UserInfo getById(@PathVariable("id") Long id);

}
