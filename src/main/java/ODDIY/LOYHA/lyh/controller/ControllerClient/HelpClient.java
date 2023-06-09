package ODDIY.LOYHA.lyh.controller.ControllerClient;

import ODDIY.LOYHA.lyh.service.order_service.Help;
import ODDIY.LOYHA.lyh.entity.Admin_menu.Help_Client;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api/help")
public class HelpClient {

    private final Help help;


    public HelpClient(Help help) {
        this.help = help;
    }

    @GetMapping("/help")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(help.findAll());
    }

//    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/help/post")
    public ResponseEntity postOne(@RequestBody Help_Client helpEntity){
       if (validation(helpEntity.getEmail())) {
           help.save(helpEntity);
           System.out.println("help malumotlarini oldim");
           return ResponseEntity.ok("Sizning murojatingiz qabul qilindi javobi tez orada olasiz");
       }
       return ResponseEntity.ok("Kiritgan Email manzilingiz haqiqiy emas haqiqiy emas");
    }

    private static boolean validation(String mail){
        String com = mail.substring((int)(mail.lastIndexOf('.')+1));
        if (com.equals("com")) {
            char[] amail = mail.toCharArray();
            for (char element: amail) {
                if (element == '@'){
                    return true;
                }
            }
        }

        return false;
    }
}
