package Model;

import java.math.BigDecimal;

public class Nutritions {
  private Integer nutritionId;
  private Recipes recipe;
  private BigDecimal calories;
  private BigDecimal fat;
  private BigDecimal carbohydrates;
  private BigDecimal protein;
  private BigDecimal cholesterol;
  private BigDecimal sodium;
  private BigDecimal fiber;

  // Constructor with nutritionId
  public Nutritions(Integer nutritionId, Recipes recipe, BigDecimal calories,
      BigDecimal fat, BigDecimal carbohydrates, BigDecimal protein, BigDecimal cholesterol,
      BigDecimal sodium, BigDecimal fiber) {
    this.nutritionId = nutritionId;
    this.recipe = recipe;
    this.calories = calories;
    this.fat = fat;
    this.carbohydrates = carbohydrates;
    this.protein = protein;
    this.cholesterol = cholesterol;
    this.sodium = sodium;
    this.fiber = fiber;
  }
  // Constructor without nutritionId
  public Nutritions(Recipes recipe, BigDecimal calories, BigDecimal fat,
      BigDecimal carbohydrates, BigDecimal protein, BigDecimal cholesterol,
      BigDecimal sodium, BigDecimal fiber) {
    this.recipe = recipe;
    this.calories = calories;
    this.fat = fat;
    this.carbohydrates = carbohydrates;
    this.protein = protein;
    this.cholesterol = cholesterol;
    this.sodium = sodium;
    this.fiber = fiber;
  }

  public Integer getNutritionId() {
    return nutritionId;
  }

  public void setNutritionId(Integer nutritionId) {
    this.nutritionId = nutritionId;
  }

  public Recipes getRecipe() {
    return recipe;
  }

  public void setRecipe(Recipes recipe) {
    this.recipe = recipe;
  }

  public BigDecimal getCalories() {
    return calories;
  }

  public void setCalories(BigDecimal calories) {
    this.calories = calories;
  }

  public BigDecimal getFat() {
    return fat;
  }

  public void setFat(BigDecimal fat) {
    this.fat = fat;
  }

  public BigDecimal getCarbohydrates() {
    return carbohydrates;
  }

  public void setCarbohydrates(BigDecimal carbohydrates) {
    this.carbohydrates = carbohydrates;
  }

  public BigDecimal getProtein() {
    return protein;
  }

  public void setProtein(BigDecimal protein) {
    this.protein = protein;
  }

  public BigDecimal getCholesterol() {
    return cholesterol;
  }

  public void setCholesterol(BigDecimal cholesterol) {
    this.cholesterol = cholesterol;
  }

  public BigDecimal getSodium() {
    return sodium;
  }

  public void setSodium(BigDecimal sodium) {
    this.sodium = sodium;
  }

  public BigDecimal getFiber() {
    return fiber;
  }

  public void setFiber(BigDecimal fiber) {
    this.fiber = fiber;
  }
}
