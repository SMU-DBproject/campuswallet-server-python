package smu.db_project.discount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import smu.db_project.domain.Discount;

import java.util.List;

public interface DiscountRepository extends JpaRepository<Discount, Long> {

	@Query(value = """
		    SELECT cb.category_id
		    FROM category_budget cb
		    JOIN spending s
		      ON cb.category_id = s.category_id
		     AND cb.s_num = s.s_num
		    WHERE cb.s_num = :studentId
		    GROUP BY cb.category_id, cb.limit_amount
		    HAVING SUM(s.amount) > cb.limit_amount
		""", nativeQuery = true)
		List<Long> findOverBudgetCategories(@Param("studentId") Long studentId);


    @Query(value = """
        SELECT category_id
        FROM (
            SELECT s.category_id, SUM(s.amount) AS total
            FROM spending s
            WHERE s.s_num = :studentId
            GROUP BY s.category_id
            ORDER BY total DESC
        )
        WHERE ROWNUM = 1
    """, nativeQuery = true)
    Long findTopSpendingCategory(@Param("studentId") Long studentId);

    @Query("SELECT d FROM Discount d WHERE d.category.id = :categoryId")
    List<Discount> findByCategoryId(@Param("categoryId") Long categoryId);
}
