package freelance.new_syria_v2.auth.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;

@Service
public class EmailService  implements EmailSend{

	@Value("${spring.mail.username}")
	private String emailSender;
	
	private final Logger LOGGER=LoggerFactory.getLogger(EmailService.class);
	private final JavaMailSender  mailSender;
	
	public EmailService(JavaMailSender mailSender) {
		super();
		this.mailSender = mailSender;
	}

	
	@Override
	@Async
	public void send(String to, String email) {
	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
	        helper.setText(email, true);
	        helper.setTo(to);
	        helper.setSubject("Confirm your email in new_syria");
	        helper.setFrom(emailSender);
	        
	        mailSender.send(message);

	    } catch (MessagingException e) {
	        LOGGER.error("failed to send email " + email, e);
	        throw new IllegalStateException("failed to send email");
	    }
	}

	
}
