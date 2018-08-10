/**
 * 
 */
package com.peter.exception;

/**
 * @author peter
 *
 */
public class UserNotExistException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5537098949394621404L;

	private String id;
	
	public UserNotExistException(String id) {
		super("user not exist");
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
