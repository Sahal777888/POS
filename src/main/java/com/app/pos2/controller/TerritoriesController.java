package com.app.pos2.controller;

import com.app.pos2.model.RegionModel;
import com.app.pos2.model.TerritoriesModel;
import com.app.pos2.service.RegionService;
import com.app.pos2.service.TerritoriesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/territories")
public class TerritoriesController {
    private TerritoriesService service;
    private RegionService regionService;
    @Autowired
    public TerritoriesController(TerritoriesService service,RegionService regionService){
        this.service=service;
        this.regionService=regionService;
    }

    @GetMapping
    public ModelAndView index(){
        List<TerritoriesModel> result=this.service.get();
        ModelAndView view=new ModelAndView("territories/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view= new ModelAndView("territories/addpop.html");
        view.addObject("data", new TerritoriesModel());
        List<RegionModel> regionModelList=this.regionService.get();
        view.addObject("regionList",regionModelList);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        TerritoriesModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/territories");
        }
        ModelAndView view=new ModelAndView("territories/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        TerritoriesModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/territories");
        }
        ModelAndView view=new ModelAndView("territories/editpop.html");
        view.addObject("data",model);
        List<RegionModel> regionModelList=this.regionService.get();
        view.addObject("regionList",regionModelList);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/territories");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") TerritoriesModel request, BindingResult result){
        ModelAndView view = new ModelAndView("territories/addpop.html");
        if(Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("data","territoryDesc","Code '"+ request.getTerritoryDesc() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            List<RegionModel> regionModelList=this.regionService.get();
            view.addObject("regionList",regionModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/territories");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") TerritoriesModel request, BindingResult result){
        ModelAndView view = new ModelAndView("territories/editpop.html");
        if(Boolean.FALSE.equals(service.checkUpdate(request))){
            FieldError fieldError = new FieldError("data","territoryDesc","Code '"+ request.getTerritoryDesc() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            List<RegionModel> regionModelList=this.regionService.get();
            view.addObject("regionList",regionModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/territories");
    }
}
