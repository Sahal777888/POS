package com.app.pos2.repository;

import com.app.pos2.entity.UsaStateEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsaStateRepo extends JpaRepository<UsaStateEntity,String> {
    List<UsaStateEntity> findByStateName(String stateName);
    @Query("select count(t) from UsaStateEntity t where t.stateName= :stateName and t.stateName != :anchorName")
    Integer findUpdateSame(@Param("stateName") String stateName,
                           @Param("anchorName") String anchorName);
    /*
    @Query("select count(t) from XE t where t.XZ= :XZ and t.XZ != :anchorName")
    Integer findUpdateSame(@Param("XZ") String XZ,
                           @Param("anchorName") String anchorName);
     */
}
