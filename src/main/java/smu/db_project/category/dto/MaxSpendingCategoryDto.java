package smu.db_project.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MaxSpendingCategoryDto {
    private String categoryName;
    private Long totalSpending;
}
