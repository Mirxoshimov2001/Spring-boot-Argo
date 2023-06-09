package ODDIY.LOYHA.lyh.repository.Mail;

import ODDIY.LOYHA.lyh.entity.Mail.EmailDetails;

public interface SendMail {

    String sendSimpleMail(EmailDetails details);


    String sendMailWithAttachment(EmailDetails details);
}
