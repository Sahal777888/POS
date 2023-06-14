package com.app.pos2.service.impl;

import com.app.pos2.entity.CustomerDemoEntity;
import com.app.pos2.entity.CustomerDemographicEntity;
import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.model.CustomerDemoModel;
import com.app.pos2.model.CustomersModel;
import com.app.pos2.repository.CustomerDemoRepo;
import com.app.pos2.repository.CustomerDemographicRepo;
import com.app.pos2.repository.CustomersRepo;
import com.app.pos2.service.CustomerDemoService;
import com.app.pos2.service.CustomersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerDemoServiceImpl implements CustomerDemoService {
    private CustomerDemoRepo repo;
    private CustomersRepo customersRepo;
    private CustomerDemographicRepo customerDemographicRepo;
    @Autowired
    public CustomerDemoServiceImpl(CustomerDemoRepo repo,CustomersRepo customersRepo,CustomerDemographicRepo customerDemographicRepo){
        this.repo=repo;
        this.customersRepo=customersRepo;
        this.customerDemographicRepo=customerDemographicRepo;
    }
    @Override
    public List<CustomerDemoModel> get() {
        return this.repo.findAll().stream().map(CustomerDemoModel::new).collect(Collectors.toList());
    }
    @Override
    public CustomerDemoModel getById(String id) {
        return this.repo.findById(id).map(CustomerDemoModel::new).orElse(new CustomerDemoModel());
    }
    @Override
    public Boolean findSame(CustomerDemoModel request) {
        Integer checkSame = this.repo.findSame(request.getCustomerId(), request.getCustomerDemographicId());
        return checkSame<=0;
        //return null;
    }
    @Override
    public Optional<CustomerDemoModel> save(CustomerDemoModel request) {
        if(request == null) { return Optional.empty(); }
        CustomerDemoEntity result = new CustomerDemoEntity(request);
        log.info("data customer.id {}", request.getCustomerId());
        log.info("data CustomerDemographic.id {}", request.getCustomerDemographicId());
        Optional<CustomersEntity> customersEntity=this.customersRepo.findById(request.getCustomerId());
        Optional<CustomerDemographicEntity> customerDemographicEntity=this.customerDemographicRepo.findById(request.getCustomerDemographicId());

        result.setCustomer(customersEntity.get());
        result.setCustomerDemographic(customerDemographicEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new CustomerDemoModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<CustomerDemoModel> update(String id, CustomerDemoModel request) {
        Optional<CustomerDemoEntity> data = this.repo.findById(id);
        if(data.isEmpty()) { return Optional.empty(); }
        CustomerDemoEntity result = data.get();
        BeanUtils.copyProperties(request,result);
        result.setId(id);
        Optional<CustomersEntity> customersEntity=this.customersRepo.findById(request.getCustomerId());
        Optional<CustomerDemographicEntity> customerDemographicEntity=this.customerDemographicRepo.findById(request.getCustomerDemographicId());
        result.setCustomer(customersEntity.get());
        result.setCustomerDemographic(customerDemographicEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new CustomerDemoModel(result));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<CustomerDemoModel> delete(String id) {
        CustomerDemoEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new CustomerDemoModel(entity));
    }
}
