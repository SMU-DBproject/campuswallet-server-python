package smu.db_project.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MaxSpendingCategoryDto {


    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("totalSpending")
    private Long totalSpending;
}
