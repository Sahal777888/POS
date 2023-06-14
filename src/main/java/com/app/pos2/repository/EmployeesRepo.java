package com.app.pos2.repository;

import com.app.pos2.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeesRepo extends JpaRepository<EmployeesEntity,String> {
    List<EmployeesEntity> findByFirstName(String firstName);
    @Query("select count(t) from EmployeesEntity t where t.firstName= :firstName and t.lastName= :lastName")
    Integer findSame(@Param("firstName") String firstName,
                     @Param("lastName") String lastName);
}
