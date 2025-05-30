package smu.db_project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Table(name = "STUDENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {

    @Id
    @Column(name = "S_NUM", nullable = false)
    private Long sNum;

    @Column(name = "S_ID", nullable = false, unique = true, length = 100)
    @Size(min = 6, max = 13, message = "아이디는 6~13자여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9-_]+$", message = "아이디는 영문 대소문자, 숫자, -, _ 만 사용 가능합니다.")
    private String sId;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    @Size(min = 8, max = 16, message = "비밀번호는 8~16자여야 합니다.")
    @Pattern(
        regexp = "^[a-zA-Z0-9!\"#$%&'()*+,-./:;?@\\[\\\\\\]^_`{|}~]+$",
        message = "비밀번호는 영문 대소문자, 숫자, 특수문자만 사용 가능합니다."
    )
    private String password;
}

