package com.dount.cloud.hystrixservice.filter;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

/**
 * 使用Hystrix的请求缓存时必须对HystrixRequestContext进行初始化
 *
 * @author micheal.wang <a href="michael.won007@gmail.com"/>
 * @create 2020-02-07
 */
@Component
@WebFilter(urlPatterns = "/*", asyncSupported = true)
public class HystrixRequestContextFilter implements Filter {

//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        //HystrixRequestContext context = HystrixRequestContext.initializeContext();
//    }

    /**
     * 使用Hystrix的请求缓存时必须对HystrixRequestContext进行初始化，否则将提示异常
     * <textarea>java.lang.IllegalStateException: Request caching is not available. Maybe you need to initialize the HystrixRequestContext?</textarea>
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HystrixRequestContext context = HystrixRequestContext.initializeContext();

        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            context.close();
        }

    }

    @Override
    public void destroy() {

    }
}
