package com.app.pos2.model;

import com.app.pos2.entity.EmployeesEntity;
import com.app.pos2.entity.ProductsEntity;
import com.app.pos2.entity.SuppliersEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ProductsModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String name;
    @NotNull(message = "data wajib diisi")
    private Integer quantityPerUnit;
    @NotNull(message = "data wajib diisi")
    private Integer unitPrice;
    @NotNull(message = "data wajib diisi")
    private Integer unitsInStock;
    @NotNull(message = "data wajib diisi")
    private Integer unitsOnOrder;
    @NotNull(message = "data wajib diisi")
    private Integer reorderLevel;
    @NotNull(message = "data wajib diisi")
    private Integer discontinued;

    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String categoryId;
    private CategoriesModel category;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String supplierId;
    private SuppliersModel supplier;
    public ProductsModel() {    }
    public ProductsModel(ProductsEntity entity){
        BeanUtils.copyProperties(entity,this);
        if(entity.getCategory() != null){
            category = new CategoriesModel(entity.getCategory());
        }
        if(entity.getSupplier() != null){
            supplier = new SuppliersModel(entity.getSupplier());
        }
    }
}
