package com.app.pos2.service.impl;

import com.app.pos2.entity.CategoriesEntiity;
import com.app.pos2.entity.ProductsEntity;
import com.app.pos2.entity.SuppliersEntity;
import com.app.pos2.model.ProductsModel;
import com.app.pos2.repository.CategoriesRepo;
import com.app.pos2.repository.ProductsRepo;
import com.app.pos2.repository.SuppliersRepo;
import com.app.pos2.service.ProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductsServiceImpl implements ProductsService {
    private ProductsRepo repo;
    private CategoriesRepo categoriesRepo;
    private SuppliersRepo suppliersRepo;
    @Autowired
    public ProductsServiceImpl(ProductsRepo repo,CategoriesRepo categoriesRepo,SuppliersRepo suppliersRepo){
        this.repo=repo;
        this.categoriesRepo=categoriesRepo;
        this.suppliersRepo=suppliersRepo;
    }
    @Override
    public List<ProductsModel> get() {
        return this.repo.findAll().stream().map(ProductsModel::new).collect(Collectors.toList());
    }
    @Override
    public ProductsModel getById(String id) {
        return this.repo.findById(id).map(ProductsModel::new).orElse(new ProductsModel());
    }
    @Override
    public Boolean validName(ProductsModel model) {
        List<ProductsEntity> checkName = this.repo.findByName(model.getName());
        return checkName.isEmpty();
        //return null;
    }
    @Override
    public Optional<ProductsModel> save(ProductsModel request) {
        if(request == null) { return Optional.empty(); }
        ProductsEntity result = new ProductsEntity(request);

        Optional<CategoriesEntiity> categoriesEntiity=this.categoriesRepo.findById(request.getCategoryId());
        Optional<SuppliersEntity> suppliersEntity=this.suppliersRepo.findById(request.getSupplierId());
        result.setCategory(categoriesEntiity.get());
        result.setSupplier(suppliersEntity.get());
        try{
            this.repo.save(result);
            return Optional.of(new ProductsModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Boolean checkUpdate(ProductsModel request) {
        Optional<ProductsEntity> nameEntity=this.repo.findById(request.getId());
        ProductsModel nameModel=new ProductsModel(nameEntity.get());
        Integer checkCount = this.repo.findUpdateSame(request.getName(),nameModel.getName());
        return checkCount<=0;
    }
    @Override
    public Optional<ProductsModel> update(String id, ProductsModel request) {
        Optional<ProductsEntity> data = this.repo.findById(id);
        if(data.isEmpty()) { return Optional.empty(); }
        ProductsEntity result = data.get();
        BeanUtils.copyProperties(request,result);
        result.setId(id);

        Optional<CategoriesEntiity> categoriesEntiity=this.categoriesRepo.findById(request.getCategoryId());
        Optional<SuppliersEntity> suppliersEntity=this.suppliersRepo.findById(request.getSupplierId());
        result.setCategory(categoriesEntiity.get());
        result.setSupplier(suppliersEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new ProductsModel(result));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<ProductsModel> delete(String id) {
        ProductsEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new ProductsModel(entity));
    }
    /*
    private XR repo;
    @Autowired
    public XSI(XR repo){this.repo=repo;}


    @Override
    public List<ProductsModel> get() {
        return this.repo.findAll().stream().map(XM::new).collect(Collectors.toList());
    }
    @Override
    public ProductsModel getById(String id) {
        return this.repo.findById(id).map(XM::new).orElse(new XM());
    }
    @Override
    public Optional<ProductsModel> save(ProductsModel request) {
        if(request == null) { return Optional.empty(); }
        XE result = new XE(request);
        try{
            this.repo.save(result);
            return Optional.of(new XM(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<ProductsModel> update(String id, ProductsModel request) {
        Optional<XE> result = this.repo.findById(id);
        if(result.isEmpty()) { return Optional.empty(); }
        XE data = result.get();
        BeanUtils.copyProperties(request,data);
        data.setId(id);
        try{
            this.repo.save(data);
            return Optional.of(new XM(data));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<ProductsModel> delete(String id) {
        XE entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new XM(entity));
    }
     */

}
