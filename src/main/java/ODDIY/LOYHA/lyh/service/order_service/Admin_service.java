package ODDIY.LOYHA.lyh.service.order_service;


import ODDIY.LOYHA.lyh.entity.Content;
import ODDIY.LOYHA.lyh.service.ContentService;
import ODDIY.LOYHA.lyh.entity.Admin_menu.Admin_entity;
import ODDIY.LOYHA.lyh.repository.Admin_repo;
import ODDIY.LOYHA.lyh.service.Mail.SendMail;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;

@Service

public class Admin_service {

    private final Admin_repo adminRepo;
    private final ContentService contentService;


    public Admin_service(Admin_repo adminRepo, ContentService contentService) {
        this.adminRepo = adminRepo;
        this.contentService = contentService;

    }

    public boolean Create_user(Admin_entity entity) {
        if (entity != null) {
            Content content = contentService.findById(entity.getContent_id());
            entity.setContent_name(content.getTitle());
            adminRepo.save(entity);
            return true;
        }


        return false;
    }

    public List<Admin_entity> findAll() {
        return adminRepo.findAll();
    }

    public Admin_entity findOne(long id) {
        return adminRepo.findById(id);
    }

    public void delete_order(long id) {
        adminRepo.deleteById(id);
    }

    @Scheduled(cron = "* 30 23 * * *")
    public void delete() throws MessagingException {
        List<Admin_entity> orders = findAll();

        Date date = new Date();
        for (Admin_entity order : orders) {
            if (order.getNewDate().getDate() + 1 <= date.getDate()) {
                 deleteCenceledproduct(order);
            }
        }
        System.out.println("Muddati o'tgan buyurmalar o'chirildi");
    }

    public void Cenceled(long id){
        Admin_entity order = findOne(id);
        deleteCenceledproduct(order);
        System.out.println("Buyurtma Admin tomonidan bekor qilindi");
    }

    private void deleteCenceledproduct(Admin_entity order){
        SendMail SendMessage = new SendMail();
        Content content = contentService.findById(order.getContent_id());
        content.setQuality(content.getQuality() + order.getOrder_Quality());
        contentService.Crete(content);
        try{
            SendMessage.SendMail(order.getEmail(),
                    "Hurmatli Mijoz! Sizning buyiurmangiz Bekor Qilinganligini Malum Qilamiz",
                    "Ogohlantirish Xati!");
        }catch (MessagingException e){
            System.out.println(e.getMessage());
        }
        delete_order(order.getId());
    }
}
