package com.app.pos2.service;

import com.app.pos2.model.CustomerDemoModel;

import java.util.List;
import java.util.Optional;

public interface CustomerDemoService {
    public List<CustomerDemoModel> get();
    public CustomerDemoModel getById(String id);
    public Boolean findSame(CustomerDemoModel request);
    //public Boolean findSame(Xm request);
    public Optional<CustomerDemoModel> save(CustomerDemoModel request);
    public Optional<CustomerDemoModel> update(String id, CustomerDemoModel request);
    public Optional<CustomerDemoModel> delete(String id);
}
