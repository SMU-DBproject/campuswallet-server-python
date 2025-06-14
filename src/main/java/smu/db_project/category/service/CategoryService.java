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
                .withProcedureName("GET_CATEGORY_SPENDINGS")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_S_NUM", Types.NUMERIC),
                        new SqlOutParameter("P_RESULT", OracleTypes.CURSOR, (rs, rowNum) ->
                                new CategorySpendingDto(
                                        rs.getString("category_name"),
                                        rs.getLong("spending")
                                )
                        ),
                        new SqlOutParameter("P_SUCCESS", Types.VARCHAR),
                        new SqlOutParameter("P_MESSAGE", Types.VARCHAR)
                );

        getMaxSpendingCategoryCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("GET_MAX_SPENDING_CATEGORY_PROC")  // ✅ 정확한 이름
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("P_S_NUM", Types.NUMERIC),               // ✅ 입력
                        new SqlOutParameter("P_CATEGORY", Types.VARCHAR),        // ✅ 출력
                        new SqlOutParameter("P_TOTAL_AMOUNT", Types.NUMERIC)     // ✅ 출력
                );
    }

    public List<CategorySpendingDto> getCategorySpendings(Long studentId) {
        try {
            Map<String, Object> in = new HashMap<>();
            in.put("P_S_NUM", studentId);

            Map<String, Object> out = getCategorySpendingCall.execute(in);

            String success = (String) out.get("P_SUCCESS");
            String message = (String) out.get("P_MESSAGE");

            if (!"Y".equals(success)) {
                throw new RuntimeException("소비 조회 실패: " + message);
            }

            return (List<CategorySpendingDto>) out.get("P_RESULT");

        } catch (Exception e) {
            throw new RuntimeException("소비 조회 중 오류", e);
        }
    }

    public MaxSpendingCategoryDto getMaxSpendingCategory(Long studentId) {
        try {
            System.out.println("📤 [서버] 호출 파라미터 - P_S_NUM: " + studentId);

            Map<String, Object> in = Map.of("P_S_NUM", studentId);
            Map<String, Object> out = getMaxSpendingCategoryCall.execute(in);

            System.out.println("📥 [서버] 프로시저 Raw 결과: " + out);

            String category = (String) out.get("P_CATEGORY");
            Number total = (Number) out.get("P_TOTAL_AMOUNT");

            System.out.println("✅ [서버] 반환값 - category: " + category + ", total: " + total);

            return new MaxSpendingCategoryDto(category, total != null ? total.longValue() : 0L);

        } catch (Exception e) {
            System.out.println("❌ [서버] 예외 발생: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("최대 소비 조회 중 오류", e);
        }
    }
}

