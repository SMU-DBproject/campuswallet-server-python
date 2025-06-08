package smu.db_project.budgetalert.service;

import org.springframework.stereotype.Service;
import smu.db_project.budgetalert.dto.BudgetAlertDto;
import smu.db_project.budgetalert.repository.BudgetAlertRepository;

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
        List<Object[]> rawResults = budgetAlertRepository.findRawAlertsByStudentNum(sNum);
        List<BudgetAlertDto> dtos = new ArrayList<>();
        for(Object[] row : rawResults) {
            String categoryName = (String) row[0];
            String discountName = (String) row[1];
            String description = (String) row[2];
            LocalDate alertDate = row[3] != null ? ((java.sql.Date) row[3]).toLocalDate() : null;
            dtos.add(new BudgetAlertDto(categoryName, discountName, description, alertDate));
        }
        return dtos;
    }

}
