package smu.db_project.budgetalert.service;

import org.springframework.stereotype.Service;
import smu.db_project.budgetalert.dto.BudgetAlertDto;
import smu.db_project.budgetalert.repository.BudgetAlertRepository;
import smu.db_project.budgetalert.dto.ExceededCategoryDto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class BudgetAlertService {

    private final BudgetAlertRepository budgetAlertRepository;

    public BudgetAlertService(BudgetAlertRepository budgetAlertRepository) {
        this.budgetAlertRepository = budgetAlertRepository;
    }

    public List<BudgetAlertDto> getAlertsByStudentNum(Long sNum) {

        System.out.println("🔧 [Service] 예산 초과 데이터 조회 시작 - sNum: " + sNum);
        List<Object[]> rawResults = budgetAlertRepository.findRawAlertsByStudentNum(sNum);

        System.out.println("📦 [Service] DB로부터 받은 rawResults 개수: " + rawResults.size());

        List<BudgetAlertDto> dtos = new ArrayList<>();
        for(Object[] row : rawResults) {
            String categoryName = (String) row[0];
            String discountName = (String) row[1];
            String description = (String) row[2];
            LocalDate alertDate = row[3] != null ? ((java.sql.Date) row[3]).toLocalDate() : null;

            System.out.println("➡️ [Service] 매핑 중 - category: " + categoryName + ", 할인: " + discountName + ", 날짜: " + alertDate);
            dtos.add(new BudgetAlertDto(categoryName, discountName, description, alertDate));
        }
        return dtos;
    }

    public List<ExceededCategoryDto> getExceededBudgets(Long sNum) {
        List<Object[]> rawResults = budgetAlertRepository.findExceededBudgets(sNum);
        List<ExceededCategoryDto> dtos = new ArrayList<>();

        for (Object[] row : rawResults) {
            String categoryName = (String) row[0];
            int limitAmount = ((Number) row[1]).intValue();
            int totalSpent = ((Number) row[2]).intValue();
            dtos.add(new ExceededCategoryDto(categoryName, limitAmount, totalSpent));
        }

        System.out.println("📦 [Service] 예산 초과 항목 개수: " + dtos.size());
        return dtos;
    }

}
