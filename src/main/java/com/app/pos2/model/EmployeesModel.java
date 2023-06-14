package com.app.pos2.model;

import com.app.pos2.entity.EmployeesEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class EmployeesModel {
    private String id;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String firstName;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String lastName;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String title;
    @NotNull(message = "data wajib diisi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;
    @NotNull(message = "data wajib diisi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate hireDate;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String city;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String region;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String photoPath;
/*
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String reportId;
    private EmployeesModel report;
  */
    public EmployeesModel() {    }
    public EmployeesModel(EmployeesEntity entity){
        BeanUtils.copyProperties(entity,this);
        /*
        if(entity.getReport()!=null){
            report= new EmployeesModel(entity.getReport());
        }

         */
    }
}
