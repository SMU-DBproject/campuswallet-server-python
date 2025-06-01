// CategoryBudget.java
package smu.db_project.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Table(name = "CATEGORY_BUDGET")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"student", "category"})

public class CategoryBudget {

    @EmbeddedId
    private CategoryBudgetId id;

    @MapsId("sNum") // CategoryBudgetId 필드명과 맞춤
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "S_NUM", nullable = false)
    private Student student;

    @MapsId("categoryName")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_NAME", nullable = false)
    private Category category;

    @Min(1)
    @Column(name = "LIMIT_AMOUNT", nullable = false)
    private Integer limitAmount;
}

