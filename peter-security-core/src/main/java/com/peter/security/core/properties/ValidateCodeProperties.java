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
	private SmsCodeProperties sms = new SmsCodeProperties();

	public ImageCodeProperties getImage() {
		return image;
	}
	public void setImage(ImageCodeProperties image) {
		this.image = image;
	}
	public SmsCodeProperties getSms() {
		return sms;
	}
	public void setSms(SmsCodeProperties sms) {
		this.sms = sms;
	}
}
