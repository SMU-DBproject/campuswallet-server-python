// src/main/java/smu/db_project/domain/repository/StudentRepository.java
package smu.db_project.domain.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smu.db_project.domain.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
    // 기본 CRUD 메서드 제공됨
}
