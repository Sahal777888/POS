package com.app.pos2.service;

import com.app.pos2.model.EmployeeTerritoriesModel;
import com.app.pos2.model.EmployeesModel;

import java.util.List;
import java.util.Optional;

public interface EmployeesService {
    public List<EmployeesModel> get();
    public EmployeesModel getById(String id);
    public Boolean validName(EmployeesModel model);
    public Boolean findSame(EmployeesModel request);
    public Optional<EmployeesModel> save(EmployeesModel request);
    public Optional<EmployeesModel> update(String id, EmployeesModel request);
    public Optional<EmployeesModel> delete(String id);
}
