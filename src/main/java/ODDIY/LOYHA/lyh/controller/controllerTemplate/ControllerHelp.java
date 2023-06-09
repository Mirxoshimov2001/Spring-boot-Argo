package ODDIY.LOYHA.lyh.controller.controllerTemplate;


import ODDIY.LOYHA.lyh.service.order_service.Help;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerHelp {

    private final Help help;

    public ControllerHelp(Help help) {
        this.help = help;
    }


    @GetMapping("/help_admin")
    public String help(Model model){
        model.addAttribute("components", help.findAll().size() == 0 ? null : help.findAll() );
        return "TheHelp";
    }

    @GetMapping("/help_admin/{id}")
    public String helpAnsever(@PathVariable long id, Model model){
        model.addAttribute("component", help.findById(id));
        return "AnseverHelp";
    }
    @GetMapping("help_admin/delete/{id}")
    public String deleteMessage(@PathVariable long id, Model model){
        if (help.findById(id) != null) {
            help.delete(id);
        return help(model);
        }
        return "ErrorLink";
    }
    @PostMapping("/help_Admin")
    public String Ansever(long id, String ansever, Model model){
         ansever = ansever.trim();
        if (ansever != null && ansever != "") {
            help.save(id, ansever);
            model.addAttribute("components", help.findAll().size() == 0 ? null : help.findAll());
            System.out.println(ansever + "bo'shliq");
            return "TheHelp";
        }
        System.out.println(ansever);
        return helpAnsever(id, model);
    }
}
