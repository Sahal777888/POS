package com.app.pos2.controller;

import com.app.pos2.model.CustomerDemoModel;
import com.app.pos2.model.CustomerDemographicModel;
import com.app.pos2.model.CustomersModel;
import com.app.pos2.service.CustomerDemoService;
import com.app.pos2.service.CustomerDemographicService;
import com.app.pos2.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/customerdemo")
public class CustomerDemoController {
    private CustomerDemoService service;
    private CustomersService customersService;
    private CustomerDemographicService customerDemographicService;
    @Autowired
    public CustomerDemoController(CustomerDemoService service,CustomersService customersService,CustomerDemographicService customerDemographicService){
        this.service=service;
        this.customersService=customersService;
        this.customerDemographicService=customerDemographicService;
    }

    @GetMapping
    public ModelAndView index(){
        List<CustomerDemoModel> result=this.service.get();
        ModelAndView view=new ModelAndView("customerdemo/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view= new ModelAndView("customerdemo/addpop.html");
        view.addObject("data",new CustomerDemoModel());
        List<CustomersModel> customersModelList=this.customersService.get();
        List<CustomerDemographicModel> customerDemographicModelList=this.customerDemographicService.get();
        view.addObject("customerList",customersModelList);
        view.addObject("customerDemographicList",customerDemographicModelList);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        CustomerDemoModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/customerdemo");
        }
        ModelAndView view=new ModelAndView("customerdemo/detailpop.html");
        view.addObject("data",model);
        List<CustomersModel> customersModelList=this.customersService.get();
        List<CustomerDemographicModel> customerDemographicModelList=this.customerDemographicService.get();
        view.addObject("customerList",customersModelList);
        view.addObject("customerDemographicList",customerDemographicModelList);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        CustomerDemoModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/customerdemo");
        }
        ModelAndView view=new ModelAndView("customerdemo/editpop.html");
        view.addObject("data",model);
        List<CustomersModel> customersModelList=this.customersService.get();
        List<CustomerDemographicModel> customerDemographicModelList=this.customerDemographicService.get();
        view.addObject("customerList",customersModelList);
        view.addObject("customerDemographicList",customerDemographicModelList);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/customerdemo");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") CustomerDemoModel request, BindingResult result){
        ModelAndView view = new ModelAndView("customerdemo/addpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","customerId","This coupling data have been used");
            result.addError(fieldError);
            FieldError fieldError2 = new FieldError("data","customerDemographicId","This coupling data have been used");
            result.addError(fieldError2);
        }
        if(result.hasErrors()){
            List<CustomersModel> customersModelList=this.customersService.get();
            List<CustomerDemographicModel> customerDemographicModelList=this.customerDemographicService.get();
            view.addObject("customerList",customersModelList);
            view.addObject("customerDemographicList",customerDemographicModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/customerdemo");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") CustomerDemoModel request, BindingResult result){
        ModelAndView view = new ModelAndView("customerdemo/editpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","customerId","This coupling data have been used");
            result.addError(fieldError);
            FieldError fieldError2 = new FieldError("data","customerDemographicId","This coupling data have been used");
            result.addError(fieldError2);
        }
        if(result.hasErrors()){
            List<CustomersModel> customersModelList=this.customersService.get();
            List<CustomerDemographicModel> customerDemographicModelList=this.customerDemographicService.get();
            view.addObject("customerList",customersModelList);
            view.addObject("customerDemographicList",customerDemographicModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/customerdemo");
    }
}
