package com.app.pos2.model;

import com.app.pos2.entity.CustomerDemoEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class CustomerDemoModel {
    private String id;
    //@NotBlank(message = "data kosong")
    //@NotEmpty(message = "data wajib diisi")
    //private String name;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String customerId;
    private CustomersModel customer;
    @NotBlank(message = "data kosong")
    @NotEmpty(message = "data wajib diisi")
    private String customerDemographicId;
    private CustomerDemographicModel customerDemographic;
    public CustomerDemoModel() {    }
    public CustomerDemoModel(CustomerDemoEntity entity){
        BeanUtils.copyProperties(entity,this);
        if(entity.getCustomer()!=null){
            customer=new CustomersModel(entity.getCustomer());
        }
        if(entity.getCustomerDemographic()!=null){
            customerDemographic=new CustomerDemographicModel(entity.getCustomerDemographic());
        }
    }

}
