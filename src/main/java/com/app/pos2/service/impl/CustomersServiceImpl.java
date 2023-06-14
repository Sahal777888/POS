package com.app.pos2.service.impl;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.ProductsEntity;
import com.app.pos2.model.CustomersModel;
import com.app.pos2.model.ProductsModel;
import com.app.pos2.repository.CustomersRepo;
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
public class CustomersServiceImpl implements CustomersService {
    private CustomersRepo repo;
    @Autowired
    public CustomersServiceImpl(CustomersRepo repo){this.repo=repo;}
    @Override
    public List<CustomersModel> get() {
        return this.repo.findAll().stream().map(CustomersModel::new).collect(Collectors.toList());
    }
    @Override
    public CustomersModel getById(String id) {
        return this.repo.findById(id).map(CustomersModel::new).orElse(new CustomersModel());
    }
    @Override
    public Boolean validName(CustomersModel model) {
        List<CustomersEntity> checkName = this.repo.findByContactName(model.getContactName());
        return checkName.isEmpty();
        //return null;
    }
    @Override
    public Optional<CustomersModel> save(CustomersModel request) {
        if(request == null) { return Optional.empty(); }
        CustomersEntity result = new CustomersEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new CustomersModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Boolean checkUpdate(CustomersModel request) {
        Optional<CustomersEntity> nameEntity=this.repo.findById(request.getId());
        CustomersModel nameModel=new CustomersModel(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.getContactName(),nameModel.getContactName());
        return checkCount<=0;
    }
    @Override
    public Optional<CustomersModel> update(String id, CustomersModel request) {
        Optional<CustomersEntity> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        CustomersEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new CustomersModel(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<CustomersModel> delete(String id) {
        CustomersEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new CustomersModel(entity));
    }
}
