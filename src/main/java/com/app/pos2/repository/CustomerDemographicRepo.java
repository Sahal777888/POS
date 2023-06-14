package com.app.pos2.repository;

import com.app.pos2.entity.CustomerDemographicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDemographicRepo extends JpaRepository<CustomerDemographicEntity,String> {
    List<CustomerDemographicEntity> findByCustomerDesc(String customerDesc);
    @Query("select count(t) from CustomerDemographicEntity t where t.customerDesc= :customerDesc and t.customerDesc != :anchorName")
    Integer findUpdateSame(@Param("customerDesc") String customerDesc,
                           @Param("anchorName") String anchorName);
}
