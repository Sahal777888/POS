package com.app.pos2.repository;

import com.app.pos2.entity.SuppliersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SuppliersRepo extends JpaRepository<SuppliersEntity,String> {
    List<SuppliersEntity> findByCompanyName(String companyName);
    @Query("select count(t) from SuppliersEntity t where t.companyName= :companyName and t.companyName != :anchorName")
    Integer findUpdateSame(@Param("companyName") String companyName,
                           @Param("anchorName") String anchorName);
}
