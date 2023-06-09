package ODDIY.LOYHA.lyh.controller.ControllerClient;



import ODDIY.LOYHA.lyh.service.ContentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/content")
public class ContentController {

    private final ContentService Service;
    public ContentController(ContentService service) {
        Service = service;

    }

    @GetMapping("/get_all")
    public ResponseEntity<?> getAll(){

        return  ResponseEntity.ok(Service.findAll());
    }

    @GetMapping("/get_one/{id}")
    public ResponseEntity getOne(@PathVariable long id){
        if (id != 0){
            return ResponseEntity.ok(Service.findById(id));
        }

        return ResponseEntity.ok("berilgan id bo'yicha malumot topilmadi");
    }


    @GetMapping("/get_all/{clsses}")
    public ResponseEntity getClasses(@PathVariable String clsses){
        return ResponseEntity.ok(Service.findByClass(clsses));
    }



}
