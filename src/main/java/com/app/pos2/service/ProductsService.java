package com.app.pos2.service;

import com.app.pos2.model.ProductsModel;

import java.util.List;
import java.util.Optional;

public interface ProductsService {
    public List<ProductsModel> get();
    public ProductsModel getById(String id);
    public Boolean validName(ProductsModel model);
    public Optional<ProductsModel> save(ProductsModel request);
    public Boolean checkUpdate(ProductsModel request);
    public Optional<ProductsModel> update(String id, ProductsModel request);
    public Optional<ProductsModel> delete(String id);
}
