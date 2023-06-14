package com.app.pos2.entity;

import com.app.pos2.model.CustomersModel;
import com.app.pos2.model.EmployeeTerritoriesModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="employee_territories_tab")
public class EmployeeTerritoriesEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "name", length = 150)
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by", length = 20)
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @Column(name = "employee_id", length = 36, insertable = false, updatable = false)
    private String employeeId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="employee_id",nullable = false)
    private EmployeesEntity employee;
    @Column(name = "territory_id", length = 36, insertable = false, updatable = false)
    private String territoryId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="territory_id",nullable = false)
    private TerritoriesEntity territory;

    public EmployeeTerritoriesEntity() {    }
    public EmployeeTerritoriesEntity(String id) {
        this.id = id;
    }

    public EmployeeTerritoriesEntity(EmployeeTerritoriesModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }


}
