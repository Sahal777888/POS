package com.app.pos2.controller;

import com.app.pos2.model.UsaStateModel;
import com.app.pos2.service.UsaStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/usastate")
public class UsaStateController {
    private UsaStateService service;
    @Autowired
    public UsaStateController(UsaStateService service){this.service=service;}

    @GetMapping
    public ModelAndView index(){
        List<UsaStateModel> result=this.service.get();
        ModelAndView view=new ModelAndView("usastate/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view=new ModelAndView("usastate/addpop.html");
        view.addObject("data",new UsaStateModel());
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        UsaStateModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/usastate");
        }
        ModelAndView view=new ModelAndView("usastate/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        UsaStateModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/usastate");
        }
        ModelAndView view=new ModelAndView("usastate/editpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/usastate");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") UsaStateModel request, BindingResult result){
        ModelAndView view = new ModelAndView("usastate/addpop.html");
        if(Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("data","stateName","Code '"+ request.getStateName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/usastate");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") UsaStateModel request, BindingResult result){
        ModelAndView view = new ModelAndView("usastate/editpop.html");
        if(Boolean.FALSE.equals(service.checkUpdate(request))){
            FieldError fieldError = new FieldError("data","stateName","Code '"+ request.getStateName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/usastate");
    }
}
