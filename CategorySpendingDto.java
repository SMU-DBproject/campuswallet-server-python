package smu.db_project.category.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategorySpendingDto {
    private String categoryName;
    private String spendMonth;   // 추가
    private Long spending;       // 변경
}
