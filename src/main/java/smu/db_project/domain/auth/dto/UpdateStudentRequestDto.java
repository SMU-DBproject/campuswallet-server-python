package smu.db_project.domain.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateStudentRequestDto {
    private String name;
    private String sId;
    private String password;
}
