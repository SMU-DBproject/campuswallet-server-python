// CategoryBudgetId.java
package smu.db_project.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CategoryBudgetId implements Serializable {

    @Column(name = "S_NUM")
    private Long sNum;

    @Column(name = "CATEGORY_NAME")
    private String categoryName;
}
