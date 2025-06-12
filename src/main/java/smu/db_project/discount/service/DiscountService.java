package smu.db_project.discount.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smu.db_project.domain.Discount;
import smu.db_project.discount.repository.DiscountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountService {

    private final DiscountRepository discountRepository;

    public List<Discount> getRecommendedDiscounts(Long studentId) {
        List<Discount> result = new ArrayList<>();

        List<Long> overBudgetCategories = discountRepository.findOverBudgetCategories(studentId);
        if (!overBudgetCategories.isEmpty()) {
            for (Long categoryId : overBudgetCategories) {
                result.addAll(discountRepository.findByCategoryId(categoryId));
            }
            return result;
        }

        Long topCategoryId = discountRepository.findTopSpendingCategory(studentId);
        if (topCategoryId != null) {
            result.addAll(discountRepository.findByCategoryId(topCategoryId));
        }

        return result;
    }
}
