package ODDIY.LOYHA.lyh.controller.controllerTemplate;

import ODDIY.LOYHA.lyh.entity.Admin_menu.Admin_entity;
import ODDIY.LOYHA.lyh.service.order_service.Admin_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller

public class ControllerTemplate {

    private final Admin_service admin_service;
    @Autowired
    public ControllerTemplate(Admin_service admin_service) {
        this.admin_service = admin_service;
    }

    @GetMapping("/")

        public String index(Model model) {
        List<Admin_entity> order_all = admin_service.findAll();
        if (order_all.size() == 0){
            model.addAttribute("order", null);
        }

        model.addAttribute("order",order_all);
        return "AdminTemplate";
        }


    @GetMapping("/order_delete/{id}")
    public String delete(@PathVariable long id, Model model){
      if (admin_service.findOne(id) != null) {
          admin_service.delete_order(id);

          List<Admin_entity> order_all = admin_service.findAll();
          if (order_all.size() == 0) {
              model.addAttribute("order", null);
          }


          model.addAttribute("order", order_all);
          return "AdminTemplate";
      }

      return "ErrorLink";
    }

    @GetMapping("/order_cencel/{id}")
    public String cenceled(@PathVariable long id, Model model){
        if (admin_service.findOne(id) != null) {
            admin_service.Cenceled(id);
            List<Admin_entity> order_all = admin_service.findAll();
            if (order_all.size() == 0) {
                model.addAttribute("order", null);
            }
            model.addAttribute("order", order_all);
            return "AdminTemplate";
        }

        return "ErrorLink";
    }
}
