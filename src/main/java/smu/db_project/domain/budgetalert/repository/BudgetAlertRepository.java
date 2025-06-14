package smu.db_project.domain.budgetalert.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import smu.db_project.domain.entity.BudgetAlert;

import java.util.List;

@Repository
public interface BudgetAlertRepository extends JpaRepository<BudgetAlert, Long> {

    @Query(value = """
        SELECT 
            category_name AS categoryName,
            discount_name AS discountName,
            description,
            alert_date AS alertDate
        FROM 
            v_budget_alert_detail
        WHERE 
            s_num = :sNum
        """, nativeQuery = true)

    List<Object[]> findRawAlertsByStudentNum(@Param("sNum") Long sNum);


    @Query(value = """
    SELECT category_name, limit_amount, total_spent
    FROM v_exceeded_budget
    WHERE s_num = :sNum
""", nativeQuery = true)
    List<Object[]> findExceededBudgets(@Param("sNum") Long sNum);

}
