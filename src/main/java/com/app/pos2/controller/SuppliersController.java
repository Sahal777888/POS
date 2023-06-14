package com.app.pos2.controller;

import com.app.pos2.model.SuppliersModel;
import com.app.pos2.service.SuppliersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/suppliers")
public class SuppliersController {
    private SuppliersService service;
    @Autowired
    public SuppliersController(SuppliersService service){this.service=service;}

    @GetMapping
    public ModelAndView index(){
        List<SuppliersModel> result=this.service.get();
        ModelAndView view=new ModelAndView("suppliers/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view = new ModelAndView("suppliers/addpop.html");
        view.addObject("data",new SuppliersModel());
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        SuppliersModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/suppliers");
        }
        ModelAndView view=new ModelAndView("suppliers/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        SuppliersModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/suppliers");
        }
        ModelAndView view=new ModelAndView("suppliers/editpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/suppliers");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") SuppliersModel request, BindingResult result){
        ModelAndView view = new ModelAndView("suppliers/addpop.html");
        if(Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("data","companyName","Code '"+ request.getCompanyName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/suppliers");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") SuppliersModel request, BindingResult result){
        ModelAndView view = new ModelAndView("suppliers/editpop.html");
        if(Boolean.FALSE.equals(service.checkUpdate(request))){
            FieldError fieldError = new FieldError("data","companyName","Code '"+ request.getCompanyName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/suppliers");
    }
}
