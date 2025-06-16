package smu.db_project.domain.budgetalert.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import smu.db_project.domain.budgetalert.dto.BudgetAlertDto;
import smu.db_project.domain.budgetalert.dto.ExceededCategoryDto;
import smu.db_project.domain.budgetalert.service.BudgetAlertService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class BudgetAlertController {

    private final BudgetAlertService budgetAlertService;


    @GetMapping("/exceed")
    public List<ExceededCategoryDto> getExceededBudgets(@RequestParam("sNum") Long sNum) {
        System.out.println("🔥 [Controller] v_exceeded_budget 조회 - sNum: " + sNum);
        return budgetAlertService.getExceededBudgets(sNum);
    }

}
