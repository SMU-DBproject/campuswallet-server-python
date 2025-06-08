package smu.db_project.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smu.db_project.auth.dto.AuthResponseDto;
import smu.db_project.auth.dto.CategoryBudgetDto;
import smu.db_project.auth.dto.LoginRequestDto;
import smu.db_project.auth.dto.StudentDto;
import smu.db_project.auth.repository.AuthRepository;
import smu.db_project.domain.Student;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    public AuthResponseDto loginOrSignup(LoginRequestDto request) {
        StudentDto dto = request.getStudent();
        Student existing = authRepository.findStudentBySNum(dto.getSNum());

        if (existing == null) {
            // sign up
            Student student = new Student();
            student.setSNum(dto.getSNum());
            student.setSId(dto.getSId());
            student.setName(dto.getName());
            student.setPassword(dto.getPassword());
            authRepository.insertStudent(student);

            if (request.getCategoryBudgets() != null) {
                for (CategoryBudgetDto b : request.getCategoryBudgets()) {
                    Long catId = authRepository.findCategoryIdByName(b.getCategoryName());
                    if (catId != null) {
                        authRepository.insertCategoryBudget(dto.getSNum(), catId, b.getLimitAmount());
                    }
                }
            }
            return new AuthResponseDto("success", "회원가입 및 예산 등록 완료");
        } else {
            if (!existing.getPassword().equals(dto.getPassword())) {
                return new AuthResponseDto("fail", "비밀번호가 올바르지 않습니다.");
            }
            return new AuthResponseDto("success", "로그인 성공");
        }
    }
}
