package ODDIY.LOYHA.lyh.controller.ControllerClient;

import ODDIY.LOYHA.lyh.entity.Content;
import ODDIY.LOYHA.lyh.service.ContentService;
import ODDIY.LOYHA.lyh.service.order_service.Admin_service;
import ODDIY.LOYHA.lyh.entity.Admin_menu.Admin_entity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/api/order")
public class Controller_content_order {

    private final ContentService CONTENT_SERVICE;
    private final Admin_service admin_service;

    public Controller_content_order(ContentService content_service, Admin_service admin_service) {
        this.CONTENT_SERVICE = content_service;
        this.admin_service = admin_service;
    }

    @PostMapping("/order_content")
    public ResponseEntity orderContent(@RequestBody Admin_entity order_content) {

        Date nowDate = new Date();
       if ( controller_orderAndContent(order_content)){
           order_content.setNewDate(nowDate);
           if(admin_service.Create_user(order_content)){
               return ResponseEntity.ok("hurmatli mijoz buyurtmangiz qabul qilindi... \n Eslatib o'tamiz, bir kun ichida olib ketilmasa, buruytma bekor qilingan deb hisoblanadi...");
           }

       }

       return ResponseEntity.ok("nimadur hato ketdi... maxsulot yetarli emas");
    }

    @GetMapping("/orders")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(admin_service.findAll());
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(long id){
        if (getOne(id) != null){
            admin_service.delete_order(id);
            return ResponseEntity.ok("buyurma o'chirildi...");
        }
        return ResponseEntity.ok("buyurtma allaqachon bekor qilingan yoki mavjud emas...");
    }

    @GetMapping("order/{id}")
    public ResponseEntity getOne(@RequestParam long id){
        return ResponseEntity.ok(admin_service.findOne(id));
    }
    private boolean controller_orderAndContent(Admin_entity order_content){


        Content content = CONTENT_SERVICE.findById(order_content.getContent_id());
        if (content.getQuality() == 0 || order_content == null){
            return false;
        }
        long residual = content.getQuality() - order_content.getOrder_Quality();
        if (residual < 0){
            return false;
        }


        content.setQuality(residual);
        CONTENT_SERVICE.Crete(content);
        return true;
    }

    private static boolean validation(Admin_entity entity){
        if (!true){}
        return false;
    }
}
