package smu.db_project.category.dto;

import lombok.Getter;

@Getter
public class CategorySpendingDto {

    private String categoryName;
    private Long totalSpending;

    public CategorySpendingDto(String categoryName, Long totalSpending) {
        this.categoryName = categoryName;
        this.totalSpending = totalSpending;
    }
}
