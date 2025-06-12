package smu.db_project.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smu.db_project.auth.dto.AuthResponseDto;
import smu.db_project.auth.dto.CategoryBudgetDto;
import smu.db_project.auth.dto.LoginRequestDto;
import smu.db_project.auth.dto.RegisterRequestDto;
import smu.db_project.auth.repository.AuthRepository;
import smu.db_project.domain.Student;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;

    // 로그인 메서드
    public AuthResponseDto login(LoginRequestDto request) {
        System.out.println("로그인 요청 도착!");
        System.out.println("sId: " + request.getSId());
        System.out.println("password: " + request.getPassword());

        Student existing = authRepository.findStudentBySId(request.getSId());

        if (existing == null) {
            return new AuthResponseDto("fail", "존재하지 않는 사용자입니다.");
        }

        if (!existing.getPassword().equals(request.getPassword())) {
            return new AuthResponseDto("fail", "비밀번호가 올바르지 않습니다.");
        }

        return new AuthResponseDto("success", "로그인 성공");
    }

    // 회원가입 메서드
    public AuthResponseDto register(RegisterRequestDto request) {
        Student existing = authRepository.findStudentBySId(request.getSId());

        if (existing != null) {
            return new AuthResponseDto("fail", "이미 가입된 사용자입니다.");
        }

        // 새로운 사용자 등록
        Student student = new Student();
        student.setSNum(request.getSNum());
        student.setSId(request.getSId());
        student.setName(request.getName());
        student.setPassword(request.getPassword());
        authRepository.insertStudent(student);

        // 소비 카테고리 등록
        if (request.getCategoryBudgets() != null) {
            for (CategoryBudgetDto b : request.getCategoryBudgets()) {
                Long catId = authRepository.findCategoryIdByName(b.getCategoryName());
                if (catId != null) {
                    authRepository.insertCategoryBudget(request.getSNum(), catId, b.getLimitAmount());
                }
            }
        }

        return new AuthResponseDto("success", "회원가입 및 예산 등록 완료");
    }
}