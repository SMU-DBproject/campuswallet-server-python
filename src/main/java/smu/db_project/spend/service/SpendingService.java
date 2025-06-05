package smu.db_project.spend.service;

import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.OracleTypes;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import smu.db_project.spend.dto.SpendingDto;

import jakarta.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Types;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SpendingService {

    private final JdbcTemplate jdbcTemplate;
    private final DataSource dataSource;

    private SimpleJdbcCall insertCall;
    private SimpleJdbcCall updateCall;
    private SimpleJdbcCall deleteCall;
    private SimpleJdbcCall selectByStudentCall;

    @PostConstruct
    private void init() {
        insertCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("insert_spending")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_s_num", Types.NUMERIC),
                        new SqlParameter("p_category", Types.VARCHAR),
                        new SqlParameter("p_amount", Types.NUMERIC),
                        new SqlParameter("p_date", Types.DATE),
                        new SqlOutParameter("p_success", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR),
                        new SqlOutParameter("p_id", Types.NUMERIC)
                );

        updateCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("update_spending")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlParameter("p_new_amount", Types.NUMERIC),
                        new SqlParameter("p_new_date", Types.DATE),
                        new SqlParameter("p_new_category", Types.VARCHAR),
                        new SqlOutParameter("p_success", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR),
                        new SqlOutParameter("p_id_out", Types.NUMERIC)
                );



        deleteCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("delete_spending")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_id", Types.NUMERIC),
                        new SqlOutParameter("p_success", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR)
                );


        selectByStudentCall = new SimpleJdbcCall(dataSource)
                .withProcedureName("get_spending_by_student")
                .withoutProcedureColumnMetaDataAccess()
                .declareParameters(
                        new SqlParameter("p_s_num", Types.NUMERIC),
                        new SqlOutParameter("p_result", OracleTypes.CURSOR,
                                (rs, rowNum) -> SpendingDto.builder()
                                        .id(rs.getLong("id"))
                                        .sNum(rs.getLong("s_num"))
                                        .categoryName(rs.getString("category_name"))
                                        .amount(rs.getInt("amount"))
                                        .spendDate(rs.getDate("spend_date"))
                                        .build()
                        ),
                        new SqlOutParameter("p_success", Types.VARCHAR),
                        new SqlOutParameter("p_message", Types.VARCHAR)
                );


    }

    public List<SpendingDto> getSpendingsByStudent(Long sNum) {
        try {
            Map<String, Object> inParams = Collections.singletonMap("p_s_num", sNum);
            Map<String, Object> result = selectByStudentCall.execute(inParams);

            String success = (String) result.get("p_success");
            String message = (String) result.get("p_message");

            if (!"Y".equalsIgnoreCase(success)) {
                throw new RuntimeException("조회 실패: " + message);
            }

            return (List<SpendingDto>) result.get("p_result");

        } catch (DataAccessException e) {
            throw new RuntimeException("지출 내역 조회 중 DB 오류", e);
        }
    }

    public SpendingDto addSpending(SpendingDto dto) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("p_s_num", dto.getSNum());
            params.put("p_category", dto.getCategoryName());
            params.put("p_amount", dto.getAmount());
            params.put("p_date", new Date(dto.getSpendDate().getTime()));

            Map<String, Object> result = insertCall.execute(params);

            String success = (String) result.get("p_success");
            String message = (String) result.get("p_message");
            Number insertedId = (Number) result.get("p_id");

            System.out.printf("결과: success=%s, message=%s, id=%s%n", success, message, insertedId);

            if (!"Y".equals(success)) {
                throw new RuntimeException("지출 추가 실패: " + message);
            }

            dto.setId(insertedId != null ? insertedId.longValue() : null);
            return dto;
        } catch (DataAccessException e) {
            throw new RuntimeException("지출 추가 실패", e);
        }
    }

    public SpendingDto updateSpending(Long id, SpendingDto dto) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("p_id", id);
            params.put("p_new_amount", dto.getAmount());
            params.put("p_new_date", new Date(dto.getSpendDate().getTime()));
            params.put("p_new_category", dto.getCategoryName());

            Map<String, Object> result = updateCall.execute(params);

            String success = (String) result.get("p_success");
            String message = (String) result.get("p_message");
            Number idOut = (Number) result.get("p_id_out");

            System.out.printf("UPDATE 결과: success=%s, message=%s, id_out=%s%n", success, message, idOut);

            if (!"Y".equals(success)) {
                throw new RuntimeException("지출 수정 실패: " + message);
            }

            dto.setId(idOut != null ? idOut.longValue() : null);
            return dto;
        } catch (DataAccessException e) {
            throw new RuntimeException("지출 수정 실패", e);
        }
    }

    public void deleteSpending(Long id) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("p_id", id);

            Map<String, Object> result = deleteCall.execute(params);

            String success = (String) result.get("p_success");
            String message = (String) result.get("p_message");

            System.out.printf("DELETE 결과: success=%s, message=%s%n", success, message);

            if (!"Y".equals(success)) {
                throw new RuntimeException("지출 삭제 실패: " + message);
            }
        } catch (DataAccessException e) {
            throw new RuntimeException("지출 삭제 실패", e);
        }
    }



}
