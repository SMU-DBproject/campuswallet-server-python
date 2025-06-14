package smu.db_project.domain.budgetalert.dto;

import java.time.LocalDate;

public class BudgetAlertDto {
    private String categoryName;
    private String discountName;
    private String description;
    private LocalDate alertDate;

    public BudgetAlertDto(String categoryName, String discountName, String description, LocalDate alertDate) {
        this.categoryName = categoryName;
        this.discountName = discountName;
        this.description = description;
        this.alertDate = alertDate;
    }

    // Getters
    public String getCategoryName() {
        return categoryName;
    }

    public String getDiscountName() {
        return discountName;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getAlertDate() {
        return alertDate;
    }
}
