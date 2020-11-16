package servlet;

import Dao.RecipesDao;
import Model.Recipes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/updaterecipe")
public class UpdateRecipe extends HttpServlet {
  protected RecipesDao recipesDao;

  @Override
  public void init() throws ServletException {
    recipesDao = RecipesDao.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    try {
      int recipeId = Integer.parseInt(req.getParameter("recipeid"));
      Recipes recipe = recipesDao.getRecipeById(recipeId);
      if (null == recipe) {
        messages.put("success", "Recipe id does not exist.");
      }
    } catch (NumberFormatException | SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.getRequestDispatcher("UpdateRecipe.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);

    try {
      int recipeId = Integer.parseInt(req.getParameter("recipeid"));
      Recipes recipe = recipesDao.getRecipeById(recipeId);
      if (null == recipe) {
        messages.put("success", "Please enter a valid recipeId");
      } else {
        String newRecipeName = req.getParameter("recipename");
        String newImgUrl = req.getParameter("imgurl");
        if (null == newImgUrl || newImgUrl.trim().isEmpty() || null == newRecipeName || newRecipeName.trim().isEmpty()) {
          messages.put("success", "Please enter valid recipe info");
        } else {
          recipe = recipesDao.updateRecipe(recipe, newRecipeName, newImgUrl);
          messages.put("success", "Successfully updated recipeId " + recipeId);
        }
      }
    } catch (NumberFormatException | SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    req.getRequestDispatcher("UpdateRecipe.jsp").forward(req, resp);
  }
}
