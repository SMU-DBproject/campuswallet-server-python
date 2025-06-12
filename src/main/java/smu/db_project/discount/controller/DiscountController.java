package smu.db_project.discount.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import smu.db_project.domain.Discount;
import smu.db_project.discount.service.DiscountService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sales")
public class DiscountController {

    private final DiscountService discountService;

    @GetMapping("/recommendation")
    public List<Discount> getRecommendedDiscounts(@RequestParam("studentId") Long studentId) {
        return discountService.getRecommendedDiscounts(studentId);
    }
}
