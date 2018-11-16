package com.xiaogangkui.util.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.xiaogangkui.wrapper.BodyReaderHttpServletRequestWrapper;
import lombok.extern.log4j.Log4j;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Created by luchunyu
 */
@Log4j
public class LoginInterceptor extends HandlerInterceptorAdapter {

    /**
     * 调用方法之前
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        String bodyString = new BodyReaderHttpServletRequestWrapper(request).getBodyString(request);
        Map<String, Object> map = JSON.parseObject(bodyString, new TypeReference<Map<String, Object>>() {
        });
        log.info(JSON.toJSON(map));
        return true;
    }

    /**
     * 调用方法成功之后处理会在DispatcherServlet 进行视图返回渲染之前被调用
     * postHandle 方法被调用的方向跟preHandle 是相反的，
     * 也就是说先声明的Interceptor 的postHandle 方法反而会后执行
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    /**
     * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，也就是DispatcherServlet渲染了视图执行
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }

}
