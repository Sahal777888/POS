package com.app.pos2.entity;

import com.app.pos2.model.OrderDetailsModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="order_details_tab")
public class OrderDetailsEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "unit_price", length = 150)
    private Integer unitPrice;
    @Column(name = "quantity", length = 150)
    private Integer quantity;
    @Column(name = "discount", length = 150)
    private Integer discount;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by", length = 20)
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @Column(name = "product_id", length = 36, insertable = false, updatable = false)
    private String productId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id",nullable = false)
    private ProductsEntity product;

    @Column(name = "order_id", length = 36, insertable = false, updatable = false)
    private String orderId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id",nullable = false)
    private OrdersEntity order;

    public OrderDetailsEntity() {    }
    public OrderDetailsEntity(String id) {
        this.id = id;
    }
    public OrderDetailsEntity(OrderDetailsModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

}
