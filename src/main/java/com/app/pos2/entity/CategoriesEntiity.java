package com.app.pos2.entity;

import com.app.pos2.model.CategoriesModel;
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
@Table(name="categories_tab")
public class CategoriesEntiity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "category_name", length = 150)
    private String categoryName;
    @Column(name = "category_desc", length = 150)
    private String categoryDesc;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by", length = 20)
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    public CategoriesEntiity() {    }
    public CategoriesEntiity(String id) {
        this.id = id;
    }

    public CategoriesEntiity(CategoriesModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }

}
