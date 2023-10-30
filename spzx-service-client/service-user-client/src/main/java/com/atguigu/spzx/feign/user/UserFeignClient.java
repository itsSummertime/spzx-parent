package com.atguigu.spzx.feign.user;

import com.atguigu.spzx.model.entity.user.UserAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "service-user", path = "/api/user/userAddress")
public interface UserFeignClient {

    @GetMapping("/auth/findById/{id}")
    public UserAddress findById(@PathVariable long id);
}