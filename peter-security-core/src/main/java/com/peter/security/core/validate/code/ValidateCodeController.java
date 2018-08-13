/**
 * 
 */
package com.peter.security.core.validate.code;

import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * 生成图形验证码接口
 * @author peter
 */
@RestController
public class ValidateCodeController {
	
	public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy(); // 操作session
	
	@Autowired
	private ValidateCodeGenerator ImageCodeGenerator;
	
	/**
	 *  1. 根据随机数生成图片
	 *  2. 将随机数存到session中
	 *  3. 在将生成的图片写到接口的响应中
	 *  4. 在browser中的signIn.html中使用
	 *   <tr>
     *       <td>图形验证码：</td>
     *       <td>
     *       	<input type="text" name="imageCode">
     *       	<img src="/code/image" />
     *       </td>
     *   </tr>
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
		ImageCode imageCode = ImageCodeGenerator.gernerate(new ServletWebRequest(request));
		sessionStrategy.setAttribute(new ServletWebRequest(request), SESSION_KEY, imageCode);
        response.setContentType("image/jpeg");
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        
		ImageIO.write(imageCode.getImage(), "JPEG", response.getOutputStream());
	}
}
