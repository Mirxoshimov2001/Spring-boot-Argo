package ODDIY.LOYHA.lyh.controller.controllerTemplate;

import ODDIY.LOYHA.lyh.entity.Content;
import ODDIY.LOYHA.lyh.service.ContentService;
import ODDIY.LOYHA.lyh.service.File.FileService;
import ODDIY.LOYHA.lyh.service.order_service.Admin_service;
import org.hashids.Hashids;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller

public class ControllerUploder {

    private final Admin_service admin_service;
    private final ContentService Service;
    private final Hashids hashids;
    private final FileService fileService;

    public ControllerUploder(Admin_service admin_service, ContentService service, FileService fileService) {
        this.admin_service = admin_service;
        Service = service;
        this.hashids = new Hashids(getClass().getName(), 10);

        this.fileService = fileService;
    }


    @GetMapping("/uplode_content")
    public String getUplode(){
        return "UplodeContentTemplate";
    }

    @PostMapping ("/uplode_content")
    public String upoode( String title,
                          String content,
                          String quality,
                          String price,
                          String classes,
                          String aboutContent,
                          MultipartFile file) {


        if (validateTitle(title) != null){
            return validateTitle(title);
        }
        if (file.getSize() == 0 || validationExt(file.getOriginalFilename())){
            return "fileNull";
        }

        if (!validate(title, price, quality)) {
            return "ErrorPage";
        }

        Content content_new = Service.Crete(createContent(content, title, quality, price, classes, aboutContent));

        String hashId = hashids.encode(content_new.getId());
        content_new.setContentFileHashId(hashId);
        createAndUpdate(content_new);
        fileService.save(file,hashId);


        return "UplodeContentTemplate";
    }

    private boolean validationExt(String originalFilename) {
        String ext = fileService.OutExt(originalFilename);

        if (ext.equals("jpg" ) || ext.equals("png")){
            return false;
        }

        return true;
    }

    private Content createAndUpdate(Content content_new){
        return  Service.Crete(content_new);
    }
    private String validateTitle(String title){
        List<Content> content1 = Service.findByContent(title);
        if(content1.size() > 0){
            return "ErrorTitle";
        }

        return null;
    }

    private boolean validate(String title, String price, String quality){
        if (title.length() == 0 || title == null ||
                price.length() == 0 || price == null ||
                quality.length() == 0 || quality == null){
             return false;
        }

        return true;
    }

    private Content createContent(String content, String title, String quality, String price, String classes, String aboutContent){
        Content newContent = new Content();
        newContent.setContent(content);
        newContent.setTitle(title);
        newContent.setQuality(Integer.parseInt(quality));
        newContent.setPrice(price);
        newContent.setClasses(classes);
        newContent.setAboutContent(aboutContent);
        return  newContent;
    }

}

