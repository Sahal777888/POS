package com.app.pos2.service.impl;

import com.app.pos2.entity.RegionEntity;
import com.app.pos2.entity.TerritoriesEntity;
import com.app.pos2.model.TerritoriesModel;
import com.app.pos2.repository.RegionRepo;
import com.app.pos2.repository.TerritoriesRepo;
import com.app.pos2.service.TerritoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TerritoriesServiceImpl implements TerritoriesService {
    private TerritoriesRepo repo;
    private RegionRepo regionRepo;
    @Autowired
    public TerritoriesServiceImpl(TerritoriesRepo repo,RegionRepo regionRepo){
        this.repo=repo;
        this.regionRepo=regionRepo;
    }
    @Override
    public List<TerritoriesModel> get() {
        return this.repo.findAll().stream().map(TerritoriesModel::new).collect(Collectors.toList());
    }
    @Override
    public TerritoriesModel getById(String id) {
        return this.repo.findById(id).map(TerritoriesModel::new).orElse(new TerritoriesModel());
    }
    @Override
    public Boolean validName(TerritoriesModel model) {
        List<TerritoriesEntity> checkName = this.repo.findByTerritoryDesc(model.getTerritoryDesc());
        return checkName.isEmpty();
        //return null;
    }
    @Override
    public Optional<TerritoriesModel> save(TerritoriesModel request) {
        if(request == null) { return Optional.empty(); }
        TerritoriesEntity result = new TerritoriesEntity(request);
        Optional<RegionEntity> regionEntity=this.regionRepo.findById(request.getRegionId());
        result.setRegion(regionEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new TerritoriesModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Boolean checkUpdate(TerritoriesModel request) {
        Optional<TerritoriesEntity> nameEntity=this.repo.findById(request.getId());
        TerritoriesModel nameModel=new TerritoriesModel(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.getTerritoryDesc(),nameModel.getTerritoryDesc());
        return checkCount<=0;
    }
    @Override
    public Optional<TerritoriesModel> update(String id, TerritoriesModel request) {
        Optional<TerritoriesEntity> data = this.repo.findById(id);
        if(data.isEmpty()) { return Optional.empty(); }
        TerritoriesEntity result = data.get();
        BeanUtils.copyProperties(request,data);
        result.setId(id);
        Optional<RegionEntity> regionEntity=this.regionRepo.findById(request.getRegionId());
        result.setRegion(regionEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new TerritoriesModel(result));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<TerritoriesModel> delete(String id) {
        TerritoriesEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new TerritoriesModel(entity));
    }
}
