package com.app.pos2.model;

import com.app.pos2.entity.CustomersEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CustomersModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String contactName;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String companyName;
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
    @NotNull(message = "data wajib diisi")
    private Integer postalCode;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String country;
    @NotNull(message = "data wajib diisi")
    private Integer phone;

    public CustomersModel() {    }
    public CustomersModel(CustomersEntity entity){
        BeanUtils.copyProperties(entity,this);
    }
}
