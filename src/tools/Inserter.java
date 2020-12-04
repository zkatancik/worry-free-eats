package tools;

import Dao.NutritionsDao;
import Dao.RecipesDao;
import Model.Nutritions;
import Model.Recipes;
import java.math.BigDecimal;
import java.sql.SQLException;

public class Inserter {

  public static void main(String[] args) throws SQLException {
    NutritionsDao nutritionsDao = NutritionsDao.getInstance();
    RecipesDao recipesDao = RecipesDao.getInstance();
    Recipes recipe = new Recipes(12345678, "testRecipe", "cookingDirects", "xxx");
    recipe = recipesDao.create(recipe);
    Nutritions nu =
        new Nutritions(recipe, new BigDecimal(2), new BigDecimal(2), new BigDecimal(2),
          new BigDecimal(2), new BigDecimal(2), new BigDecimal(2), new BigDecimal(2));

    nutritionsDao.create(nu);
    System.out.println("Nutrition Id is:" + nu.getNutritionId());
  }
}
