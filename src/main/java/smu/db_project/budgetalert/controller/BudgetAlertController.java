package smu.db_project.budgetalert.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import smu.db_project.budgetalert.dto.BudgetAlertDto;
import smu.db_project.budgetalert.dto.ExceededCategoryDto;
import smu.db_project.budgetalert.service.BudgetAlertService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class BudgetAlertController {

    private final BudgetAlertService budgetAlertService;

    @GetMapping("/exceed")
    public List<BudgetAlertDto> getExceededCategories(@RequestParam("sNum") Long sNum) {


        System.out.println("🔍 [Controller] 예산 초과 카테고리 요청 - sNum: " + sNum);
        List<BudgetAlertDto> result = budgetAlertService.getAlertsByStudentNum(sNum);
        System.out.println("✅ [Controller] 응답 데이터 크기: " + result.size());
        return result;
    }

    @GetMapping("/exceed2")
    public List<ExceededCategoryDto> getExceededBudgets(@RequestParam("sNum") Long sNum) {
        System.out.println("🔥 [Controller] v_exceeded_budget 조회 - sNum: " + sNum);
        return budgetAlertService.getExceededBudgets(sNum);
    }

}
