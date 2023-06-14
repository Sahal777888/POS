package com.app.pos2.model;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.UsaStateEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class UsaStateModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String stateName;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String stateAbbr;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String stateRegion;
    public UsaStateModel() {    }
    public UsaStateModel(UsaStateEntity entity){
        BeanUtils.copyProperties(entity,this);
    }

}
