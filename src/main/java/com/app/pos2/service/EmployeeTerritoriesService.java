package com.app.pos2.service;

import com.app.pos2.model.EmployeeTerritoriesModel;

import java.util.List;
import java.util.Optional;

public interface EmployeeTerritoriesService {
    public List<EmployeeTerritoriesModel> get();
    public EmployeeTerritoriesModel getById(String id);
    public Boolean findSame(EmployeeTerritoriesModel request);
    public Optional<EmployeeTerritoriesModel> save(EmployeeTerritoriesModel request);
    public Optional<EmployeeTerritoriesModel> update(String id, EmployeeTerritoriesModel request);
    public Optional<EmployeeTerritoriesModel> delete(String id);
}
