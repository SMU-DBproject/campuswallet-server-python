package smu.db_project.category.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.db_project.domain.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    // 기본 CRUD 메서드 제공됨
}
