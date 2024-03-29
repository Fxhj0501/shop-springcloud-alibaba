package io.shop.order.fegin;

import io.shop.bean.User;
import io.shop.order.fegin.fallback.UserServiceFallBack;
import io.shop.order.fegin.fallback.factory.UserServiceFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @description 调用用户微服务的接口
 */
@FeignClient(value = "server-user", fallbackFactory = UserServiceFallBackFactory.class)
//@FeignClient(value = "server-user")
public interface UserService {
    @GetMapping(value = "/user/get/{uid}")
    User getUser(@PathVariable("uid") Long uid);
}