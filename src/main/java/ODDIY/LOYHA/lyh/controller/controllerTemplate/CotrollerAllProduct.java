package ODDIY.LOYHA.lyh.controller.controllerTemplate;

import ODDIY.LOYHA.lyh.service.ContentService;
import ODDIY.LOYHA.lyh.entity.Content;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CotrollerAllProduct {

    private final ContentService contentService;

    public CotrollerAllProduct(ContentService contentService) {
        this.contentService = contentService;
    }


    @GetMapping("/all_product")
    public String getAll(Model model){
        List<Content> contents = contentService.findAll();
        model.addAttribute("contents", contents);
        return "AllProduct";
    }
}
