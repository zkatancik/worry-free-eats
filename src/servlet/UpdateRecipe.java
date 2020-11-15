package servlet;

import Dao.RecipesDao;
import Model.Recipes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateRecipe extends HttpServlet {
  protected RecipesDao recipesDao;

  @Override
  public void init() throws ServletException {
    recipesDao = RecipesDao.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
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
        String newImgUrl = req.getParameter("newurl");
        if (null == newImgUrl || newImgUrl.trim().isEmpty()) {
          messages.put("success", "Please enter a valid url");
        } else {
          recipe = recipesDao.updateImageUrl(recipe, newImgUrl);
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
