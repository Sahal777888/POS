package com.app.pos2.service.impl;

import com.app.pos2.entity.CategoriesEntiity;
import com.app.pos2.entity.UsaStateEntity;
import com.app.pos2.model.CategoriesModel;
import com.app.pos2.model.UsaStateModel;
import com.app.pos2.repository.CategoriesRepo;
import com.app.pos2.service.CategoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CategoriesServiceImpl implements CategoriesService {
    private CategoriesRepo repo;
    @Autowired
    public CategoriesServiceImpl(CategoriesRepo repo){this.repo=repo;}
    @Override
    public List<CategoriesModel> get() {
        return this.repo.findAll().stream().map(CategoriesModel::new).collect(Collectors.toList());
    }
    @Override
    public CategoriesModel getById(String id) {
        return this.repo.findById(id).map(CategoriesModel::new).orElse(new CategoriesModel());
    }
    @Override
    public Boolean validName(CategoriesModel model) {
        List<CategoriesEntiity> checkName = this.repo.findByCategoryName(model.getCategoryName());
        return checkName.isEmpty();
        //return null;
    }
    /*
    @Override
    public Boolean validName(XM model) {
        List<XE> checkName = this.repo.findB(model.ge());
        return checkName.isEmpty();
    }
     */
    @Override
    public Optional<CategoriesModel> save(CategoriesModel request) {
        if(request == null) { return Optional.empty(); }
        CategoriesEntiity result = new CategoriesEntiity(request);
        try{
            this.repo.save(result);
            return Optional.of(new CategoriesModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Boolean checkUpdate(CategoriesModel request) {
        Optional<CategoriesEntiity> nameEntity=this.repo.findById(request.getId());
        CategoriesModel nameModel=new CategoriesModel(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.getCategoryName(),nameModel.getCategoryName());
        return checkCount<=0;
    }
    @Override
    public Optional<CategoriesModel> update(String id, CategoriesModel request) {
        Optional<CategoriesEntiity> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        CategoriesEntiity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        data.setUpdatedAt(LocalDateTime.now());
        data.setUpdatedBy("SYSTEM");
        try{
            this.repo.save(data);
            return Optional.of(new CategoriesModel(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<CategoriesModel> delete(String id) {
        CategoriesEntiity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new CategoriesModel(entity));
    }
}
