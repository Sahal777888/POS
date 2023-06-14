package com.app.pos2.service.impl;

import com.app.pos2.entity.ShippersEntity;
import com.app.pos2.model.ShippersModel;
import com.app.pos2.repository.ShippersRepo;
import com.app.pos2.service.ShippersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ShippersServiceImpl implements ShippersService {
    private ShippersRepo repo;
    @Autowired
    public ShippersServiceImpl(ShippersRepo repo){this.repo=repo;}
    @Override
    public List<ShippersModel> get() {
        return this.repo.findAll().stream().map(ShippersModel::new).collect(Collectors.toList());
    }
    @Override
    public ShippersModel getById(String id) {
        return this.repo.findById(id).map(ShippersModel::new).orElse(new ShippersModel());
    }
    @Override
    public Boolean validName(ShippersModel model) {
        List<ShippersEntity> checkName = this.repo.findByCompanyName(model.getCompanyName());
        return checkName.isEmpty();
        //return null;
    }
    @Override
    public Optional<ShippersModel> save(ShippersModel request) {
        if(request == null) { return Optional.empty(); }
        ShippersEntity result = new ShippersEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new ShippersModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Boolean checkUpdate(ShippersModel request) {
        Optional<ShippersEntity> nameEntity=this.repo.findById(request.getId());
        ShippersModel nameModel=new ShippersModel(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.getCompanyName(),nameModel.getCompanyName());
        return checkCount<=0;
    }
    @Override
    public Optional<ShippersModel> update(String id, ShippersModel request) {
        Optional<ShippersEntity> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        ShippersEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new ShippersModel(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<ShippersModel> delete(String id) {
        ShippersEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new ShippersModel(entity));
    }
}
