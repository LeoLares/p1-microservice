package com.microservice.oauth.client;


import com.microservice.commonsusers.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "service-users", url = "localhost:8590/api/users")
public interface UserFeignClient {

    @GetMapping("users/search/findByUserName")

    public User findByUserName(@RequestParam String usermame);
}
