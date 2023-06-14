package com.app.pos2.repository;

import com.app.pos2.entity.CategoriesEntiity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriesRepo extends JpaRepository<CategoriesEntiity,String> {
    List<CategoriesEntiity> findByCategoryName(String categoryName);
    @Query("select count(t) from CategoriesEntiity t where t.categoryName= :categoryName and t.categoryName != :anchorName")
    Integer findUpdateSame(@Param("categoryName") String categoryName,
                           @Param("anchorName") String anchorName);
}
