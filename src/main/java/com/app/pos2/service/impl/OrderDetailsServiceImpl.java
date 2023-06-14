package com.app.pos2.service.impl;

import com.app.pos2.entity.OrderDetailsEntity;
import com.app.pos2.entity.OrdersEntity;
import com.app.pos2.entity.ProductsEntity;
import com.app.pos2.model.OrderDetailsModel;
import com.app.pos2.model.OrdersModel;
import com.app.pos2.repository.OrderDetailsRepo;
import com.app.pos2.repository.OrdersRepo;
import com.app.pos2.repository.ProductsRepo;
import com.app.pos2.service.OrderDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private OrderDetailsRepo repo;
    private ProductsRepo productsRepo;
    private OrdersRepo ordersRepo;
    @Autowired
    public OrderDetailsServiceImpl(OrderDetailsRepo repo,ProductsRepo productsRepo,OrdersRepo ordersRepo){
        this.repo=repo;
        this.productsRepo=productsRepo;
        this.ordersRepo=ordersRepo;
    }
    @Override
    public List<OrderDetailsModel> get() {
        return this.repo.findAll().stream().map(OrderDetailsModel::new).collect(Collectors.toList());
    }
    @Override
    public OrderDetailsModel getById(String id) {
        return this.repo.findById(id).map(OrderDetailsModel::new).orElse(new OrderDetailsModel());
    }
    @Override
    public Boolean findSame(OrderDetailsModel request) {
        Integer checkSame = this.repo.findSame(request.getProductId(), request.getOrderId());
        return checkSame<=0;
        //return null;
    }
    @Override
    public Optional<OrderDetailsModel> save(OrderDetailsModel request) {
        if(request == null) { return Optional.empty(); }
        OrderDetailsEntity result = new OrderDetailsEntity(request);
        Optional<ProductsEntity> productsEntity=this.productsRepo.findById(request.getProductId());
        Optional<OrdersEntity> ordersEntity=this.ordersRepo.findById(request.getOrderId());
        result.setProduct(productsEntity.get());
        result.setOrder(ordersEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new OrderDetailsModel(result));
        }catch (Exception e){
            log.info("Save is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<OrderDetailsModel> update(String id, OrderDetailsModel request) {
        Optional<OrderDetailsEntity> data = this.repo.findById(id);
        if(data.isEmpty()) { return Optional.empty(); }
        OrderDetailsEntity result = data.get();
        BeanUtils.copyProperties(request,result);
        result.setId(id);
        Optional<ProductsEntity> productsEntity=this.productsRepo.findById(request.getProductId());
        Optional<OrdersEntity> ordersEntity=this.ordersRepo.findById(request.getOrderId());
        result.setProduct(productsEntity.get());
        result.setOrder(ordersEntity.get());

        try{
            this.repo.save(result);
            return Optional.of(new OrderDetailsModel(result));
        }catch (Exception e){
            log.info("Update is failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }
    @Override
    public Optional<OrderDetailsModel> delete(String id) {
        OrderDetailsEntity entity = this.repo.findById(id).orElse(null);
        if(entity == null) { return Optional.empty(); }
        try{
            this.repo.delete(entity);
        }catch (Exception e){
            log.info("Delete is failed, error: {}", e.getMessage());
        }
        return Optional.of(new OrderDetailsModel(entity));
    }
}
