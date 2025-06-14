package smu.db_project.category.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smu.db_project.category.dto.CategorySpendingDto;
import smu.db_project.category.dto.MaxSpendingCategoryDto;
import smu.db_project.domain.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

@Repository
public interface SpendingQueryRepository extends JpaRepository<Spending, Long> {

    @Query("""
        SELECT new smu.db_project.category.dto.MaxSpendingCategoryDto(
            c.categoryName,
            SUM(s.amount)
        )
        FROM Spending s
        JOIN s.category c
        WHERE s.student.sNum = :studentId
        GROUP BY c.categoryName
        ORDER BY SUM(s.amount) DESC
        """)
    List<MaxSpendingCategoryDto> findTopSpendingCategory(@Param("studentId") Long studentId);


}
