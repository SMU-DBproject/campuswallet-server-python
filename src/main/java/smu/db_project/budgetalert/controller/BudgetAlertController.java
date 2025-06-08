package smu.db_project.budgetalert.controller;

import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;
import smu.db_project.budgetalert.dto.BudgetAlertDto;
import smu.db_project.budgetalert.service.BudgetAlertService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class BudgetAlertController {

    private final BudgetAlertService budgetAlertService;

    @GetMapping("/exceed")
    public List<BudgetAlertDto> getExceededCategories(@RequestParam("sNum") Long sNum) {
        return budgetAlertService.getAlertsByStudentNum(sNum);
    }
}
