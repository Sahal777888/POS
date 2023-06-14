package com.app.pos2.controller;

import com.app.pos2.model.CategoriesModel;
import com.app.pos2.service.CategoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

import static java.lang.Boolean.TRUE;

@Controller
@RequestMapping("/categories")
public class CategoriesController {
    private CategoriesService service;
    @Autowired
    public CategoriesController(CategoriesService service){this.service=service;}

    @GetMapping
    public ModelAndView index(){
        List<CategoriesModel> result=this.service.get();
        ModelAndView view=new ModelAndView("categories/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view =new ModelAndView("categories/addpop.html");
        view.addObject("data",new CategoriesModel());
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        CategoriesModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/categories");
        }
        ModelAndView view=new ModelAndView("categories/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        CategoriesModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/categories");
        }
        ModelAndView view=new ModelAndView("categories/editpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/categories");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") CategoriesModel request, BindingResult result){
        ModelAndView view = new ModelAndView("categories/addpop.html");
        if(Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("data","categoryName","Code '"+ request.getCategoryName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/categories");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") CategoriesModel request, BindingResult result){
        ModelAndView view = new ModelAndView("categories/editpop.html");
        if(Boolean.FALSE.equals(service.checkUpdate(request))){
            FieldError fieldError = new FieldError("data","categoryName","Code '"+ request.getCategoryName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/categories");
    }
}
