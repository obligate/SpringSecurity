/**
 * 
 */
package com.peter.security.core.validate.code.image;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

import com.peter.security.core.validate.code.ValidateCode;

/**
 * @author peter
 *
 */
public class ImageCode extends ValidateCode {
	
	private BufferedImage image;
	
	public ImageCode(BufferedImage image, String code, int expireSecond) {
		super(code, expireSecond);
		this.image = image;
	}
	
	public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
		super(code, expireTime);
		this.image = image;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}
}
