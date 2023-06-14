package com.app.pos2.service.impl;

import com.app.pos2.entity.SuppliersEntity;
import com.app.pos2.model.SuppliersModel;
import com.app.pos2.repository.SuppliersRepo;
import com.app.pos2.service.SuppliersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class SuppliersServiceImpl implements SuppliersService {
    private SuppliersRepo repo;
    @Autowired
    public SuppliersServiceImpl(SuppliersRepo repo){this.repo=repo;}
    @Override
    public List<SuppliersModel> get() {
        return this.repo.findAll().stream().map(SuppliersModel::new).collect(Collectors.toList());
    }
    @Override
    public SuppliersModel getById(String id) {
        return this.repo.findById(id).map(SuppliersModel::new).orElse(new SuppliersModel());
    }
    @Override
    public Boolean validName(SuppliersModel model) {
        List<SuppliersEntity> checkName = this.repo.findByCompanyName(model.getCompanyName());
        return checkName.isEmpty();
        //return null;
    }
    @Override
    public Optional<SuppliersModel> save(SuppliersModel request) {
        if(request == null) { return Optional.empty(); }
        SuppliersEntity result = new SuppliersEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new SuppliersModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Boolean checkUpdate(SuppliersModel request) {
        Optional<SuppliersEntity> nameEntity=this.repo.findById(request.getId());
        SuppliersModel nameModel=new SuppliersModel(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.getCompanyName(),nameModel.getCompanyName());
        return checkCount<=0;
    }
    @Override
    public Optional<SuppliersModel> update(String id, SuppliersModel request) {
        Optional<SuppliersEntity> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        SuppliersEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new SuppliersModel(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<SuppliersModel> delete(String id) {
        SuppliersEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new SuppliersModel(entity));
    }
}
