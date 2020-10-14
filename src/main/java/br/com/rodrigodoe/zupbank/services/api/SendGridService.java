package br.com.rodrigodoe.zupbank.services.api;

import com.sendgrid.Response;
import com.sendgrid.helpers.mail.objects.Email;

public interface SendGridService {

	public Response sendMailConfirmation(String fromMail, String String);

}
