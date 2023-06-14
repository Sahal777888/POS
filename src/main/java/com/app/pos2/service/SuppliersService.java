package com.app.pos2.service;


import com.app.pos2.model.SuppliersModel;

import java.util.List;
import java.util.Optional;

public interface SuppliersService {
    public List<SuppliersModel> get();
    public SuppliersModel getById(String id);
    public Boolean validName(SuppliersModel model);
    public Optional<SuppliersModel> save(SuppliersModel request);
    public Boolean checkUpdate(SuppliersModel request);
    public Optional<SuppliersModel> update(String id, SuppliersModel request);
    public Optional<SuppliersModel> delete(String id);
}
