package com.app.pos2.model;

import com.app.pos2.entity.CategoriesEntiity;
import com.app.pos2.entity.SuppliersEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class SuppliersModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String companyName;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String contactName;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String contactTitle;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String address;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String city;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String region;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String postalCode;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String country;
    public SuppliersModel() {    }
    public SuppliersModel(SuppliersEntity entity){
        BeanUtils.copyProperties(entity,this);
    }
}
