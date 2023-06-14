package com.app.pos2.controller;

import com.app.pos2.model.CustomerDemographicModel;
import com.app.pos2.service.CustomerDemographicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customerdemographic")
public class CustomerDemographicController {
    private CustomerDemographicService service;
    @Autowired
    public CustomerDemographicController(CustomerDemographicService service){this.service=service;}

    @GetMapping
    public ModelAndView index(){
        List<CustomerDemographicModel> result=this.service.get();
        ModelAndView view=new ModelAndView("customerdemographic/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("customerdemographic/addpop.html");
        view.addObject("data",new CustomerDemographicModel());
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        CustomerDemographicModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/customerdemographic");
        }
        ModelAndView view=new ModelAndView("customerdemographic/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        CustomerDemographicModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/customerdemographic");
        }
        ModelAndView view=new ModelAndView("customerdemographic/editpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/customerdemographic");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") CustomerDemographicModel request, BindingResult result){
        ModelAndView view = new ModelAndView("customerdemographic/addpop.html");
        if(Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("data","customerDesc","Code '"+ request.getCustomerDesc() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/customerdemographic");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") CustomerDemographicModel request, BindingResult result){
        ModelAndView view = new ModelAndView("customerdemographic/editpop.html");
        if(Boolean.FALSE.equals(service.checkUpdate(request))){
            FieldError fieldError = new FieldError("data","customerDesc","Code '"+ request.getCustomerDesc() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/customerdemographic");
    }
}
