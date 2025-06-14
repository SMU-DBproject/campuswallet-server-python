package smu.db_project.domain.auth.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import smu.db_project.domain.entity.Student;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class AuthRepository {

    private final JdbcTemplate jdbcTemplate;

    private final RowMapper<Student> studentRowMapper = (rs, rowNum) -> {
        Student s = new Student();
        s.setSNum(rs.getLong("S_NUM"));
        s.setSId(rs.getString("S_ID"));
        s.setName(rs.getString("NAME"));
        s.setPassword(rs.getString("PASSWORD"));
        return s;
    };

    // 🔹 기존 학번 기준 조회 메서드
    public Student findStudentBySNum(Long sNum) {
        String sql = "SELECT S_NUM, S_ID, NAME, PASSWORD FROM STUDENT WHERE S_NUM = ?";
        List<Student> list = jdbcTemplate.query(sql, studentRowMapper, sNum);
        return list.isEmpty() ? null : list.get(0);
    }

    // 🔹 신규 추가: 사용자 ID 기준 조회 메서드
    public Student findStudentBySId(String sId) {
        String sql = "SELECT S_NUM, S_ID, NAME, PASSWORD FROM STUDENT WHERE S_ID = ?";
        List<Student> list = jdbcTemplate.query(sql, studentRowMapper, sId);
        return list.isEmpty() ? null : list.get(0);
    }

    public void insertStudent(Student student) {
        String sql = "INSERT INTO STUDENT (S_NUM, S_ID, NAME, PASSWORD) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                student.getSNum(),
                student.getSId(),
                student.getName(),
                student.getPassword());
    }

    public Long findCategoryIdByName(String name) {
        String sql = "SELECT ID FROM CATEGORY WHERE CATEGORY_NAME = ?";
        List<Long> list = jdbcTemplate.query(sql, (rs, rowNum) -> rs.getLong("ID"), name);
        return list.isEmpty() ? null : list.get(0);
    }

    public void insertCategoryBudget(Long sNum, Long categoryId, Integer limitAmount) {
        String sql = "INSERT INTO CATEGORY_BUDGET (S_NUM, CATEGORY_ID, LIMIT_AMOUNT) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, sNum, categoryId, limitAmount);
    }

    public void updateStudentFull(Student updatedStudent, String currentSId) {
        String sql = "UPDATE STUDENT SET S_ID = ?, NAME = ?, PASSWORD = ? WHERE S_ID = ?";
        jdbcTemplate.update(sql,
                updatedStudent.getSId(),
                updatedStudent.getName(),
                updatedStudent.getPassword(),
                currentSId // WHERE 절에는 수정 전 ID 사용
        );
    }


}