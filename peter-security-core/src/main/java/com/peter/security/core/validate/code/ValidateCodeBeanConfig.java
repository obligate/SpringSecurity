/**
 * 
 */
package com.peter.security.core.validate.code;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.peter.security.core.properties.SecurityProperties;

/**
 * @author peter
 *
 */
@Configuration
public class ValidateCodeBeanConfig {
	
	@Autowired
	private SecurityProperties securityProperties;
	
	@Bean
	// 先从Spring容器中查找是否存在imageCodeGenerator的bean，如果不存在使用下面创建的bean
	@ConditionalOnMissingBean(name = "imageCodeGenerator") 
	public ValidateCodeGenerator imageCodeGenerator() {
		ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
		codeGenerator.setSecurityProperties(securityProperties);
		return codeGenerator;
	}
}
