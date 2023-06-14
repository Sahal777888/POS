package com.app.pos2.controller;

import com.app.pos2.model.EmployeeTerritoriesModel;
import com.app.pos2.model.EmployeesModel;
import com.app.pos2.model.TerritoriesModel;
import com.app.pos2.service.EmployeeTerritoriesService;
import com.app.pos2.service.EmployeesService;
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
@RequestMapping("/employeeterritories")
public class EmployeeTerritoriesController {
    private EmployeeTerritoriesService service;
    private EmployeesService employeesService;
    private TerritoriesService territoriesService;
    @Autowired
    public EmployeeTerritoriesController(EmployeeTerritoriesService service,EmployeesService employeesService,TerritoriesService territoriesService){
        this.service=service;
        this.employeesService=employeesService;
        this.territoriesService=territoriesService;
    }

    @GetMapping
    public ModelAndView index(){
        List<EmployeeTerritoriesModel> result=this.service.get();
        ModelAndView view=new ModelAndView("employeeterritories/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view= new ModelAndView("employeeterritories/addpop.html");
        view.addObject("data",new EmployeeTerritoriesModel());
        List<EmployeesModel> employeesModelList=this.employeesService.get();
        List<TerritoriesModel> territoriesModelList=this.territoriesService.get();
        view.addObject("employeeList",employeesModelList);
        view.addObject("territoryList",territoriesModelList);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        EmployeeTerritoriesModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/employeeterritories");
        }
        ModelAndView view=new ModelAndView("employeeterritories/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        EmployeeTerritoriesModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/employeeterritories");
        }
        ModelAndView view=new ModelAndView("employeeterritories/editpop.html");
        view.addObject("data",model);
        List<EmployeesModel> employeesModelList=this.employeesService.get();
        List<TerritoriesModel> territoriesModelList=this.territoriesService.get();
        view.addObject("employeeList",employeesModelList);
        view.addObject("territoryList",territoriesModelList);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/employeeterritories");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") EmployeeTerritoriesModel request, BindingResult result){
        ModelAndView view = new ModelAndView("employeeterritories/addpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","employeeId","This coupling data have been used");
            result.addError(fieldError);
            FieldError fieldError2 = new FieldError("data","territoryId","This coupling data have been used");
            result.addError(fieldError2);
        }
        if(result.hasErrors()){
            List<EmployeesModel> employeesModelList=this.employeesService.get();
            view.addObject("employeeList",employeesModelList);
            List<TerritoriesModel> territoriesModelList=this.territoriesService.get();
            view.addObject("territoryList",territoriesModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/employeeterritories");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") EmployeeTerritoriesModel request, BindingResult result){
        ModelAndView view = new ModelAndView("employeeterritories/editpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","employeeId","This coupling data have been used");
            result.addError(fieldError);
            FieldError fieldError2 = new FieldError("data","territoryId","This coupling data have been used");
            result.addError(fieldError2);
        }
        if(result.hasErrors()){
            List<EmployeesModel> employeesModelList=this.employeesService.get();
            view.addObject("employeeList",employeesModelList);
            List<TerritoriesModel> territoriesModelList=this.territoriesService.get();
            view.addObject("territoryList",territoriesModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/employeeterritories");
    }
}
