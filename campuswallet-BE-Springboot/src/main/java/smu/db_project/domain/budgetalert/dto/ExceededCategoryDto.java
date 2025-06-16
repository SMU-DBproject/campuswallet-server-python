package smu.db_project.domain.budgetalert.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceededCategoryDto {
    private String categoryName;
    private int limitAmount;
    private int totalSpent;
}
