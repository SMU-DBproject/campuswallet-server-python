package smu.db_project.domain.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MaxSpendingCategoryDto {
    private String categoryName;
    private Long totalSpending;
    private String spendMonth;   // 추가된 소비 월 필드
}
