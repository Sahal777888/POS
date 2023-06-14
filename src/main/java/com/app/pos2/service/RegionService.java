package com.app.pos2.service;

import com.app.pos2.model.RegionModel;

import java.util.List;
import java.util.Optional;

public interface RegionService {
    public List<RegionModel> get();
    public RegionModel getById(String id);
    public Boolean validName(RegionModel model);
    public Optional<RegionModel> save(RegionModel request);
    //public Boolean sameUpdate(RegionModel request);
    public Optional<RegionModel> update(String id, RegionModel request);
    public Optional<RegionModel> delete(String id);
}
