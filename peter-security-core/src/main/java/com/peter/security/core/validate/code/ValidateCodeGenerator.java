/**
 * 
 */
package com.peter.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * 验证码生成接口
 * @author peter
 *
 */
public interface ValidateCodeGenerator {
	ValidateCode generate(ServletWebRequest request);
}
