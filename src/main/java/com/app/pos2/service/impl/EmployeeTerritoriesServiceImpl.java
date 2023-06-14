package com.app.pos2.service.impl;

import com.app.pos2.entity.EmployeeTerritoriesEntity;
import com.app.pos2.entity.EmployeesEntity;
import com.app.pos2.entity.TerritoriesEntity;
import com.app.pos2.model.EmployeeTerritoriesModel;
import com.app.pos2.repository.EmployeeTerritoriesRepo;
import com.app.pos2.repository.EmployeesRepo;
import com.app.pos2.repository.TerritoriesRepo;
import com.app.pos2.service.EmployeeTerritoriesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeeTerritoriesServiceImpl implements EmployeeTerritoriesService {
    private EmployeeTerritoriesRepo repo;
    private EmployeesRepo employeesRepo;
    private TerritoriesRepo territoriesRepo;
    @Autowired
    public EmployeeTerritoriesServiceImpl(EmployeeTerritoriesRepo repo,EmployeesRepo employeesRepo,TerritoriesRepo territoriesRepo){
        this.repo=repo;
        this.employeesRepo=employeesRepo;
        this.territoriesRepo=territoriesRepo;
    }
    @Override
    public List<EmployeeTerritoriesModel> get() {
        return this.repo.findAll().stream().map(EmployeeTerritoriesModel::new).collect(Collectors.toList());
    }
    @Override
    public EmployeeTerritoriesModel getById(String id) {
        return this.repo.findById(id).map(EmployeeTerritoriesModel::new).orElse(new EmployeeTerritoriesModel());
    }
    @Override
    public Boolean findSame(EmployeeTerritoriesModel request) {
        Integer checkSame = this.repo.findSame(request.getEmployeeId(), request.getTerritoryId());
        return checkSame<=0;
        //return null;
    }
    @Override
    public Optional<EmployeeTerritoriesModel> save(EmployeeTerritoriesModel request) {
        if(request == null) { return Optional.empty(); }
        EmployeeTerritoriesEntity result = new EmployeeTerritoriesEntity(request);
        Optional<EmployeesEntity> employeesEntity=this.employeesRepo.findById(request.getEmployeeId());
        Optional<TerritoriesEntity> territoriesEntity=this.territoriesRepo.findById(request.getTerritoryId());
        result.setEmployee(employeesEntity.get());
        result.setTerritory(territoriesEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new EmployeeTerritoriesModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<EmployeeTerritoriesModel> update(String id, EmployeeTerritoriesModel request) {
        Optional<EmployeeTerritoriesEntity> data = this.repo.findById(id);
        if(data.isEmpty()) { return Optional.empty(); }
        EmployeeTerritoriesEntity result = data.get();
        BeanUtils.copyProperties(request,result);
        result.setId(id);
        Optional<EmployeesEntity> employeesEntity=this.employeesRepo.findById(request.getEmployeeId());
        Optional<TerritoriesEntity> territoriesEntity=this.territoriesRepo.findById(request.getTerritoryId());
        result.setEmployee(employeesEntity.get());
        result.setTerritory(territoriesEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new EmployeeTerritoriesModel(result));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<EmployeeTerritoriesModel> delete(String id) {
        EmployeeTerritoriesEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new EmployeeTerritoriesModel(entity));
    }
}
