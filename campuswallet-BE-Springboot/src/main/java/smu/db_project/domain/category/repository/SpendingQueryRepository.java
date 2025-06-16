package smu.db_project.domain.category.repository;

import org.springframework.stereotype.Repository;
import smu.db_project.domain.entity.Spending;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SpendingQueryRepository extends JpaRepository<Spending, Long> {

}
