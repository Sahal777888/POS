package com.app.pos2.repository;

import com.app.pos2.entity.CustomerDemoEntity;
import com.app.pos2.entity.CustomersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerDemoRepo extends JpaRepository<CustomerDemoEntity,String> {
    @Query("select count(t) from CustomerDemoEntity t where t.customerId= :customerId and t.customerDemographicId= :customerDemographicId")
    Integer findSame(@Param("customerId") String customerId, @Param("customerDemographicId") String customerDemographicId);
}
