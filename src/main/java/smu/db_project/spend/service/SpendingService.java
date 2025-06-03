// spend/service/SpendingService.java
package smu.db_project.spend.service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import smu.db_project.domain.Category;
import smu.db_project.domain.Spending;
import smu.db_project.domain.Student;
import smu.db_project.spend.dto.SpendingDto;
import smu.db_project.spend.repository.SpendingRepository;
import smu.db_project.category.repository.CategoryRepository;
import smu.db_project.auth.repository.StudentRepository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SpendingService {

    private final SpendingRepository spendingRepository;
    private final StudentRepository studentRepository;
    private final CategoryRepository categoryRepository;

    @Transactional
    public List<SpendingDto> getSpendingByStudent(Long studentId) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("get_spending_by_student");

        query.registerStoredProcedureParameter("p_s_num", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_result", void.class, ParameterMode.REF_CURSOR);

        query.setParameter("p_s_num", studentId);
        query.execute();

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        return results.stream().map(row -> SpendingDto.builder()
                .id(((Number) row[0]).longValue())
                .sNum(((Number) row[1]).longValue())
                .categoryName((String) row[2])
                .amount(((Number) row[3]).intValue())
                .spendDate(java.sql.Date.valueOf((String) row[4]))  // TO_CHAR된 문자열을 DATE로 파싱
                .build()
        ).collect(Collectors.toList());
    }



    @PersistenceContext
    private EntityManager em;

    @Transactional
    public SpendingDto addSpending(SpendingDto dto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("insert_spending");

        query.registerStoredProcedureParameter("p_s_num", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_category", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_amount", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_date", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_new_id", Long.class, ParameterMode.OUT);

        query.setParameter("p_s_num", dto.getSNum());
        query.setParameter("p_category", dto.getCategoryName());
        query.setParameter("p_amount", dto.getAmount());
        query.setParameter("p_date", dto.getSpendDate() == null ? new Date() : dto.getSpendDate());

        query.execute();

        Long newId = ((Number) query.getOutputParameterValue("p_new_id")).longValue();
        dto.setId(newId);

        return dto;
    }


    @Transactional
    public SpendingDto updateSpending(Long id, SpendingDto dto) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("update_spending");

        query.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_new_amount", Integer.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_new_date", Date.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_updated_id", Long.class, ParameterMode.OUT);

        query.setParameter("p_id", id);
        query.setParameter("p_new_amount", dto.getAmount());
        query.setParameter("p_new_date", dto.getSpendDate());

        query.execute();

        Long updatedId = ((Number) query.getOutputParameterValue("p_updated_id")).longValue();

        return SpendingDto.builder()
                .id(updatedId)
                .sNum(dto.getSNum())
                .categoryName(dto.getCategoryName())
                .amount(dto.getAmount())
                .spendDate(dto.getSpendDate())
                .build();
    }


    @Transactional
    public void deleteSpending(Long id) {
        StoredProcedureQuery query = em.createStoredProcedureQuery("delete_spending");
        query.registerStoredProcedureParameter("p_id", Long.class, ParameterMode.IN);
        query.setParameter("p_id", id);
        query.execute();
    }

}
