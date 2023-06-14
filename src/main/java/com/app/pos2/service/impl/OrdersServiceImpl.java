package com.app.pos2.service.impl;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.EmployeesEntity;
import com.app.pos2.entity.OrdersEntity;
import com.app.pos2.entity.ShippersEntity;
import com.app.pos2.model.OrdersModel;
import com.app.pos2.repository.CustomersRepo;
import com.app.pos2.repository.EmployeesRepo;
import com.app.pos2.repository.OrdersRepo;
import com.app.pos2.repository.ShippersRepo;
import com.app.pos2.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrdersServiceImpl implements OrdersService {
    private OrdersRepo repo;
    private CustomersRepo customersRepo;
    private ShippersRepo shippersRepo;
    private EmployeesRepo employeesRepo;
    @Autowired
    public OrdersServiceImpl(OrdersRepo repo,CustomersRepo customersRepo,ShippersRepo shippersRepo,EmployeesRepo employeesRepo){
        this.repo=repo;
        this.customersRepo=customersRepo;
        this.shippersRepo=shippersRepo;
        this.employeesRepo=employeesRepo;
    }
    @Override
    public List<OrdersModel> get() {
        return this.repo.findAll().stream().map(OrdersModel::new).collect(Collectors.toList());
    }
    @Override
    public OrdersModel getById(String id) {
        return this.repo.findById(id).map(OrdersModel::new).orElse(new OrdersModel());
    }
    @Override
    public Boolean findSame(OrdersModel model) {
        Integer checkName = this.repo.findSame(model.getCustomerId(),model.getEmployeeId(), model.getShipperId());
        return checkName<=0;
        //return null;
    }
    @Override
    public Optional<OrdersModel> save(OrdersModel request) {
        if(request == null) { return Optional.empty(); }
        OrdersEntity result = new OrdersEntity(request);
        Optional<CustomersEntity> customersEntity=this.customersRepo.findById(request.getCustomerId());
        Optional<ShippersEntity> shippersEntity=this.shippersRepo.findById(request.getShipperId());
        Optional<EmployeesEntity> employeesEntity=this.employeesRepo.findById(request.getEmployeeId());
        result.setCustomer(customersEntity.get());
        result.setShipper(shippersEntity.get());
        result.setEmployee(employeesEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new OrdersModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<OrdersModel> update(String id, OrdersModel request) {
        Optional<OrdersEntity> data = this.repo.findById(id);
        if(data.isEmpty()) { return Optional.empty(); }
        OrdersEntity result = data.get();
        BeanUtils.copyProperties(request,result);
        result.setId(id);
        Optional<CustomersEntity> customersEntity=this.customersRepo.findById(request.getCustomerId());
        Optional<ShippersEntity> shippersEntity=this.shippersRepo.findById(request.getShipperId());
        Optional<EmployeesEntity> employeesEntity=this.employeesRepo.findById(request.getEmployeeId());
        result.setCustomer(customersEntity.get());
        result.setShipper(shippersEntity.get());
        result.setEmployee(employeesEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new OrdersModel(result));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<OrdersModel> delete(String id) {
        OrdersEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new OrdersModel(entity));
    }
}
