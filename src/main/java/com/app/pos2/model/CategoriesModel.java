package com.app.pos2.model;

import com.app.pos2.entity.CategoriesEntiity;
import com.app.pos2.entity.CustomersEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CategoriesModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String categoryName;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String categoryDesc;
    public CategoriesModel() {    }
    public CategoriesModel(CategoriesEntiity entity){
        BeanUtils.copyProperties(entity,this);
    }
}
