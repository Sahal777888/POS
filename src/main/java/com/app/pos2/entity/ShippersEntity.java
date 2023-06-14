package com.app.pos2.entity;

import com.app.pos2.model.CustomersModel;
import com.app.pos2.model.ShippersModel;
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
@Table(name="shippers_tab")
public class ShippersEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "company_name", length = 150)
    private String companyName;
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

    public ShippersEntity() {    }
    public ShippersEntity(String id) {
        this.id = id;
    }

    public ShippersEntity(ShippersModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

}
