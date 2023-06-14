package com.app.pos2.service;

import com.app.pos2.model.CustomersModel;

import java.util.List;
import java.util.Optional;

public interface CustomersService {
    public List<CustomersModel> get();
    public CustomersModel getById(String id);
    public Boolean validName(CustomersModel model);
    public Optional<CustomersModel> save(CustomersModel request);
    public Boolean checkUpdate(CustomersModel request);
    public Optional<CustomersModel> update(String id, CustomersModel request);
    public Optional<CustomersModel> delete(String id);
}
