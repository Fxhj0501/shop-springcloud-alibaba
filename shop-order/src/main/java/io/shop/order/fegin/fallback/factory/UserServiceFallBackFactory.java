package io.shop.order.fegin.fallback.factory;

import feign.hystrix.FallbackFactory;
import io.shop.bean.User;
import io.shop.order.fegin.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceFallBackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable cause) {
        return new UserService() {
            @Override
            public User getUser(Long uid) {
                User user = new User();
                user.setId(-1L);
                return user;
            }
        };
    }
}

