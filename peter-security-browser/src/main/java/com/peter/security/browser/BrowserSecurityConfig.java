/**
 * 
 */
package com.peter.security.browser;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author peter
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		http.httpBasic().disable();  // 禁用密码验证
		http.formLogin()
//		http.httpBasic() 
			.and()
			.authorizeRequests()
			.anyRequest()
			.authenticated();
	}
}
