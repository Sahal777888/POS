package com.app.pos2.controller;

import com.app.pos2.model.CustomersModel;
import com.app.pos2.model.EmployeesModel;
import com.app.pos2.model.OrdersModel;
import com.app.pos2.model.ShippersModel;
import com.app.pos2.service.CustomersService;
import com.app.pos2.service.EmployeesService;
import com.app.pos2.service.OrdersService;
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
@RequestMapping("/orders")
public class OrdersController {
    private OrdersService service;
    private CustomersService customersService;
    private ShippersService shippersService;
    private EmployeesService employeesService;
    @Autowired
    public OrdersController(OrdersService service,CustomersService customersService,ShippersService shippersService,EmployeesService employeesService){
        this.service=service;
        this.customersService=customersService;
        this.shippersService=shippersService;
        this.employeesService=employeesService;
    }

    @GetMapping
    public ModelAndView index(){
        List<OrdersModel> result=this.service.get();
        ModelAndView view=new ModelAndView("orders/index.html");
        view.addObject("dataList",result);
        return view;
    }
    @GetMapping("/add")
    public ModelAndView add(){
        ModelAndView view=new ModelAndView("orders/addpop.html");
        view.addObject("data",new OrdersModel());
        List<CustomersModel> customersModelList=this.customersService.get();
        List<ShippersModel> shippersModelList=this.shippersService.get();
        List<EmployeesModel> employeesModelList=this.employeesService.get();
        view.addObject("customerList",customersModelList);
        view.addObject("shipperList",shippersModelList);
        view.addObject("employeeList",employeesModelList);
        return view;
    }

    @GetMapping("/detail/{id}")
    public ModelAndView detail(@PathVariable("id") String id){
        OrdersModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/orders");
        }
        ModelAndView view=new ModelAndView("orders/detailpop.html");
        view.addObject("data",model);
        return view;
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") String id){
        OrdersModel model=this.service.getById(id);
        if(model==null){
            return new ModelAndView("redirect:/orders");
        }
        ModelAndView view=new ModelAndView("orders/editpop.html");
        view.addObject("data",model);
        List<CustomersModel> customersModelList=this.customersService.get();
        List<ShippersModel> shippersModelList=this.shippersService.get();
        List<EmployeesModel> employeesModelList=this.employeesService.get();
        view.addObject("customerList",customersModelList);
        view.addObject("shipperList",shippersModelList);
        view.addObject("employeeList",employeesModelList);
        return view;
    }
    @GetMapping("/delete/{id}")
    public ModelAndView delete(@PathVariable("id") String id){
        this.service.delete(id);
        return new ModelAndView("redirect:/orders");
    }
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("data") OrdersModel request, BindingResult result){
        ModelAndView view = new ModelAndView("orders/editpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            result.addError(new FieldError("data","customerId","This coupling data have been used"));
            result.addError(new FieldError("data","employeeId","This coupling data have been used"));
            result.addError(new FieldError("data","shipperId","This coupling data have been used"));
        }
        if(result.hasErrors()){
            view.addObject("orders", request);
            List<CustomersModel> customersModelList=this.customersService.get();
            List<ShippersModel> shippersModelList=this.shippersService.get();
            List<EmployeesModel> employeesModelList=this.employeesService.get();
            view.addObject("customerList",customersModelList);
            view.addObject("shipperList",shippersModelList);
            view.addObject("employeeList",employeesModelList);
            return view;
        }
        this.service.save(request);
        return new ModelAndView("redirect:/orders");
    }
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("data") OrdersModel request, BindingResult result){
        ModelAndView view = new ModelAndView("orders/addpop.html");
        if(Boolean.FALSE.equals(service.findSame(request))){
            result.addError(new FieldError("data","customerId","This coupling data have been used"));
            result.addError(new FieldError("data","employeeId","This coupling data have been used"));
            result.addError(new FieldError("data","shipperId","This coupling data have been used"));
        }
        if(result.hasErrors()){
            view.addObject("orders", request);
            List<CustomersModel> customersModelList=this.customersService.get();
            List<ShippersModel> shippersModelList=this.shippersService.get();
            List<EmployeesModel> employeesModelList=this.employeesService.get();
            view.addObject("customerList",customersModelList);
            view.addObject("shipperList",shippersModelList);
            view.addObject("employeeList",employeesModelList);
            return view;
        }
        this.service.update(request.getId(),request);
        return new ModelAndView("redirect:/orders");
    }
}
