/**
 * 
 */
package com.peter.security.core.validate.code;

import org.springframework.security.core.AuthenticationException;

/**
 * @author peter
 *
 */
public class ValidateCodeException extends AuthenticationException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8866381804810510619L;

	public ValidateCodeException(String msg) {
		super(msg);
	}

}
