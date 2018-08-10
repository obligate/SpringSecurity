/**
 * 
 */
package com.peter.web.interceptor;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author peter
 *
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {
	// controller调用之前被调用
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("preHandle");
		System.out.println(((HandlerMethod)handler).getBean().getClass().getName());
		System.out.println(((HandlerMethod)handler).getMethod().getName());
		request.setAttribute("startTime", new Date().getTime());
		return true;
	}
	// controller 出现异常不会被调用
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		System.out.println("postHandle");
		long start = (long) request.getAttribute("startTime");
		System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
	}
	// controller调用结束被调用，不管是否发生异常
	// 如果出现了异常，异常之前如果定义了ControllerExceptionHandler，此时的ex 为null
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("afterCompletion");
		long start = (long) request.getAttribute("startTime");
		System.out.println("time interceptor 耗时:" + (new Date().getTime() - start));
		System.out.println("ex is " + ex);
	}
}
