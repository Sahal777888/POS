package com.app.pos2.entity;

import com.app.pos2.model.OrdersModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="orders_tab")
public class OrdersEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "name", length = 150)
    private String name;
    @Column(name = "order_date")
    private LocalDate orderDate;
    @Column(name = "required_date")
    private LocalDate requiredDate;
    @Column(name = "shipped_date")
    private LocalDate shippedDate;
    @Column(name = "ship_via", length = 150)
    private Integer shipVia;
    @Column(name = "freight", length = 150)
    private Integer freight;
    @Column(name = "ship_name", length = 150)
    private String shipName;
    @Column(name = "ship_address", length = 150)
    private String shipAddress;
    @Column(name = "ship_city", length = 150)
    private String shipCity;
    @Column(name = "ship_region", length = 150)
    private String shipRegion;
    @Column(name = "ship_postal_code", length = 150)
    private Integer shipPostalCode;
    @Column(name = "ship_country", length = 150)
    private String shipCountry;

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

    @Column(name = "shipper_id", length = 36, insertable = false, updatable = false)
    private String shipperId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="shipper_id",nullable = false)
    private ShippersEntity Shipper;

    @Column(name = "employee_id", length = 36, insertable = false, updatable = false)
    private String employeeId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="employee_id",nullable = false)
    private EmployeesEntity employee;

    public OrdersEntity() {    }
    public OrdersEntity(String id) {
        this.id = id;
    }
    public OrdersEntity(OrdersModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }
}
