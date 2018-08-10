/**
 * 
 */
package com.peter.security.browser.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peter.security.core.properties.LoginType;
import com.peter.security.core.properties.SecurityProperties;

/**
 * @author peter
 *
 */
@Component("peterAuthenticationSuccessHandler")
public class PeterAuthenticationSuccessHandler  extends SavedRequestAwareAuthenticationSuccessHandler{ // implements AuthenticationSuccessHandler {

	private  Logger  logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper ObjectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;
	/**
	 *  登录成功会调用
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		logger.info("登陆成功");
		
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(ObjectMapper.writeValueAsString(authentication));
		}else {
//			调用父类的方法，默认就是跳转
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
