package servlet;

import Dao.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleterecipeingredients")
public class DeleteRecipeIngredients extends HttpServlet {
  protected RecipeIngredientsDao recipeIngredientsDao;

  @Override
  public void init() throws ServletException {
    recipeIngredientsDao = RecipeIngredientsDao.getInstance();
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

    int recipeIngredientsId = -1;
    try {
      recipeIngredientsId = Integer.parseInt(req.getParameter("recipeingredientsid"));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    try {
      if (recipeIngredientsDao.delete(recipeIngredientsDao.getRecipeIngredientsById(recipeIngredientsId)) == null) {
        messages.put("title", "Successfully deleted " + Integer.toString(recipeIngredientsId));
        messages.put("disableSubmit", "true");
      } else {
        messages.put("title", "Failed to delete " + Integer.toString(recipeIngredientsId));
        messages.put("disableSubmit", "false");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }


    req.getRequestDispatcher("/DeleteRecipeIngredients.jsp").forward(req, resp);
  }
}
