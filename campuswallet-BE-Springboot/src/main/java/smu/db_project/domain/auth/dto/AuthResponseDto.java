package smu.db_project.domain.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponseDto {
    private String status;
    private String message;
    private Long sNum;

}
