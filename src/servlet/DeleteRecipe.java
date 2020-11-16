package servlet;

import Dao.RecipesDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleterecipe")
public class DeleteRecipe extends HttpServlet {
  protected RecipesDao recipesDao;

  @Override
  public void init() throws ServletException {
    recipesDao = RecipesDao.getInstance();
  }

  @Override
  public void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  public void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    String recipeName = req.getParameter("recipename");
    int recipeId = -1;
    try {
      recipeId = Integer.parseInt(req.getParameter("recipeid"));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    try {
      if (recipesDao.delete(recipeId) == null) {
        messages.put("title", "Successfully deleted \"" + recipeId + ", " + recipeName + "\"");
      } else {
        messages.put("title", "Failed to delete \"" + recipeId + ", " + recipeName + "\"");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      messages.put("title", "No records available to delete for \"" + recipeId + ", " + recipeName + "\"");
    }


    req.getRequestDispatcher("/DeleteRecipe.jsp").forward(req, resp);
  }
}
