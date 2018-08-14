/**
 * 
 */
package com.peter.security.browser;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.peter.security.core.authentication.mobile.SmsCodeAuthenticationSecurityConfig;
import com.peter.security.core.properties.SecurityProperties;
import com.peter.security.core.validate.code.SmsCodeFilter;
import com.peter.security.core.validate.code.ValidateCodeFilter;

/**
 * @author peter
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	@Autowired
	private AuthenticationSuccessHandler peterAuthenticationSuccessHandler;
	@Autowired
	private AuthenticationFailureHandler peterAuthenticationFailureHandler;
	
	@Autowired
	private DataSource dataSource;
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private SmsCodeAuthenticationSecurityConfig smsCodeAuthenticationSecurityConfig;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().disable();  // 禁用密码验证
		ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
		validateCodeFilter.setAuthenticationFailureHandler(peterAuthenticationFailureHandler);
		validateCodeFilter.setSecurityProperties(securityProperties);
		validateCodeFilter.afterPropertiesSet();
		
		SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
		smsCodeFilter.setAuthenticationFailureHandler(peterAuthenticationFailureHandler);
		smsCodeFilter.setSecurityProperties(securityProperties);
		smsCodeFilter.afterPropertiesSet();
		
		http
			.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class) // 把图片验证的filter放到UsernamePasswordAuthenticationFilter过滤器前面，如果验证失败，直接调用peterAuthenticationFailureHandler进行错误处理
			.formLogin()   // 表单认证
				.loginPage("/authentication/require") // 自定义登录页面
				.loginProcessingUrl("/authentication/form") // 通知spring使用UsernamePasswordAuthenticationFilter来处理
				.successHandler(peterAuthenticationSuccessHandler) // 访问signIn.html,表单登录成功，会使用自己的处理器处理
				.failureHandler(peterAuthenticationFailureHandler) // 访问失败的处理器
				.and()
			.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())
				.userDetailsService(userDetailsService)
//		http.httpBasic()   // http basic认证
			.and()
			.authorizeRequests()
			.antMatchers("/authentication/require",
					securityProperties.getBrowser().getLoginPage(),
					"/code/*").permitAll() // 访问"/signIn.html" 不需要进行身份认证，直接访问
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable() // 跨站防护功能关闭
			.apply(smsCodeAuthenticationSecurityConfig); 
	}
	
    @Bean
    public PasswordEncoder  passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    
	@Bean
	public PersistentTokenRepository persistentTokenRepository() {
		JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    	tokenRepository.setDataSource(dataSource);
//    	tokenRepository.setCreateTableOnStartup(true); // 启动的时候自动创建表
		return tokenRepository;
	}
}
