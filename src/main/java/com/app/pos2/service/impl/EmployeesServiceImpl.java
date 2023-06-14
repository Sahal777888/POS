package com.app.pos2.service.impl;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.EmployeesEntity;
import com.app.pos2.entity.RegionEntity;
import com.app.pos2.entity.TerritoriesEntity;
import com.app.pos2.model.CustomersModel;
import com.app.pos2.model.EmployeesModel;
import com.app.pos2.repository.EmployeesRepo;
import com.app.pos2.service.EmployeesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class EmployeesServiceImpl implements EmployeesService {
    private EmployeesRepo repo;
    @Autowired
    public EmployeesServiceImpl(EmployeesRepo repo){this.repo=repo;}
    @Override
    public List<EmployeesModel> get() {
        return this.repo.findAll().stream().map(EmployeesModel::new).collect(Collectors.toList());
    }
    @Override
    public EmployeesModel getById(String id) {
        return this.repo.findById(id).map(EmployeesModel::new).orElse(new EmployeesModel());
    }
    @Override
    public Boolean validName(EmployeesModel model) {
        List<EmployeesEntity> checkName = this.repo.findByFirstName(model.getFirstName());
        return checkName.isEmpty();
        //return null;
    }
    @Override
    public Boolean findSame(EmployeesModel request) {
        Integer checkSame = this.repo.findSame(request.getFirstName(), request.getLastName());
        return checkSame<=0;
    }
    @Override
    public Optional<EmployeesModel> save(EmployeesModel request) {
        if(request == null) { return Optional.empty(); }
        EmployeesEntity result = new EmployeesEntity(request);
        //Optional<EmployeesEntity> reportEntity=this.repo.findById(request.getReportId());
        //result.setReport(reportEntity.get());
        try{
            this.repo.save(result);
            return Optional.of(new EmployeesModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<EmployeesModel> update(String id, EmployeesModel request) {
        Optional<EmployeesEntity> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        EmployeesEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new EmployeesModel(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<EmployeesModel> delete(String id) {
        EmployeesEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new EmployeesModel(entity));
    }
}
