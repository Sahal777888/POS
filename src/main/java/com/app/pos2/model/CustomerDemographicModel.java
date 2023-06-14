package com.app.pos2.model;

import com.app.pos2.entity.CustomerDemographicEntity;
import com.app.pos2.entity.CustomersEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CustomerDemographicModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String customerDesc;
    public CustomerDemographicModel() {    }
    public CustomerDemographicModel(CustomerDemographicEntity entity){
        BeanUtils.copyProperties(entity,this);
    }

}
