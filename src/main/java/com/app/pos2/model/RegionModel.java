package com.app.pos2.model;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.RegionEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter

public class RegionModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String contactName;
    public RegionModel() {    }
    public RegionModel(RegionEntity entity){
        BeanUtils.copyProperties(entity,this);
    }

}
