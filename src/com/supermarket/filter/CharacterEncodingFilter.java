package com.supermarket.filter;

import com.supermarket.decorator.RequestDecorator;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 这个类用于全局请求参数拦截处理
 */
@WebFilter(filterName = "CharacterEncodingFilter")
public class CharacterEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获取字符集
        String charsetNew = request.getServletContext().getInitParameter("characterEncoding");
        // 处理响应乱码
        response.setContentType("text/html;charset=" + charsetNew);
        // 处理请求乱码
        request = new RequestDecorator((HttpServletRequest) request);
        // 放行
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
