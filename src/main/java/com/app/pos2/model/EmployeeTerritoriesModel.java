package com.app.pos2.model;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.EmployeeTerritoriesEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class EmployeeTerritoriesModel {
    private String id;
    //@NotBlank(message = "data kosong")
    //@NotEmpty(message = "data wajib diisi")
    private String name;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String employeeId;
    private EmployeesModel employee;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String territoryId;
    private TerritoriesModel territory;
    public EmployeeTerritoriesModel() {    }
    public EmployeeTerritoriesModel(EmployeeTerritoriesEntity entity){
        BeanUtils.copyProperties(entity,this);
        if(entity.getEmployee()!=null){
            employee=new EmployeesModel(entity.getEmployee());
        }
        if(entity.getTerritory()!=null){
            territory=new TerritoriesModel(entity.getTerritory());
        }
    }

}
