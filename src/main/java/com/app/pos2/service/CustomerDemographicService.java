package com.app.pos2.service;

import com.app.pos2.model.CustomerDemographicModel;

import java.util.List;
import java.util.Optional;

public interface CustomerDemographicService {
    public List<CustomerDemographicModel> get();
    public CustomerDemographicModel getById(String id);
    public Boolean validName(CustomerDemographicModel model);
    public Optional<CustomerDemographicModel> save(CustomerDemographicModel request);
    public Boolean checkUpdate(CustomerDemographicModel request);
    public Optional<CustomerDemographicModel> update(String id, CustomerDemographicModel request);
    public Optional<CustomerDemographicModel> delete(String id);
}
