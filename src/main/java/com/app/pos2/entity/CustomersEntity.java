package com.app.pos2.entity;

import com.app.pos2.model.CustomersModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="customers_tab")
public class CustomersEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "contact_name", length = 150)
    private String contactName;
    @Column(name = "company_name", length = 150)
    private String companyName;
    @Column(name = "contact_title", length = 150)
    private String contactTitle;
    @Column(name = "address", length = 150)
    private String address;
    @Column(name = "city", length = 150)
    private String city;
    @Column(name = "region", length = 150)
    private String region;
    @Column(name = "postal_code", length = 150)
    private Integer postalCode;
    @Column(name = "country", length = 150)
    private String country;
    @Column(name = "phone", length = 150)
    private Integer phone;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by", length = 20)
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    public CustomersEntity() {    }
    public CustomersEntity(String id) {
        this.id = id;
    }
    public CustomersEntity(CustomersModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }
}
