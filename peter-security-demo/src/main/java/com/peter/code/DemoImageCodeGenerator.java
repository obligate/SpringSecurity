/**
 * 
 */
package com.peter.code;

import org.springframework.web.context.request.ServletWebRequest;

import com.peter.security.core.validate.code.ValidateCodeGenerator;
import com.peter.security.core.validate.code.image.ImageCode;

/**
 * 应用级的图像生成器的接口实现
 * 
 * @author peter
 *
 */
//@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	@Override
	public ImageCode generate(ServletWebRequest request) {
		System.out.println("应用中的更高级的图形验证码生成代码");
		return null;
	}

}
