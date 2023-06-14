package com.app.pos2.service;

import com.app.pos2.model.TerritoriesModel;

import java.util.List;
import java.util.Optional;

public interface TerritoriesService {
    public List<TerritoriesModel> get();
    public TerritoriesModel getById(String id);
    public Boolean validName(TerritoriesModel model);
    public Optional<TerritoriesModel> save(TerritoriesModel request);
    public Boolean checkUpdate(TerritoriesModel request);
    public Optional<TerritoriesModel> update(String id, TerritoriesModel request);
    public Optional<TerritoriesModel> delete(String id);
}
