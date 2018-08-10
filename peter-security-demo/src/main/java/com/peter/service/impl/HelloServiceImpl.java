/**
 * 
 */
package com.peter.service.impl;

import org.springframework.stereotype.Service;

import com.peter.service.HelloService;

/**
 * @author peter
 *
 */
@Service
public class HelloServiceImpl implements HelloService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.peter.service.HelloService#greeting(java.lang.String)
	 */
	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello " + name;
	}

}
