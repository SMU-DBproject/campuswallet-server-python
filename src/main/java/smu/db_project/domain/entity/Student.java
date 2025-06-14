package smu.db_project.domain.entity;

import jakarta.persistence.*;
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
    private String sId;

    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @Column(name = "PASSWORD", nullable = false, length = 100)
    private String password;
}
