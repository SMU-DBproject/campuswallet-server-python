package smu.db_project.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StudentResponseDto {
    private Long sNum;
    private String sId;
    private String name;
    // password는 보안상 포함하지 않음
}
