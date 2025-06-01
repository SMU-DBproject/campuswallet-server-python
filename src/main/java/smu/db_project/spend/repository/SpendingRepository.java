package smu.db_project.spend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import smu.db_project.domain.Spending;
import smu.db_project.category.dto.CategorySpendingDto;
import smu.db_project.category.dto.MaxSpendingCategoryDto;

import java.util.List;

public interface SpendingRepository extends JpaRepository<Spending, Long> {

    @Query("SELECT new smu.db_project.category.dto.CategorySpendingDto(s.categoryName.categoryName, SUM(s.amount)) " +
            "FROM Spending s WHERE s.sNum.sNum = :studentId GROUP BY s.categoryName.categoryName")
    List<CategorySpendingDto> findSpendingByCategory(@Param("studentId") Long studentId);

    @Query("SELECT new smu.db_project.category.dto.MaxSpendingCategoryDto(s.categoryName.categoryName, SUM(s.amount)) " +
            "FROM Spending s WHERE s.sNum.sNum = :studentId GROUP BY s.categoryName.categoryName " +
            "ORDER BY SUM(s.amount) DESC")
    List<MaxSpendingCategoryDto> findMaxSpendingCategory(@Param("studentId") Long studentId);
}
