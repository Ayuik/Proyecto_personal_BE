package dev.ayelen.mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(MailRequest mailRequest) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

        helper.setFrom("marcosayelen@gmail.com");
        helper.setTo("marcosayelen@gmail.com");  
        helper.setSubject("Nuevo mensaje de contacto de " + mailRequest.getName());

        String text = "Has recibido un nuevo mensaje de contacto:\n\n" +
                "Nombre: " + mailRequest.getName() + "\n" +
                "Email: " + mailRequest.getEmail() + "\n" +
                "Asunto: " + mailRequest.getSubject() + "\n\n" +
                "Mensaje:\n" + mailRequest.getMessage();
        helper.setText(text, false);

        mailSender.send(mimeMessage);
    }

}
