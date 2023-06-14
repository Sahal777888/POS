package com.app.pos2.service.impl;

import com.app.pos2.entity.CategoriesEntiity;
import com.app.pos2.entity.CustomerDemographicEntity;
import com.app.pos2.model.CategoriesModel;
import com.app.pos2.model.CustomerDemographicModel;
import com.app.pos2.repository.CustomerDemographicRepo;
import com.app.pos2.service.CustomerDemographicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CustomerDemographicServiceImpl implements CustomerDemographicService {
    private CustomerDemographicRepo repo;
    @Autowired
    public CustomerDemographicServiceImpl(CustomerDemographicRepo repo){this.repo=repo;}
    @Override
    public List<CustomerDemographicModel> get() {
        return this.repo.findAll().stream().map(CustomerDemographicModel::new).collect(Collectors.toList());
    }
    @Override
    public CustomerDemographicModel getById(String id) {
        return this.repo.findById(id).map(CustomerDemographicModel::new).orElse(new CustomerDemographicModel());
    }

    @Override
    public Boolean validName(CustomerDemographicModel model) {
        List<CustomerDemographicEntity> checkName = this.repo.findByCustomerDesc(model.getCustomerDesc());
        return checkName.isEmpty();
        //return null;
    }

    @Override
    public Optional<CustomerDemographicModel> save(CustomerDemographicModel request) {
        if(request == null) { return Optional.empty(); }
        CustomerDemographicEntity result = new CustomerDemographicEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new CustomerDemographicModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Boolean checkUpdate(CustomerDemographicModel request) {
        Optional<CustomerDemographicEntity> nameEntity=this.repo.findById(request.getId());
        CustomerDemographicModel nameModel=new CustomerDemographicModel(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.getCustomerDesc(),nameModel.getCustomerDesc());
        return checkCount<=0;
    }
    @Override
    public Optional<CustomerDemographicModel> update(String id, CustomerDemographicModel request) {
        Optional<CustomerDemographicEntity> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        CustomerDemographicEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new CustomerDemographicModel(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<CustomerDemographicModel> delete(String id) {
        CustomerDemographicEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new CustomerDemographicModel(entity));
    }
}
