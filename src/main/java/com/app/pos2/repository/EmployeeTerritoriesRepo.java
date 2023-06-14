package com.app.pos2.repository;

import com.app.pos2.entity.EmployeeTerritoriesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTerritoriesRepo extends JpaRepository<EmployeeTerritoriesEntity,String> {
    @Query("select count(t) from EmployeeTerritoriesEntity t where t.employeeId= :employeeId and t.territoryId= :territoryId")
    Integer findSame(@Param("employeeId") String employeeId,
                     @Param("territoryId") String territoryId);
}
