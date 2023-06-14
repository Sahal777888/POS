package com.app.pos2.service;

import com.app.pos2.model.UsaStateModel;

import java.util.List;
import java.util.Optional;

public interface UsaStateService {
    public List<UsaStateModel> get();
    public UsaStateModel getById(String id);
    public Boolean validName(UsaStateModel model);
    public Optional<UsaStateModel> save(UsaStateModel request);
    public Boolean checkUpdate(UsaStateModel request);
    /*
    public Boolean checkUpdate(XM request);
     */
    public Optional<UsaStateModel> update(String id, UsaStateModel request);
    public Optional<UsaStateModel> delete(String id);
}
