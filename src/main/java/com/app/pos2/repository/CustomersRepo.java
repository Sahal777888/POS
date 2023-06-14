package com.app.pos2.repository;

import com.app.pos2.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomersRepo extends JpaRepository<CustomersEntity,String> {
    List<CustomersEntity> findByContactName(String contactName);
    @Query("select count(t) from CustomersEntity t where t.contactName= :contactName and t.contactName != :anchorName")
    Integer findUpdateSame(@Param("contactName") String contactName,
                           @Param("anchorName") String anchorName);
}
