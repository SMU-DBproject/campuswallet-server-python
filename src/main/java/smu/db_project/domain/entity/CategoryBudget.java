package smu.db_project.domain.entity;

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

    @MapsId("sNum")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "S_NUM", nullable = false)
    private Student student;

    @MapsId("categoryId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private Category category;

    @Min(0)
    @Column(name = "LIMIT_AMOUNT", nullable = false)
    private Integer limitAmount;
}
