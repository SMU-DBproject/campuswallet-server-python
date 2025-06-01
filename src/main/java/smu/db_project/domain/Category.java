package smu.db_project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "CATEGORY")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @Id
    @Column(name = "CATEGORY_NAME", length = 100, nullable = false)
    private String categoryName;

    @Column(name = "DESCRIPTION", length = 300)
    private String description;

    @Min(0)
    @Max(6)
    @Column(name = "PRIORITY_LEVEL", nullable = false)
    private Integer priorityLevel;

    @Enumerated(EnumType.STRING)
    @Column(name = "IS_ESSENTIAL", nullable = false, length = 1)
    private YesNo isEssential; // 'Y' or 'N'
}
