package com.app.pos2.model;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.ShippersEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class ShippersModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String companyName;
    @NotNull(message = "data wajib diisi")
    private Integer phone;
    public ShippersModel() {    }
    public ShippersModel(ShippersEntity entity){
        BeanUtils.copyProperties(entity,this);
    }

}
