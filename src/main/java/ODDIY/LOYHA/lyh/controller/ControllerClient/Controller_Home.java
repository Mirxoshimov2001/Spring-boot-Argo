package ODDIY.LOYHA.lyh.controller.ControllerClient;

import ODDIY.LOYHA.lyh.entity.HomePage.HomePageAdvice;
import ODDIY.LOYHA.lyh.service.HomeService.HomeAdviceService;
import ODDIY.LOYHA.lyh.service.HomeService.HomeUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/home")
public class Controller_Home {


    private final HomeAdviceService homeAdviceService;
    private final HomeUserService homeUserService;

    public Controller_Home(HomeAdviceService homeAdviceService, HomeUserService homeUserService) {
        this.homeAdviceService = homeAdviceService;
        this.homeUserService = homeUserService;
    }

    @GetMapping("/advice")
    public ResponseEntity homeAdvice(){
        List<HomePageAdvice> advice = homeAdviceService.findAll();
        return ResponseEntity.ok(advice);
    }
    @GetMapping("/advice/{id}")
    public ResponseEntity homeAdviceOne(@PathVariable long id){
        return ResponseEntity.ok(homeAdviceService.findById(id));
    }

    @GetMapping("/user")
    public ResponseEntity homeUser(){
        return ResponseEntity.ok(homeUserService.findAll());
    }
}
