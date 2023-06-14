package com.app.pos2.repository;

import com.app.pos2.entity.OrderDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailsRepo extends JpaRepository<OrderDetailsEntity,String> {
    @Query("select count(t) from OrderDetailsEntity t where t.productId= :productId and t.orderId= :orderId")
    Integer findSame(@Param("productId") String productId,
                     @Param("orderId") String orderId);
}
