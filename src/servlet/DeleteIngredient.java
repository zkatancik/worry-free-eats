package servlet;

import Dao.*;
import Model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteingredient")
public class DeleteIngredient extends HttpServlet {
  protected IngredientDao ingredientDao;

  @Override
  public void init() throws ServletException {
    ingredientDao = IngredientDao.getInstance();
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

    String ingredientName = req.getParameter("ingredientname");
    int ingredientId = -1;
    try {
      ingredientId = Integer.parseInt(req.getParameter("ingredientid"));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    try {
      if (ingredientDao.delete(ingredientDao.getIngredientById(ingredientId)) == null) {
        messages.put("title", "Successfully deleted " + ingredientName);
        messages.put("disableSubmit", "true");
      } else {
        messages.put("title", "Failed to delete " + ingredientName);
        messages.put("disableSubmit", "false");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }


    req.getRequestDispatcher("/DeleteIngredient.jsp").forward(req, resp);
  }
}
