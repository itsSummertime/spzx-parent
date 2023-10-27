package com.atguigu.spzx.gateway.filter;

import com.alibaba.fastjson.JSON;
import com.atguigu.spzx.model.vo.common.Result;
import com.atguigu.spzx.model.vo.common.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    //路径匹配器
    private AntPathMatcher matcher = new AntPathMatcher();

    /**
     * 全局的登录过滤
     * 如果请求路径中包含 auth ,则验证token令牌是否正确
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取网关的请求对象
        ServerHttpRequest request = exchange.getRequest();
        //获取请求的路径
        String path = request.getURI().getPath();
        //匹配请求路径中是否包含 auth , pattern表达式必须 / 开头
        if(matcher.match("/**/auth/**", path)){
            //1.判断请求头中是否有token >>>>>>>>>>>
            List<String> list = request.getHeaders().get("token");
            if(list == null){
                //未传递token，拦截请求,并响应数据
                return out(exchange);
            }

            //2.判断token在Redis中是否存在 >>>>>>>>>>>
            //获取到token
            String token = list.get(0);
            //到Redis中判断
            Boolean hasKey = redisTemplate.hasKey("user:login:" + token);
            if(!hasKey){
                //redis中没有对应的token，拦截请求,并响应数据
                return out(exchange);
            }
        }

        //放行
        return chain.filter(exchange);
    }

    //拦截请求,并响应数据
    private Mono<Void> out(ServerWebExchange exchange) {
        //需要响应的数据
        Result<Object> result = Result.error(ResultCodeEnum.LOGIN_AUTH);
        //转为字节数组
        byte[] bytes = JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8);

        //获取网关的响应对象
        ServerHttpResponse response = exchange.getResponse();
        //将字节数组添加到缓冲区
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        //设置响应内容的格式为json，编码为UTF-8  (applictaion/json;charset=utf-8)
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON_UTF8);
        //响应数据
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        //优先级，数值越小，优先级越高，越先执行
        return 0;
    }
}
