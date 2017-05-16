/**
 *  @author csdvedd
 */
package bh.gov.cio.gbs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.ui.velocity.VelocityEngineUtils;

/**
 * @author csdvedd
 * 
 */
public class MailUtil {
	
	private final static String MAIL_TEMPLATE_LOCATION = "mail-templates/";
	private final static String SEMICOMMA_SEPERATED = ",";

	public static void sendMail(String from, String to, String subject,
			String message, JavaMailSender mailSender) {
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		simpleMailMessage.setFrom(from);
		simpleMailMessage.setTo(to);
		simpleMailMessage.setSubject(subject);
		simpleMailMessage.setText(message);
		mailSender.send(simpleMailMessage);
	}
	
	public static void sendMailHTML(final String from, final String to,
			final String subject, final String message, JavaMailSender mailSender) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(
						mimeMessage,"UTF-8");
				messageHelper.setFrom(from);
				messageHelper.setTo(to);
				messageHelper.setSubject(subject);
				messageHelper.setSentDate(new Date());
				messageHelper.setText(message, true);
			}
		};
		mailSender.send(preparator);
	}
	
	public static void sendMailUsingVelocityTemplate(final String from,
			final String to, final String subject, JavaMailSender mailSender,
			final VelocityEngine velocityEngine, final Map<String, Object> model , final String mailTemplateName , final Date sendDate) {
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			public void prepare(MimeMessage mimeMessage) throws Exception {
				MimeMessageHelper messageHelper = new MimeMessageHelper(
						mimeMessage, "UTF-8");
				messageHelper.setFrom(from);
				messageHelper.setTo(to);
				messageHelper.setSubject(subject);
				messageHelper.setSentDate(sendDate);
				String text = VelocityEngineUtils
						.mergeTemplateIntoString(velocityEngine,
								MAIL_TEMPLATE_LOCATION + mailTemplateName,
								"UTF-8", model);
				messageHelper.setText(text, true);
			}
		};
		mailSender.send(preparator);
	}

	public static Properties loadPropsFileUsingContextLoader(String filename) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = classLoader.getResourceAsStream(filename);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	
	public static Properties loadPropsFile(String fileName){
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//		ServletContext servletContext =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getServletContext();
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = classLoader.getResourceAsStream(fileName);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
	
	public static String getSemiCommaSeperatedEmails(List<String> emails){
		String commaEmails = "";
		for (String email : emails) {
			if(email !=null)
				commaEmails += email + SEMICOMMA_SEPERATED;
		}
		return commaEmails;
	}

}
