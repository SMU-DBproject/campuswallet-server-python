package smu.db_project.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequestDto {
    @JsonProperty("sNum")  // ✅ JSON 키가 "sNum"일 때 매핑해줘
    private Long sNum;

    @JsonProperty("sId")
    private String sId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password")
    private String password;

    @JsonProperty("categoryBudgets")
    private List<CategoryBudgetDto> categoryBudgets;
}