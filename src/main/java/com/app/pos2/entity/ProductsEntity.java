package com.app.pos2.entity;

import com.app.pos2.model.ProductsModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="products_tab")
public class ProductsEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "product_name", length = 150)
    private String name;
    @Column(name = "quantity_per_unit")
    private Integer quantityPerUnit;
    @Column(name = "unit_price")
    private Integer unitPrice;
    @Column(name = "units_in_stock")
    private Integer unitsInStock;
    @Column(name = "units_on_order")
    private Integer unitsOnOrder;
    @Column(name = "reorder_level")
    private Integer reorderLevel;
    @Column(name = "discontinued")
    private Integer discontinued;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by", length = 20)
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @Column(name = "category_id", length = 36, insertable = false, updatable = false)
    private String categoryId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id",nullable = false)
    private CategoriesEntiity category;

    @Column(name = "suppliers_id", length = 36, insertable = false, updatable = false)
    private String supplierId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="suppliers_id",nullable = false)
    private SuppliersEntity supplier;


    public ProductsEntity() {    }
    public ProductsEntity(String id) {
        this.id = id;
    }
    public ProductsEntity(ProductsModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

}
