/**
 * 
 */
package com.peter.web.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.peter.web.filter.TimeFilter;
import com.peter.web.interceptor.TimeInterceptor;

/**
 * @author peter
 *
 */
//@Configuration
// extends org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter 5.0+已过时
public class WebConfig implements WebMvcConfigurer {
	@Autowired
	private TimeInterceptor timeInterceptor;

	@Override
	public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
		// 异步支持
//	        configurer.registerCallableInterceptors(); // callable 拦截器
//	        configurer.registerDeferredResultInterceptors(); // deferredResult拦截器
//	        configurer.setTaskExecutor(); // 自定义线程池
//	        configurer.setDefaultTimeout() // 超时设置
	}

	@Bean
	public FilterRegistrationBean<Filter> timeFilter() {
		FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<Filter>();
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);

		List<String> urls = new ArrayList<String>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInterceptor);
	}


}
