package smu.db_project.category.service;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.OracleTypes;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import smu.db_project.category.dto.CategorySpendingDto;
import smu.db_project.category.dto.MaxSpendingCategoryDto;
import smu.db_project.category.repository.SpendingQueryRepository;

import javax.sql.DataSource;
import java.sql.Types;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    private SimpleJdbcCall getCategorySpendingCall;

    private SimpleJdbcCall getMaxSpendingCategoryCall;


    @PostConstruct
    private void init() {
        getCategorySpendingCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("get_category_spendings")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_s_num", Types.NUMERIC),
                        new SqlOutParameter("p_result", OracleTypes.CURSOR, (rs, rowNum) ->
                                new CategorySpendingDto(
                                        rs.getString("category_name"),
                                        rs.getLong("spending")
                                )
                        ),
                        new SqlOutParameter("p_success", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR)
                );

        getMaxSpendingCategoryCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("get_max_spending_category")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_s_num", Types.NUMERIC),
                        new SqlOutParameter("p_category", Types.VARCHAR),
                        new SqlOutParameter("p_total", Types.NUMERIC),
                        new SqlOutParameter("p_success", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR)
                );
    }


    public List<CategorySpendingDto> getCategorySpendings(Long studentId) {
        try {
            Map<String, Object> in = new HashMap<>();
            in.put("p_s_num", studentId);

            Map<String, Object> out = getCategorySpendingCall.execute(in);

            String success = (String) out.get("p_success");
            String message = (String) out.get("p_message");

            if (!"Y".equals(success)) {
                throw new RuntimeException("소비 조회 실패: " + message);
            }

            return (List<CategorySpendingDto>) out.get("p_result");

        } catch (Exception e) {
            throw new RuntimeException("소비 조회 중 오류", e);
        }
    }

    public MaxSpendingCategoryDto getMaxSpendingCategory(Long studentId) {
        try {
            Map<String, Object> in = Map.of("p_s_num", studentId);
            Map<String, Object> out = getMaxSpendingCategoryCall.execute(in);

            String success = (String) out.get("p_success");
            String message = (String) out.get("p_message");

            if (!"Y".equals(success)) {
                throw new RuntimeException("최대 소비 조회 실패: %s%n" + message);
            }

            String category = (String) out.get("p_category");
            Number total = (Number) out.get("p_total");

            return new MaxSpendingCategoryDto(category, total != null ? total.longValue() : 0L);

        } catch (Exception e) {
            throw new RuntimeException("최대 소비 조회 중 오류", e);
        }
    }

}
