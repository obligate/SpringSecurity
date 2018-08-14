package com.peter.security.core.validate.code.impl;

import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.context.request.ServletWebRequest;

import com.peter.security.core.validate.code.ValidateCode;
import com.peter.security.core.validate.code.ValidateCodeGenerator;
import com.peter.security.core.validate.code.ValidateCodeProcessor;

public abstract class AbstractValidateCodeProcessor<C extends ValidateCode> implements ValidateCodeProcessor {

	/**
	 * 操作session的工具类
	 */
	private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
	/**
	 * 收集系统中所有的 {@link ValidateCodeGenerator} 接口的实现。
	 * 依赖搜索查找
	 * 启动的时候spring会在spring容器查找所有ValidateCodeGenerator的实现，放到map中，其中key就是bean name
	 */
	@Autowired
	private Map<String, ValidateCodeGenerator> validateCodeGenerators;

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
	 */
	@Override
	public void create(ServletWebRequest request) throws Exception {
		C validateCode = generate(request);
		save(request, validateCode);
		send(request, validateCode);
	}
	
	/**
	 * 生成校验码
	 * 
	 * @param request
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private C generate(ServletWebRequest request) {
		String type = getProcessorType(request);
		ValidateCodeGenerator validateCodeGenerator = validateCodeGenerators.get(type + "CodeGenerator");
		return (C) validateCodeGenerator.generate(request);
	}

	/**
	 * 保存校验码
	 * @param request
	 * @param validateCode
	 */
	private void save(ServletWebRequest request, C validateCode) {
		sessionStrategy.setAttribute(request, SESSION_KEY_PREFIX + getProcessorType(request).toUpperCase(),
				validateCode);
	}

	/**
	 * 发送校验码，由子类实现
	 * @param request
	 * @param validateCode
	 * @throws Exception
	 */
	protected abstract void send(ServletWebRequest request, C validateCode) throws Exception;
	/**
	 * 根据请求的url获取校验码的类型
	 * @param request
	 * @return
	 */
	private String getProcessorType(ServletWebRequest request) {
		return StringUtils.substringAfter(request.getRequest().getRequestURI(), "/code/");
	}
}
