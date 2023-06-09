package ODDIY.LOYHA.lyh.service.order_service;

import ODDIY.LOYHA.lyh.repository.order_content.HelpRepository;
import ODDIY.LOYHA.lyh.entity.Admin_menu.Help_Client;
import ODDIY.LOYHA.lyh.entity.Admin_menu.Help_entity;
import ODDIY.LOYHA.lyh.service.Mail.SendMail;
import org.jetbrains.annotations.Async;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.List;

@Service
public class Help {

    private final HelpRepository HELP_REPOSITORY;

    public Help(HelpRepository help_repository) {
        HELP_REPOSITORY = help_repository;
    }

    public List<Help_entity> findAll(){
        return HELP_REPOSITORY.findAll();
    }

    public Help_entity findById(long id){
        return HELP_REPOSITORY.findById(id);
    }

    public void save(@NotNull Help_Client entity){
        Help_entity helpEntity = new Help_entity();
        helpEntity.setName(entity.getName());
        helpEntity.setQuery(entity.getQuery());
        helpEntity.setEmail(entity.getEmail());
        HELP_REPOSITORY.save(helpEntity);
    }

    public void save(long id, String ansever){
        Help_entity helpEntity = HELP_REPOSITORY.findById(id);
        helpEntity.setAnsever(ansever);


        SendMail sendMail = new SendMail();

        try {
            sendMail.SendMail(helpEntity.getEmail(), helpEntity.getAnsever(), helpEntity.getQuery());
        } catch (MessagingException e) {
//            throw new RuntimeException(e);
            System.out.println(e.getMessage());
        }

        HELP_REPOSITORY.save(helpEntity);

    }

    public void delete(long id){
        HELP_REPOSITORY.delete(findById(id));
    }

    @Scheduled(cron = "* 07 17 30 * *")
    public void deleteMessage(){
        for (Help_entity entity: findAll()) {
            delete(entity.getId());
        }
    }


}
