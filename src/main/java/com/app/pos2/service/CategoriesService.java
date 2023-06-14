package com.app.pos2.service;

import com.app.pos2.model.CategoriesModel;
import com.app.pos2.model.UsaStateModel;

import java.util.List;
import java.util.Optional;

public interface CategoriesService {
    public List<CategoriesModel> get();
    public CategoriesModel getById(String id);
    public Boolean validName(CategoriesModel model);
    //public Boolean validName(XM model);
    public Optional<CategoriesModel> save(CategoriesModel request);
    public Boolean checkUpdate(CategoriesModel request);
    public Optional<CategoriesModel> update(String id, CategoriesModel request);
    public Optional<CategoriesModel> delete(String id);
}
