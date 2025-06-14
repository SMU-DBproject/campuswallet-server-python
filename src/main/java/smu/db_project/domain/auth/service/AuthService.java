package smu.db_project.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smu.db_project.domain.auth.dto.*;
import smu.db_project.domain.auth.repository.AuthRepository;
import smu.db_project.domain.entity.Student;

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
            return new AuthResponseDto("fail", "존재하지 않는 사용자입니다.", null);
        }

        if (!existing.getPassword().equals(request.getPassword())) {
            return new AuthResponseDto("fail", "비밀번호가 올바르지 않습니다." , null);
        }

        return new AuthResponseDto("success", "로그인 성공", existing.getSNum());
    }

    // 회원가입 메서드
    public AuthResponseDto register(RegisterRequestDto request) {
        Student existing = authRepository.findStudentBySId(request.getSId());

        if (existing != null) {
            return new AuthResponseDto("fail", "이미 가입된 사용자입니다.", existing.getSNum());
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

        return new AuthResponseDto("success", "회원가입 및 예산 등록 완료", student.getSNum());
    }

    // 마이페이지 메소드
    // AuthService.java

    public StudentResponseDto getStudentInfoBySId(String sId) {
        Student student = authRepository.findStudentBySId(sId);

        if (student == null) {
            throw new IllegalArgumentException("존재하지 않는 사용자입니다.");
        }

        return new StudentResponseDto(
                student.getSNum(),
                student.getSId(),
                student.getName()
        );
    }

    public AuthResponseDto updateStudentInfo(String currentSId, UpdateStudentRequestDto request) {
        Student student = authRepository.findStudentBySId(currentSId);
        if (student == null) {
            return new AuthResponseDto("fail", "존재하지 않는 사용자입니다.", null);
        }

        // sId(아이디) 변경 시, 중복 확인
        if (request.getSId() != null && !request.getSId().equals(currentSId)) {
            Student duplicated = authRepository.findStudentBySId(request.getSId());
            if (duplicated != null) {
                return new AuthResponseDto("fail", "이미 존재하는 아이디입니다.", null);
            }
            student.setSId(request.getSId());
        }

        if (request.getName() != null) {
            student.setName(request.getName());
        }

        if (request.getPassword() != null) {
            student.setPassword(request.getPassword());
        }

        authRepository.updateStudentFull(student, currentSId); // 기존 ID 기준으로 업데이트

        return new AuthResponseDto("success", "사용자 정보가 수정되었습니다.", student.getSNum());
    }

}