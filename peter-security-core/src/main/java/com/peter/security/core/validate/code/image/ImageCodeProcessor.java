package com.peter.security.core.validate.code.image;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import com.peter.security.core.validate.code.impl.AbstractValidateCodeProcessor;

/**
 * 图片验证码处理器
 * @author peter
 *
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {
	/**
	 * 发送图形验证码，将其写到响应中
	 */
	@Override
	protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
		HttpServletResponse response  = request.getResponse();
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
}
