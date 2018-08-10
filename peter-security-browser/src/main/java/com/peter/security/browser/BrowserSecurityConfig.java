/**
 * 
 */
package com.peter.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.peter.security.core.properties.SecurityProperties;

/**
 * @author peter
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private SecurityProperties securityProperties;
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().disable();  // 禁用密码验证
		http.formLogin()   // 表单认证
			.loginPage("/authentication/require") // 自定义登录页面
			.loginProcessingUrl("/authentication/form") // 通知spring使用UsernamePasswordAuthenticationFilter来处理
//		http.httpBasic()   // http basic认证
			.and()
			.authorizeRequests()
			.antMatchers("/authentication/require",securityProperties.getBrowser().getLoginPage()).permitAll() // 访问"/signIn.html" 不需要进行身份认证，直接访问
			.anyRequest()
			.authenticated()
			.and()
			.csrf().disable(); // 跨站防护功能关闭
	}
	
    @Bean
    public PasswordEncoder  passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
}
