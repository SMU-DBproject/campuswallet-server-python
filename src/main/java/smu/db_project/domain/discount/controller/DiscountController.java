package smu.db_project.domain.discount.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import smu.db_project.domain.entity.Discount;
import smu.db_project.domain.discount.service.DiscountService;

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
