package com.app.pos2.service;

import com.app.pos2.model.OrdersModel;

import java.util.List;
import java.util.Optional;

public interface OrdersService {
    public List<OrdersModel> get();
    public OrdersModel getById(String id);
    public Boolean findSame(OrdersModel model);
    public Optional<OrdersModel> save(OrdersModel request);
    public Optional<OrdersModel> update(String id, OrdersModel request);
    public Optional<OrdersModel> delete(String id);
}
