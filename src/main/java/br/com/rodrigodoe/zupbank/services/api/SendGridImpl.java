package br.com.rodrigodoe.zupbank.services.api;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

public class SendGridImpl implements SendGridService {

	private Logger logger = LoggerFactory.getLogger(SendGridImpl.class);

	@Autowired
	private SendGrid sendGrind;

	public Response sendMailConfirmation(String fromMail, String toMail) {
		String subject = "Parabéns você acaba de se tornar mais um cliente no nosso serviço";
		Content content = new Content("text/plain",
				"Obrigado por aceitar a proposta em breve você receberá informacoes sobre sua conta");
		Request request = new Request();
		request.setMethod(Method.POST);
		request.setEndpoint("mail/send");
		Mail mail = new Mail(new Email(fromMail), subject, new Email(toMail), content);
		try {
			request.setBody(mail.build());
			Response response = sendGrind.api(request);
			return response;
		} catch (IOException e) {
			logger.info("sCaught an IOException from sendgrid API");
			logger.info("Cause:   " + e.getCause());
			logger.info("Message: " + e.getMessage());
			return null;
		}
	}

}
