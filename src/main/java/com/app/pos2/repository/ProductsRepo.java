package com.app.pos2.repository;

import com.app.pos2.entity.ProductsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductsRepo extends JpaRepository<ProductsEntity,String>{
    List<ProductsEntity> findByName(String name);
    @Query("select count(t) from ProductsEntity t where t.name= :name and t.name != :anchorName")
    Integer findUpdateSame(@Param("name") String name,
                           @Param("anchorName") String anchorName);
}
