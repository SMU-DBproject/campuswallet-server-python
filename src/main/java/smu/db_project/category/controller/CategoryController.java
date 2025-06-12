package smu.db_project.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.db_project.category.dto.CategorySpendingDto;
import smu.db_project.category.dto.MaxSpendingCategoryDto;
import smu.db_project.category.service.CategoryService;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{sNum}")
    public ResponseEntity<List<CategorySpendingDto>> getCategorySpendings(@PathVariable Long sNum) {
        List<CategorySpendingDto> result = categoryService.getCategorySpendings(sNum);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/max/{sNum}")
    public ResponseEntity<MaxSpendingCategoryDto> getMaxCategory(@PathVariable Long sNum) {
        MaxSpendingCategoryDto result = categoryService.getMaxSpendingCategory(sNum);
        return ResponseEntity.ok(result);
    }
}
