package smu.db_project.domain.spend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.db_project.domain.entity.Spending;

public interface SpendingRepository extends JpaRepository<Spending, Long> {
    // Stored Procedure 사용으로 현재 미사용
}
