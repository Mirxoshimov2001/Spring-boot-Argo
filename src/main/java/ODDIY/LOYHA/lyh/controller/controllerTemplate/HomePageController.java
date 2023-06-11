package ODDIY.LOYHA.lyh.controller.controllerTemplate;


import ODDIY.LOYHA.lyh.entity.HomePage.HomePageAdvice;
import ODDIY.LOYHA.lyh.entity.HomePage.HomePageUser;
import ODDIY.LOYHA.lyh.service.File.FileService;
import ODDIY.LOYHA.lyh.service.HomeService.HomeAdviceService;
import ODDIY.LOYHA.lyh.service.HomeService.HomeUserService;
import org.hashids.Hashids;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Controller
public class HomePageController {

    private final Hashids hashids;
    private final HomeAdviceService homeAdviceService;
    private final HomeUserService homeUserService;

    private final FileService fileService;

    public HomePageController(HomeAdviceService homeAdviceService, HomeUserService homeUserService, FileService fileService) {
        this.fileService = fileService;
        this.hashids = new Hashids(getClass().getName(), 6);
        this.homeAdviceService = homeAdviceService;
        this.homeUserService = homeUserService;
    }

    @GetMapping("/home_page")

    public String homePage(Model model) {
        List<HomePageAdvice> homePageAdvices = homeAdviceService.findAll();
        List<HomePageUser> homePageUsers = homeUserService.findAll();
        model.addAttribute("content_user", homePageUsers.size() != 0 ? homePageUsers : null );
        model.addAttribute("content_advice", homePageAdvices.size() != 0 ? homePageAdvices : null );
        return "HomeAll";
    }

    @GetMapping("/home_advice")
    public String Advace(Model model){
        model.addAttribute("content", null);
        return "HomeAdvice";
    }

    @GetMapping("/home_advice/{id}")
    public String findAdvice(@PathVariable long id, Model model) {
        HomePageAdvice homePageAdvices = homeAdviceService.findById(id);
        model.addAttribute("content", homePageAdvices);
        return "HomeAdvice";

    }

    @PostMapping("/home_advice")
    public String createAdvice(String nameProduct, String textProduct, String aboutUse, MultipartFile file, Model model) {

        List<HomePageAdvice> advices = homeAdviceService.findByAboutUse(aboutUse);

        if (advices.size() > 0){
            long id = advices.get(0).getId();
            updateAdvice(id, nameProduct, textProduct, aboutUse, file, model);
        }else {
            if (nameProduct.length() == 0 || file.getSize() == 0  ||textProduct.length() == 0){
                return "ErrorPage";
            }
            HomePageAdvice homePageAdvice = new HomePageAdvice();
            homePageAdvice.setAboutUse(aboutUse);
            homePageAdvice.setNameProduct(nameProduct);
            homePageAdvice.setTextProduct(textProduct);
            String hashId = hashids.encode(aboutUse.length());
            homePageAdvice.setPictureProduct_hashId(hashId);

            homeAdviceService.save(homePageAdvice);
            fileService.save(file, hashId);
        }

        return homePage(model);
    }
    @PostMapping("/home_advice/update")
    public String updateAdvice(long id, String nameProduct, String textProduct, String aboutUse, MultipartFile file, Model model) {
        HomePageAdvice homePageAdvice = homeAdviceService.findById(id);
        if (aboutUse.length() != 0)
            homePageAdvice.setAboutUse(aboutUse);

        if (nameProduct.length() != 0)
            homePageAdvice.setNameProduct(nameProduct);

        if (textProduct.length() != 0)
            homePageAdvice.setTextProduct(textProduct);

        homeAdviceService.save(homePageAdvice);
        if (file.getSize() != 0)
            fileService.save(file, homePageAdvice.getPictureProduct_hashId());


        return homePage(model);
    }


    @GetMapping("/home_advice/delete/{id}")
    public String DeleteAdvice(@PathVariable long id, Model model) {
        if (homeAdviceService.findById(id) != null) {
            homeAdviceService.delete(id);
            return homePage(model);
        }
        return "ErrorLink";
    }

    @GetMapping("/home_user")
    public String user(Model model){
        model.addAttribute("content_user", null);
        return "HomeUser";
    }
    @GetMapping("/home_user/{id}")
    public String findUser(@PathVariable long id, Model model) {
        HomePageUser homePageUsers = homeUserService.findById(id);
        model.addAttribute("content", homePageUsers);
        return "HomeUser";
    }

    @PostMapping("/home_user")
    public String createUser(String name, String surname, String email, String number, String t_me_address, String whoIs, MultipartFile file, Model model) {

       List<HomePageUser> haveuser = homeUserService.findByNumber(number);
       if (haveuser.size() > 0){
           long id = haveuser.get(0).getId();
           updateUser(id, name, surname, email, number,t_me_address, whoIs,file, model);
       }else {
            if (name.length() == 0 || file.getSize() == 0 || number.length()==0){
                return "ErrorPage";
            }

           long number_ = Long.parseLong(KRICK(number));
           HomePageUser pageUser = new HomePageUser();
           String hashId = hashids.encode(number_);
           pageUser.setName(name);
           pageUser.setSurname(surname);
           pageUser.setEmail(email);
           pageUser.setNumber(number);
           pageUser.setWhoIs(whoIs);
           pageUser.setT_me_address(t_me_address);
           pageUser.setPicture_user_hashId(hashId);

           homeUserService.save(pageUser);
           fileService.save(file, hashId);
       }
        return homePage(model);
    }
    @PostMapping("/home_user/update")
    public String updateUser(long id, String name, String surname, String email, String number, String t_me_address, String whoIs, MultipartFile file, Model model) {

        HomePageUser pageUser = homeUserService.findById(id);
        if (name.length() != 0) {
            pageUser.setName(name);
        }
        if (surname.length() != 0){
            pageUser.setSurname(surname);
        }
        if (email.length() != 0) {
            pageUser.setEmail(email);
        }
        if (number.length() != 0) {
            pageUser.setNumber(number);
        }
        if (whoIs.length() != 0) {
            pageUser.setWhoIs(whoIs);
        }
        if (t_me_address.length() != 0) {
            pageUser.setT_me_address(t_me_address);
        }

        homeUserService.save(pageUser);
        if (file.getSize() != 0) {
            fileService.save(file, pageUser.getPicture_user_hashId());
        }
        return homePage(model);
    }

    @GetMapping("/home_user/delete/{id}")
    public String DeleteUser(@PathVariable long id, Model model) {
        if (homeUserService.findById(id) != null) {
            homeUserService.delete(id);
            return homePage(model);
        }

        return "ErrorLink";
    }

    private static String KRICK(String a){
        if (a.length() <= 9){
            return a = a.replaceAll("\\s+", "");
        }
        int begin = a.length() - 9;
        String subA = a.substring(begin);
        return subA = subA.replaceAll("\\s+", "");
    }

}
