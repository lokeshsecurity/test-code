/**
 *  @author CSDVEDD
 */
package bh.gov.cio.gbs.util;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;

/**
 * @author CSDVEDD
 *
 */
public class SecurityUtil {
	
	public static String hashPassword(String password) throws Throwable {
		final String ENCODING = "UTF8";
		String result = password;
		String algorithm = "SHA";
		MessageDigest md = null;
		if (password != null) {
			md = MessageDigest.getInstance(algorithm);
			byte[] bytes = null;
			bytes = password.getBytes(ENCODING);
			md.update(bytes);
			byte[] raw = md.digest();
			result = new String(Base64.encodeBase64(raw));
		}
		return result;
	}

}
