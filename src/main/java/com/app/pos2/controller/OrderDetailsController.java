package com.app.pos2.controller;

import com.app.pos2.model.OrderDetailsModel;
import com.app.pos2.model.OrdersModel;
import com.app.pos2.model.ProductsModel;
import com.app.pos2.service.OrderDetailsService;
import com.app.pos2.service.OrdersService;
import com.app.pos2.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("orderdetails")
public class OrderDetailsController {
    private OrderDetailsService service;
    private ProductsService productsService;
    private OrdersService ordersService;
    @Autowired
    public OrderDetailsController(OrderDetailsService service,ProductsService productsService,OrdersService ordersService){
        this.service=service;
        this.productsService=productsService;
        this.ordersService=ordersService;
    }

    @GetMapping
    public ModelAndView index(){
        List<OrderDetailsModel> result=this.service.get();
        ModelAndView view=new ModelAndView("orderdetails/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view =new ModelAndView("orderdetails/addpop.html");
        view.addObject("data",new OrderDetailsModel());
        List<ProductsModel> productsModelList=this.productsService.get();
        List<OrdersModel> ordersModelList=this.ordersService.get();
        view.addObject("productList",productsModelList);
        view.addObject("orderList",ordersModelList);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        OrderDetailsModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/orderdetails");
        }
        ModelAndView view=new ModelAndView("orderdetails/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        OrderDetailsModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/orderdetails");
        }
        ModelAndView view=new ModelAndView("orderdetails/editpop.html");
        view.addObject("data",model);
        List<ProductsModel> productsModelList=this.productsService.get();
        List<OrdersModel> ordersModelList=this.ordersService.get();
        view.addObject("productList",productsModelList);
        view.addObject("orderList",ordersModelList);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/orderdetails");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") OrderDetailsModel request, BindingResult result){
        ModelAndView view = new ModelAndView("orderdetails/addpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","orderId","This coupling data have been used");
            result.addError(fieldError);
            FieldError fieldError2 = new FieldError("data","productId","This coupling data have been used");
            result.addError(fieldError2);
        }
        if(result.hasErrors()){
            List<OrdersModel> OrderModelList=this.ordersService.get();
            view.addObject("orderList",OrderModelList);
            List<ProductsModel> ProductModelList=this.productsService.get();
            view.addObject("productList",ProductModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/orderdetails");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") OrderDetailsModel request, BindingResult result){
        ModelAndView view = new ModelAndView("orderdetails/editpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            FieldError fieldError = new FieldError("data","orderId","This coupling data have been used");
            result.addError(fieldError);
            FieldError fieldError2 = new FieldError("data","productId","This coupling data have been used");
            result.addError(fieldError2);
        }
        if(result.hasErrors()){
            List<OrdersModel> OrderModelList=this.ordersService.get();
            view.addObject("orderList",OrderModelList);
            List<ProductsModel> ProductModelList=this.productsService.get();
            view.addObject("productList",ProductModelList);
            view.addObject("data", request);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/orderdetails");
    }
}
