package com.app.pos2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@Controller
public class HomeController {


    @GetMapping("/")
    //public ModelAndView index(){ return new ModelAndView("products/index"); }
    public ModelAndView index(){ return new ModelAndView("pages/home1"); }

    @GetMapping("/home")
    //public ModelAndView home(){ return new ModelAndView("products/index"); }
    public ModelAndView home(){ return new ModelAndView("pages/home1");}

    @GetMapping("/dashboard")
    //public ModelAndView dashboard(){ return new ModelAndView("products/index"); }
    public ModelAndView dashboard(){ return new ModelAndView("pages/home1"); }

    @GetMapping("/login")
    public ModelAndView login(){
        return new ModelAndView("pages/login");
    }
}
