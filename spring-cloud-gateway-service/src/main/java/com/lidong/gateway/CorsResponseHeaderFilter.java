package com.lidong.gateway;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;

/**
 * 解决Spring Cloud Gateway 2.x跨域时出现重复Origin的BUG
 * 思路：
 * 当请求经过NettyRoutingFilter处理后，并不会马上响应客户端请求，
 * 接下来还有重要的一步要做，那就是处理响应体(ResponseBoby)，
 * 由NettyWriteResponseFilter这个过滤器来处理，
 * 所以，要修复这个问题，就在处理完响应体之后立马再处理重复的跨域请求头。
 */
@Component("corsResponseHeaderFilter")
public class CorsResponseHeaderFilter implements GlobalFilter, Ordered {

    @Override
    public int getOrder() {
        // 指定此过滤器位于NettyWriteResponseFilter之后
        // 即待处理完响应体后接着处理响应头
        return NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER + 1;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        return chain.filter(exchange).then(Mono.defer(() -> {
            exchange.getResponse().getHeaders().entrySet().stream()
                    .filter(kv -> (kv.getValue() != null && kv.getValue().size() > 1))
                    .filter(kv -> (kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN)
                            || kv.getKey().equals(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS)))
                    .forEach(kv -> {
                        kv.setValue(new ArrayList<String>() {{
                            add(kv.getValue().get(0));
                        }});
                    });
            return chain.filter(exchange);
        }));
    }
}
