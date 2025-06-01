package smu.db_project.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smu.db_project.category.dto.CategorySpendingDto;
import smu.db_project.category.dto.MaxSpendingCategoryDto;
import smu.db_project.spend.repository.SpendingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final SpendingRepository spendingRepository;

    public List<CategorySpendingDto> getCategorySpendings(Long studentId) {
        return spendingRepository.findSpendingByCategory(studentId);
    }

    public MaxSpendingCategoryDto getMaxSpendingCategory(Long studentId) {
        List<MaxSpendingCategoryDto> result = spendingRepository.findMaxSpendingCategory(studentId);
        return result.isEmpty() ? null : result.get(0);
    }
}
