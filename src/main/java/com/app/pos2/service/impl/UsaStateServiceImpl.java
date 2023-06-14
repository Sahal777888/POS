package com.app.pos2.service.impl;

import com.app.pos2.entity.UsaStateEntity;
import com.app.pos2.model.UsaStateModel;
import com.app.pos2.repository.UsaStateRepo;
import com.app.pos2.service.UsaStateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UsaStateServiceImpl implements UsaStateService {
    private UsaStateRepo repo;
    @Autowired
    public UsaStateServiceImpl(UsaStateRepo repo){this.repo=repo;}
    @Override
    public List<UsaStateModel> get() {
        return this.repo.findAll().stream().map(UsaStateModel::new).collect(Collectors.toList());
    }
    @Override
    public UsaStateModel getById(String id) {
        return this.repo.findById(id).map(UsaStateModel::new).orElse(new UsaStateModel());
    }
    @Override
    public Boolean validName(UsaStateModel model) {
        List<UsaStateEntity> checkName = this.repo.findByStateName(model.getStateName());
        return checkName.isEmpty();
        //return null;
    }
    @Override
    public Optional<UsaStateModel> save(UsaStateModel request) {
        if(request == null) { return Optional.empty(); }
        UsaStateEntity result = new UsaStateEntity(request);
        try{
            this.repo.save(result);
            return Optional.of(new UsaStateModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Boolean checkUpdate(UsaStateModel request) {
        Optional<UsaStateEntity> nameEntity=this.repo.findById(request.getId());
        UsaStateModel nameModel=new UsaStateModel(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.getStateName(),nameModel.getStateName());
        return checkCount<=0;
    }
    /*
        Optional<XE> nameEntity=this.repo.findById(request.getId());
        XM nameModel=new XM(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.ge(),nameModel.ge());
        return checkCount<=0;
     */
    @Override
    public Optional<UsaStateModel> update(String id, UsaStateModel request) {
        Optional<UsaStateEntity> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        UsaStateEntity data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new UsaStateModel(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<UsaStateModel> delete(String id) {
        UsaStateEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new UsaStateModel(entity));
    }
}
