/**
 * 
 */
package com.peter.security.core.properties;

/**
 *  图形验证码的默认配置，默认长度是4位
 * @author peter
 */
public class ImageCodeProperties extends SmsCodeProperties {
	private int width = 67;
	private int height = 23;
	
	public ImageCodeProperties() {
		setLength(4);
	}
	
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
}
