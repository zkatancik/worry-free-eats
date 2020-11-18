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

@WebServlet("/deletefavorite")
public class DeleteFavorites extends HttpServlet {
	protected FavoritesDao favoritesDao;

  @Override
  public void init() throws ServletException {
	  favoritesDao = FavoritesDao.getInstance();
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

    int favoriteId = -1;
    try {
    	favoriteId = Integer.parseInt(req.getParameter("favoriteId"));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    try {
      if (favoritesDao.delete(favoritesDao.getFavoritesById(favoriteId)) == null) {
        messages.put("title", "Successfully deleted " + Integer.toString(favoriteId));
        messages.put("disableSubmit", "true");
      } else {
        messages.put("title", "Failed to delete " + Integer.toString(favoriteId));
        messages.put("disableSubmit", "false");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }


    req.getRequestDispatcher("/DeleteFavorites.jsp").forward(req, resp);
  }
}

