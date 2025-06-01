// spend/service/SpendingService.java
package smu.db_project.spend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smu.db_project.domain.Category;
import smu.db_project.domain.Spending;
import smu.db_project.domain.Student;
import smu.db_project.spend.dto.SpendingDto;
import smu.db_project.spend.repository.SpendingRepository;
import smu.db_project.category.repository.CategoryRepository;
import smu.db_project.auth.repository.StudentRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpendingService {

    private final SpendingRepository spendingRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;

    public List<SpendingDto> getAllSpendings() {
        return spendingRepository.findAll().stream()
                .map(s -> SpendingDto.builder()
                        .id(s.getId())
                        .sNum(s.getSNum().getSNum())
                        .categoryName(s.getCategoryName().getCategoryName())
                        .amount(s.getAmount())
                        .spendDate(s.getSpendDate())
                        .build())
                .collect(Collectors.toList());
    }


    @Transactional
    public SpendingDto addSpending(SpendingDto dto) {
        Student student = studentRepository.findById(dto.getSNum())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 학생"));
        Category category = categoryRepository.findById(dto.getCategoryName())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 카테고리"));

        Spending spending = new Spending();
        spending.setSNum(student);
        spending.setCategoryName(category);
        spending.setAmount(dto.getAmount());
        spending.setSpendDate(dto.getSpendDate() == null ? new Date() : dto.getSpendDate());

        Spending saved = spendingRepository.save(spending);

        dto.setId(saved.getId());
        return dto;
    }

    @Transactional
    public SpendingDto updateSpending(Long id, SpendingDto dto) {
        Spending spending = spendingRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 소비"));

        if (dto.getAmount() != null)
            spending.setAmount(dto.getAmount());
        if (dto.getSpendDate() != null)
            spending.setSpendDate(dto.getSpendDate());

        return SpendingDto.builder()
                .id(spending.getId())
                .sNum(spending.getSNum().getSNum())
                .categoryName(spending.getCategoryName().getCategoryName())
                .amount(spending.getAmount())
                .spendDate(spending.getSpendDate())
                .build();
    }

    @Transactional
    public void deleteSpending(Long id) {
        spendingRepository.deleteById(id);
    }
}
