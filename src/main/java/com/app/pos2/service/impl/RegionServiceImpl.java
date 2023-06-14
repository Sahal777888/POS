package com.app.pos2.service.impl;

import com.app.pos2.entity.RegionEntity;
import com.app.pos2.model.RegionModel;
import com.app.pos2.repository.RegionRepo;
import com.app.pos2.service.RegionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class RegionServiceImpl implements RegionService {
    private RegionRepo repo;
    @Autowired
    public  RegionServiceImpl(RegionRepo repo){this.repo=repo;}
    @Override
    public List<RegionModel> get() {
        return this.repo.findAll().stream().map(RegionModel::new).collect(Collectors.toList());
    }
    @Override
    public RegionModel getById(String id) {
        return this.repo.findById(id).map(RegionModel::new).orElse(new RegionModel());
    }
    @Override
    public Boolean validName(RegionModel model) {
        List<RegionEntity> checkName = this.repo.findByContactName(model.getContactName());
        return checkName.isEmpty();
        //return null;
    }
    @Override
    public Optional<RegionModel> save(RegionModel request) {
        if(request == null) { return Optional.empty(); }
        RegionEntity result = new RegionEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new RegionModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    /*
    @Override
    public Boolean sameUpdate(RegionModel request) {
        //Integer checkSame=this.repo.findUpdateSame(request.getContactName());
        //return checkSame >=0;
        return null;
    }

     */
    @Override
    public Optional<RegionModel> update(String id, RegionModel request) {
        Optional<RegionEntity> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        RegionEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new RegionModel(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<RegionModel> delete(String id) {
        RegionEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new RegionModel(entity));
    }
}
