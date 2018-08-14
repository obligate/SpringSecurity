/**
 * 
 */
package com.peter.security.core.properties;

/**
 *  短信验证码的默认配置，默认长度是6位
 * @author peter
 */
public class SmsCodeProperties {
	private int length = 6;
	private int expireIn = 60;
	private String url;
	
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public int getExpireIn() {
		return expireIn;
	}
	public void setExpireIn(int expireIn) {
		this.expireIn = expireIn;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
