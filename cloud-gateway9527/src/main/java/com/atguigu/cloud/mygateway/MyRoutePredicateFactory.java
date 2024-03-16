package com.atguigu.cloud.mygateway;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

@Component
public class MyRoutePredicateFactory  extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {



    public MyRoutePredicateFactory()
    {
        super(MyRoutePredicateFactory.Config.class);
    }



    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }



    @Validated
    public static class Config{
        @Setter
        @Getter
        @NotEmpty
        private String userType; //钻、金、银等用户等级
    }




    @Override
    public Predicate<ServerWebExchange> apply(MyRoutePredicateFactory.Config config)
    {
        return new Predicate<ServerWebExchange>() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if(userType == null){
                    return false;
                }
                if(userType.equalsIgnoreCase(config.getUserType())){
                    return true;
                }
                return false;
            }

        };
    }

}
