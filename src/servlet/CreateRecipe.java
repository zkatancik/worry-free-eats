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

@WebServlet("/createrecipe")
public class CreateRecipe extends HttpServlet {
  protected RecipesDao recipesDao;
  @Override
  public void init() throws ServletException {
    recipesDao = RecipesDao.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);
    //Just render the JSP.
    req.getRequestDispatcher("/CreateRecipe.jsp").forward(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    // Map for storing messages.
    Map<String, String> messages = new HashMap<String, String>();
    req.setAttribute("messages", messages);

    // Retrieve and validate name.
    String recipeName = req.getParameter("recipename");
    if (recipeName == null || recipeName.trim().isEmpty()) {
      messages.put("success", "Invalid RecipeName");
    } else {
      String imageUrl = req.getParameter("imageurl");
      try {

        Recipes recipe = new Recipes(recipeName, imageUrl);
        recipe = recipesDao.create(recipe);
        messages.put("success", "Successfully created " + recipeName);
      } catch (SQLException e) {
        e.printStackTrace();
        throw new IOException(e);
      }
    }

    req.getRequestDispatcher("/CreateRecipe.jsp").forward(req, resp);
  }
}
