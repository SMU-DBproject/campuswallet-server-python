package smu.db_project.spend;

public class ExceedCategoryDTO {
    private String categoryName;
    private int totalAmount;
    private int budget;

    public ExceedCategoryDTO(String categoryName, int totalAmount, int budget) {
        this.categoryName = categoryName;
        this.totalAmount = totalAmount;
        this.budget = budget;
    }

    // Getters
    public String getCategoryName() { return categoryName; }
    public int getTotalAmount() { return totalAmount; }
    public int getBudget() { return budget; }
}
