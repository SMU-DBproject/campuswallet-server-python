package smu.db_project.category.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.db_project.category.dto.CategorySpendingDto;
import smu.db_project.category.dto.MaxSpendingCategoryDto;
import smu.db_project.category.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // TODO: 로그인 처리 후 학생 ID 받아오는 방식으로 바꾸기
    private static final Long MOCK_STUDENT_ID = 1L;

    @GetMapping
    public ResponseEntity<List<CategorySpendingDto>> getCategorySpendings() {
        List<CategorySpendingDto> result = categoryService.getCategorySpendings(MOCK_STUDENT_ID);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/max")
    public ResponseEntity<MaxSpendingCategoryDto> getMaxCategory() {
        MaxSpendingCategoryDto result = categoryService.getMaxSpendingCategory(MOCK_STUDENT_ID);
        return result != null ? ResponseEntity.ok(result) : ResponseEntity.noContent().build();
    }
}
