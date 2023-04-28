package com.autoaid.breakdown.client;

import com.autoaid.breakdown.model.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "UserService")
public interface UserFeignClient {
    @GetMapping("/userapi/get/{id}")
    UserInfo getUserInfo(@PathVariable Long id);
}
