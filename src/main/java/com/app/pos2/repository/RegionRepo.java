package com.app.pos2.repository;

import com.app.pos2.entity.RegionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegionRepo extends JpaRepository<RegionEntity,String> {
    List<RegionEntity> findByContactName(String contactName);
    //@Query("select count(t) from RegionEntity t where t.name= :name ")
    //Integer findUpdateSame(@Param("name") String name);
}
