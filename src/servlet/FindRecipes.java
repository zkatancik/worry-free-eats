package servlet;

import Dao.RecipesDao;
import Model.Recipes;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findrecipes")
public class FindRecipes extends HttpServlet {
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
    List<Recipes> recipesList = new ArrayList<>();

    String keyword = req.getParameter("keyword");
    if (null == keyword || keyword.trim().isEmpty()) {
      messages.put("success", "Please input a valid keyword");
    } else {
      try {
        recipesList = recipesDao.getRecipesByKeyword(keyword);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      messages.put("success", "Displaying results for keyword \"" + keyword + "\"");
    }
    req.setAttribute("recipesList", recipesList);

    req.getRequestDispatcher("FindRecipes.jsp").forward(req, resp);
  }
}
