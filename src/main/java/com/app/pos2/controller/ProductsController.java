package com.app.pos2.controller;

import com.app.pos2.model.CategoriesModel;
import com.app.pos2.model.ProductsModel;
import com.app.pos2.model.SuppliersModel;
import com.app.pos2.service.CategoriesService;
import com.app.pos2.service.ProductsService;
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
@RequestMapping("/products")
public class ProductsController {
    private ProductsService service;
    private CategoriesService categoriesService;
    private SuppliersService suppliersService;
    @Autowired
    public ProductsController(ProductsService service,CategoriesService categoriesService,SuppliersService suppliersService){
        this.service=service;
        this.categoriesService=categoriesService;
        this.suppliersService=suppliersService;
    }

    @GetMapping
    public ModelAndView index(){
        List<ProductsModel> result=this.service.get();
        ModelAndView view=new ModelAndView("products/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view= new ModelAndView("products/addpop.html");
        view.addObject("data",new ProductsModel());
        List<CategoriesModel> categoriesModelList=this.categoriesService.get();
        List<SuppliersModel> suppliersModelList=this.suppliersService.get();
        view.addObject("categoryList",categoriesModelList);
        view.addObject("supplierList",suppliersModelList);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        ProductsModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/products");
        }
        ModelAndView view=new ModelAndView("products/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        ProductsModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/products");
        }
        ModelAndView view=new ModelAndView("products/editpop.html");
        view.addObject("data",model);
        List<CategoriesModel> categoriesModelList=this.categoriesService.get();
        List<SuppliersModel> suppliersModelList=this.suppliersService.get();
        view.addObject("categoryList",categoriesModelList);
        view.addObject("supplierList",suppliersModelList);

        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/products");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") ProductsModel request, BindingResult result){
        ModelAndView view = new ModelAndView("products/addpop.html");
        if(Boolean.FALSE.equals(service.validName(request))){
            FieldError fieldError = new FieldError("data","name","Code '"+ request.getName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            List<CategoriesModel> categoriesModelList=this.categoriesService.get();
            view.addObject("categoryList",categoriesModelList);
            List<SuppliersModel> suppliersModelList=this.suppliersService.get();
            view.addObject("supplierList",suppliersModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/products");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") ProductsModel request, BindingResult result){
        ModelAndView view = new ModelAndView("products/editpop.html");
        if(Boolean.FALSE.equals(service.checkUpdate(request))){
            FieldError fieldError = new FieldError("data","name","Code '"+ request.getName() +"' already exist");
            result.addError(fieldError);
        }
        if(result.hasErrors()){
            List<CategoriesModel> categoriesModelList=this.categoriesService.get();
            view.addObject("categoryList",categoriesModelList);
            List<SuppliersModel> suppliersModelList=this.suppliersService.get();
            view.addObject("supplierList",suppliersModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/products");
    }
}
