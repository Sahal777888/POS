package com.app.pos2.service;

import com.app.pos2.model.OrderDetailsModel;

import java.util.List;
import java.util.Optional;

public interface OrderDetailsService {
    public List<OrderDetailsModel> get();
    public OrderDetailsModel getById(String id);
    public Boolean findSame(OrderDetailsModel request);
    public Optional<OrderDetailsModel> save(OrderDetailsModel request);
    public Optional<OrderDetailsModel> update(String id, OrderDetailsModel request);
    public Optional<OrderDetailsModel> delete(String id);
}
