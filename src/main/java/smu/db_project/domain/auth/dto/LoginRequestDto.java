
package smu.db_project.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequestDto {
    @JsonProperty("sId")
    private String sId;  // 사용자 ID

    @JsonProperty("password")
    private String password;  // 비밀번호
}
