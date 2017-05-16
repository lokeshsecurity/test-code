package bh.gov.cio.gbs.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class PasswordEncoderGenerator {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(PasswordEncoderGenerator.class);
	
	 public static void main(String[] args) {
		 
		String password = "123456789";
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		logger.info(hashedPassword);
		 
		 
	 }


}
