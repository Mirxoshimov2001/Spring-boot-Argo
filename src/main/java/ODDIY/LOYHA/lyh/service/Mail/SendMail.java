package ODDIY.LOYHA.lyh.service.Mail;




import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

// Emailni yuborish funksiyasi


public class SendMail {
    public void SendMail(String toName, String body, String subject) throws MessagingException {
        // Elektron xabar uchun sozlamalar
        String host = "smtp.gmail.com";
        String port = "587";
        String username = "isf.message.info.2001@gmail.com";
        String password = "tjaoprhzpzomqeeu";

        // Sozlamalarni foydalanish uchun Properties obyekti yaratamiz
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Yangi seans yaratamiz va autentifikatsiyadan o'tish uchun yaroqli ma'lumotlarni bermoqda
        Session session = Session.getInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        // Yangi elektron xabar obyekti yaratamiz
        Message message = new MimeMessage(session);

        // Xabarni yuborishchi manzilini va mavzusini belgilash
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toName));
        message.setSubject("Savolingizga jvob berayotganimizdan mamnunmiz!");

        // Xabarning matnini joylash
        message.setText("SAVOL: " + subject +"\n" + "JAVOB: " + body);

        // Xabarni yuborish
        Transport.send(message);

        System.out.println("Xabar yuborildi!");
    }
}