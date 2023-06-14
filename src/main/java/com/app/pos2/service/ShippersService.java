package com.app.pos2.service;

import com.app.pos2.model.ShippersModel;

import java.util.List;
import java.util.Optional;

public interface ShippersService {
    public List<ShippersModel> get();
    public ShippersModel getById(String id);
    public Boolean validName(ShippersModel model);
    public Optional<ShippersModel> save(ShippersModel request);
    public Boolean checkUpdate(ShippersModel request);
    public Optional<ShippersModel> update(String id, ShippersModel request);
    public Optional<ShippersModel> delete(String id);
}
