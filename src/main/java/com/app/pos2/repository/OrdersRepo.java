package com.app.pos2.repository;

import com.app.pos2.entity.OrdersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersRepo extends JpaRepository<OrdersEntity,String> {
    //List<OrdersEntity> findByName(String name);
    @Query("select count(t) from OrdersEntity t where t.customerId= :customerId and t.employeeId= :employeeId and t.shipperId=:shipperId")
    Integer findSame(@Param("customerId") String customerId,
                     @Param("employeeId") String employeeId,
                     @Param("shipperId") String shipperId);
}
