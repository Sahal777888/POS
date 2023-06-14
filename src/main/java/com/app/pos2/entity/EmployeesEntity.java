package com.app.pos2.entity;

import com.app.pos2.model.EmployeesModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="employees_tab")
public class EmployeesEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "first_name", length = 150)
    private String firstName;
    @Column(name = "last_name", length = 150)
    private String lastName;
    @Column(name = "title", length = 150)
    private String title;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "hire_date")
    private LocalDate hireDate;
    @Column(name = "city", length = 150)
    private String city;
    @Column(name = "region", length = 150)
    private String region;
    @Column(name = "photo_path", length = 150)
    private String photoPath;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by", length = 20)
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    /*
    @Column(name = "report_id", length = 36, insertable = false, updatable = false)
    private String reportId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="report_id",nullable = false)
    private EmployeesEntity report;
*/
    public EmployeesEntity() {    }
    public EmployeesEntity(String id) {
        this.id = id;
    }
    public EmployeesEntity(EmployeesModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }
}
