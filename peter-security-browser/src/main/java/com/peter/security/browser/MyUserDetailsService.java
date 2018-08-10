/**
 * 
 */
package com.peter.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author peter
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger Logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Override
	// 根据用户名获取用户信息
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Logger.info("登录用户名：" + username);
		return new User(username, passwordEncoder.encode("qwe123"),
				true,true,true,true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
