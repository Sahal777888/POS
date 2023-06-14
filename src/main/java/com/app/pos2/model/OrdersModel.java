package com.app.pos2.model;

import com.app.pos2.entity.CustomersEntity;
import com.app.pos2.entity.OrdersEntity;
import com.app.pos2.entity.ShippersEntity;
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
public class OrdersModel {
    private String id;
    //@NotBlank(message = "data kosong")
    //@NotEmpty(message = "data wajib diisi")
    private String name;
    @NotNull(message = "data wajib diisi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate orderDate;
    @NotNull(message = "data wajib diisi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate requiredDate;
    @NotNull(message = "data wajib diisi")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate shippedDate;
    @NotNull(message = "data wajib diisi")
    private Integer shipVia;
    @NotNull(message = "data wajib diisi")
    private Integer freight;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String shipName;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String shipAddress;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String shipCity;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String shipRegion;
    @NotNull(message = "data wajib diisi")
    private Integer shipPostalCode;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String shipCountry;

    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String customerId;
    private CustomersModel customer;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String shipperId;
    private ShippersModel shipper;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String employeeId;
    private EmployeesModel employee;
    public OrdersModel() {    }
    public OrdersModel(OrdersEntity entity){
        BeanUtils.copyProperties(entity,this);
        if(entity.getCustomer() != null){
            customer = new CustomersModel(entity.getCustomer());
        }
        if(entity.getShipper() != null){
            shipper = new ShippersModel(entity.getShipper());
        }
        if(entity.getEmployee() != null){
            employee = new EmployeesModel(entity.getEmployee());
        }
    }
}
