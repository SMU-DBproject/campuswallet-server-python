package smu.db_project.category.service;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import smu.db_project.category.dto.CategorySpendingDto;
import smu.db_project.category.dto.MaxSpendingCategoryDto;
import smu.db_project.spend.repository.SpendingRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final SpendingRepository spendingRepository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public List<CategorySpendingDto> getCategorySpendings(Long studentId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("get_category_spendings");

        query.registerStoredProcedureParameter("p_s_num", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_result", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_s_num", studentId);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results.stream().map(row -> new CategorySpendingDto(
                (String) row[0],
                (long) ((Number) row[1]).intValue()
        )).collect(Collectors.toList());
    }


    @Transactional
    public MaxSpendingCategoryDto getMaxSpendingCategory(Long studentId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("get_max_spending_category_proc");

        query.registerStoredProcedureParameter("p_s_num", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_category", String.class, ParameterMode.OUT);
        query.registerStoredProcedureParameter("p_total_amount", Long.class, ParameterMode.OUT);

        query.setParameter("p_s_num", studentId);
        query.execute();

        String category = (String) query.getOutputParameterValue("p_category");
        Number amount = (Number) query.getOutputParameterValue("p_total_amount");

        if (category == null || amount == null) {
            return null;
        }

        return new MaxSpendingCategoryDto(category, amount.longValue());
    }


}
