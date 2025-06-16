package smu.db_project.domain.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryBudgetId implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long sNum;
    private Long categoryId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoryBudgetId)) return false;
        CategoryBudgetId that = (CategoryBudgetId) o;
        return Objects.equals(sNum, that.sNum) &&
               Objects.equals(categoryId, that.categoryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sNum, categoryId);
    }
}

