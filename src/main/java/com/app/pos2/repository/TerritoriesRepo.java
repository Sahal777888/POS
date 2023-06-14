package com.app.pos2.repository;

import com.app.pos2.entity.TerritoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TerritoriesRepo extends JpaRepository<TerritoriesEntity,String> {
    List<TerritoriesEntity> findByTerritoryDesc(String territoryDesc);
    @Query("select count(t) from TerritoriesEntity t where t.territoryDesc= :territoryDesc and t.territoryDesc != :anchorName")
    Integer findUpdateSame(@Param("territoryDesc") String territoryDesc,
                           @Param("anchorName") String anchorName);
}
