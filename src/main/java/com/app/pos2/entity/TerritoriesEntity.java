package com.app.pos2.entity;

import com.app.pos2.model.TerritoriesModel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name="territories_tab")
public class TerritoriesEntity {
    @Id
    @Column(name = "id", length = 36)
    private String id;
    @Column(name = "territory_desc", length = 150)
    private String territoryDesc;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "created_by", length = 20)
    private String createdBy;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
    @Column(name = "updated_by", length = 20)
    private String updatedBy;

    @Column(name = "region_id", length = 36, insertable = false, updatable = false)
    private String regionId;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="region_id",nullable = false)
    private RegionEntity region;

    public TerritoriesEntity() {    }
    public TerritoriesEntity(String id) {
        this.id = id;
    }

    public TerritoriesEntity(TerritoriesModel model) {
        BeanUtils.copyProperties(model, this);
        this.createdAt = LocalDateTime.now();
        this.createdBy = "SYSTEM";
        this.id = UUID.randomUUID().toString();
    }


}
