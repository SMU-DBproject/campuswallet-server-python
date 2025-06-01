package smu.db_project.category.dto;

import lombok.Getter;

@Getter
public class MaxSpendingCategoryDto {

    private String categoryName;
    private Long totalSpending;

    public MaxSpendingCategoryDto(String categoryName, Long totalSpending) {
        this.categoryName = categoryName;
        this.totalSpending = totalSpending;
    }
}
