package com.app.pos2.controller;

import com.app.pos2.model.CustomersModel;
import com.app.pos2.model.EmployeesModel;
import com.app.pos2.service.CustomersService;
import com.app.pos2.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Slf4j
@Controller
@RequestMapping("/employees")
public class EmployeesController {
    private EmployeesService service;
    @Autowired
    public EmployeesController(EmployeesService service){this.service=service;}

    @GetMapping
    public ModelAndView index(){
        List<EmployeesModel> result=this.service.get();
        ModelAndView view=new ModelAndView("employees/index.html");
        view.addObject("dataList",result);/*
        log.info("@#$ result 1: {}",result.get(0).getReportId());
        log.info("@#$ result 2: {}",result.get(1).getReportId());
        log.info("@#$ result 3: {}",result.get(2).getReportId());
        log.info("@#$ result 4: {}",result.get(3).getReportId());
        log.info("@#$ result 5: {}",result.get(4).getReportId());
        log.info("@#$ result 6: {}",result.get(5).getReportId());
        */
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view =new ModelAndView("employees/addpop.html");
        view.addObject("data",new EmployeesModel());
        List<EmployeesModel> employeesModelList=this.service.get();
        view.addObject("reportList",employeesModelList);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        EmployeesModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/employees");
        }
        ModelAndView view=new ModelAndView("employees/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        EmployeesModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/employees");
        }
        ModelAndView view=new ModelAndView("employees/editpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/employees");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") EmployeesModel request, BindingResult result){
        ModelAndView view = new ModelAndView("employees/addpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","firstName","this coupling name already taken");
            result.addError(fieldError);
        }
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","lastName","this coupling name already taken");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            List<EmployeesModel> employeesModelList=this.service.get();
            view.addObject("reportList",employeesModelList);
            return view;
        }
        //log.info("report id: {}",request.getReportId());
        this.service.save(request);
        return new ModelAndView("redirect:/employees");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") EmployeesModel request, BindingResult result){
        ModelAndView view = new ModelAndView("employees/editpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","firstName","Code '"+ request.getFirstName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            List<EmployeesModel> employeesModelList=this.service.get();
            view.addObject("reportList",employeesModelList);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/employees");
    }
}
