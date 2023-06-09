package ODDIY.LOYHA.lyh.controller.SecurityController;

import ODDIY.LOYHA.lyh.entity.Security.Role;
import ODDIY.LOYHA.lyh.entity.Security.User;
import ODDIY.LOYHA.lyh.entity.Security.UserRoleStatic;
import ODDIY.LOYHA.lyh.service.Mail.SendMail;
import ODDIY.LOYHA.lyh.service.SecurityService.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.jaas.SecurityContextLoginModule;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.Set;



@Controller
public class UserResours {

    private final UserService userService;

    public UserResours(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signin")
    public String SignIn(){
        return "SingIn";
    }

    @GetMapping("/loginp")
    public String LogIn(){
        return "LogIn";
    }

    @GetMapping("/home")
    public String home() {
        return "TheHelp";
    }
    @PostMapping("/register")
    public String create(String userName, String email, Model model){
        User user = new User();
        SendMail mail = new SendMail();
        Set<Role> role = new HashSet<>();
        Role roles = new Role();
        roles.setName(UserRoleStatic.ROLE_ADMIN);
        role.add(roles);

        String password = String.valueOf( (long) (Math.random() * 1000000));

                try {
                    if (cheeckPassWord(password)) {
                        if (!userService.cheeckUserName(userName)) {
                                mail.SendMail("isfandiyor3005@gmail.com", password, "Yangi Foydalanuchi Uchun Kod");
                                user.setUserName(userName);
                                user.setPassword(password);
                                user.setEmail(email);
                                user.setRoles(role);
                                userService.create(user);
                            return "LogIn";
                        }
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                }
        return "ErrorPage";
    }

    private Boolean cheeckPassWord(String password){
        return password.length() > 4 && password.length() < 8;
    }

}
