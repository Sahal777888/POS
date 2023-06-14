package com.app.pos2.controller;

import com.app.pos2.model.ShippersModel;
import com.app.pos2.service.ShippersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/shippers")
public class ShippersController {
    private ShippersService service;
    @Autowired
    public ShippersController(ShippersService service){this.service=service;}

    @GetMapping
    public ModelAndView index(){
        List<ShippersModel> result=this.service.get();
        ModelAndView view=new ModelAndView("shippers/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view =new ModelAndView("shippers/addpop.html");
        view.addObject("data",new ShippersModel());
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ShippersModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/shippers");
        }
        ModelAndView view=new ModelAndView("shippers/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ShippersModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/shippers");
        }
        ModelAndView view=new ModelAndView("shippers/editpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/shippers");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") ShippersModel request, BindingResult result){
        ModelAndView view = new ModelAndView("shippers/addpop.html");
        if(Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("data","companyName","Code '"+ request.getCompanyName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/shippers");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") ShippersModel request, BindingResult result){
        ModelAndView view = new ModelAndView("shippers/editpop.html");
        if(Boolean.FALSE.equals(service.checkUpdate(request))){
            FieldError fieldError = new FieldError("data","companyName","Code '"+ request.getCompanyName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/shippers");
    }
}
