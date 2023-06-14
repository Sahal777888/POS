package com.app.pos2.entity;

import com.app.pos2.model.CustomerDemoModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="customer_demo_tab")
public class CustomerDemoEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    //@Column(name = "namex", length = 150)
    //private String name;


    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by", length = 20)
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @Column(name = "customer_id", length = 36, insertable = false, updatable = false)
    private String customerId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_id",nullable = false)
    private CustomersEntity customer;

    @Column(name = "customer_demographic_id", length = 36, insertable = false, updatable = false)
    private String customerDemographicId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="customer_demographic_id",nullable = false)
    private CustomerDemographicEntity customerDemographic;

    public CustomerDemoEntity() {    }
    public CustomerDemoEntity(String id) {
        this.id = id;
    }
    public CustomerDemoEntity(CustomerDemoModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }


}
