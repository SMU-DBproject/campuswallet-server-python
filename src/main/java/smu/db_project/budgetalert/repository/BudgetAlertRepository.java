package smu.db_project.budgetalert.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BudgetAlertRepository {

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
}
