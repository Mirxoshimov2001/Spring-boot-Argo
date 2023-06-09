package ODDIY.LOYHA.lyh.controller.controllerTemplate;


import ODDIY.LOYHA.lyh.entity.Content;
import ODDIY.LOYHA.lyh.service.ContentService;
import ODDIY.LOYHA.lyh.service.File.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class ControllerUpdate {

    private final ContentService contentService;
    private final FileService fileService;

    public ControllerUpdate(ContentService contentService, FileService fileService) {
        this.contentService = contentService;
        this.fileService = fileService;
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id, Model model){
        if (id != 0){
            contentService.Delete(id);
            List<Content> contents = contentService.findAll();
            model.addAttribute("contents", contents);
            return "AllProduct";

        }

        return "berilgan id bo'yicha malumot topilmadi";
    }

    @GetMapping("*")
    public String Error(){
        return "ErrorLink";
    }

    @GetMapping("/Update_template/{id}")
    public String getOne(@PathVariable long id, Model model){
        model.addAttribute("content", findContent(id));
        return "UpdateTemplate";
    }

    @PostMapping("/Update_template")
    public String Update(long id,
                         String title,
                         String content,
                         String quality,
                         String price,
                         String classes,
                         String aboutContent,
                         MultipartFile file,
                         Model model){
        Content content1 = findContent(id);
//        Content content1 = new Content()
//        content1.setId(id);
//        content1.setTitle(title);
//        content1.setContent(content);
//        content1.setClasses(classes);
//        content1.setPrice(price);
//        content1.setQuality(Long.parseLong(quality));
//        content1.setAboutContent(aboutContent);
        if (file.getSize() != 0) fileService.save(file,content1.getContentFileHashId() );
        if (content1 != null){
            if (title.length() != 0 ) content1.setTitle(title);
            if (content != null ) content1.setContent(content);
            if (classes.length() != 0) content1.setClasses(classes);
            if (price.length() != 0) content1.setPrice(price);
            if (quality.length() != 0) content1.setQuality(Long.parseLong(quality));
            if (aboutContent.length() != 0) content1.setAboutContent(aboutContent);

            contentService.Crete(content1);
            List<Content> contents = contentService.findAll();
            model.addAttribute("contents", contents);
            return "AllProduct";
        }

        return "fileNull";
    }

    private Content findContent(long id){
       return contentService.findById(id);
    }
    private void UpdateFile(MultipartFile file, String hashId){
        fileService.save(file, hashId);
    }

}
