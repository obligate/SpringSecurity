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
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.peter.security.core.properties.LoginType;
import com.peter.security.core.properties.SecurityProperties;

/**
 * @author peter
 *
 */
@Component("peterAuthenticationFailureHandler")
public class PeterAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler{ // implements AuthenticationFailureHandler {
	
	private  Logger  logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private ObjectMapper ObjectMapper;
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		logger.info("登陆失败");
		if(LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
			response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
			response.setContentType("application/json;charset=UTF-8");
			response.getWriter().write(ObjectMapper.writeValueAsString(exception));
		}else {
			super.onAuthenticationFailure(request, response, exception);
		}
		
	}

}
