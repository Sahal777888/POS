package com.app.pos2.model;

import com.app.pos2.entity.OrderDetailsEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class OrderDetailsModel {
    private String id;
    @NotNull(message = "data wajib diisi")
    private Integer unitPrice;
    @NotNull(message = "data wajib diisi")
    private Integer quantity;
    @NotNull(message = "data wajib diisi")
    private Integer discount;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String productId;
    private ProductsModel product;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String orderId;
    private OrdersModel order;
    public OrderDetailsModel() {    }
    public OrderDetailsModel(OrderDetailsEntity entity){
        BeanUtils.copyProperties(entity,this);
        if(entity.getProduct() != null){
            product = new ProductsModel(entity.getProduct());
        }
        if(entity.getOrder() != null){
            order = new OrdersModel(entity.getOrder());
        }
    }

}
