package com.peter.security.core.validate.code.sms;

/**
 * 默认的短信发送，为了让别人覆盖需要在@see ValidateCodeBeanConfig 配置一下
 * @author peter
 *
 */
public class DefaultSmsCodeSender implements SmsCodeSender {

	@Override
	public void send(String mobile, String code) {
		System.out.println(String.format("向手机%s发送短信验证码%s", mobile,code));
	}
}
