package com.app.pos2.model;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.TerritoriesEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class TerritoriesModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String territoryDesc;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String regionId;
    private RegionModel region;
    public TerritoriesModel() {    }
    public TerritoriesModel(TerritoriesEntity entity){
        BeanUtils.copyProperties(entity,this);
        if(entity.getRegion()!=null){
            region= new RegionModel(entity.getRegion());
        }
    }

}
