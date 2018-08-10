/**
 * 
 */
package com.peter.security.core.properties;

/**
 * 验证码配置，图形验证码，短信验证码
 * @author peter
 *
 */
public class ValidateCodeProperties {
	private ImageCodeProperties image = new ImageCodeProperties();

	public ImageCodeProperties getImage() {
		return image;
	}
	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}
}
