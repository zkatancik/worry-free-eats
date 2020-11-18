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

@WebServlet("/deletelikes")
public class DeleteLikes extends HttpServlet {
	protected LikesDao likesDao;

  @Override
  public void init() throws ServletException {
	  likesDao = LikesDao.getInstance();
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

    int likeId = -1;
    try {
    	likeId = Integer.parseInt(req.getParameter("likeId"));
    } catch (NumberFormatException e) {
      e.printStackTrace();
      throw new IOException(e);
    }

    try {
      if (likesDao.delete(likesDao.getLikesById(likeId)) == null) {
        messages.put("title", "Successfully deleted " + Integer.toString(likeId));
        messages.put("disableSubmit", "true");
      } else {
        messages.put("title", "Failed to delete " + Integer.toString(likeId));
        messages.put("disableSubmit", "false");
      }
    } catch (SQLException e) {
      e.printStackTrace();
      throw new IOException(e);
    }


    req.getRequestDispatcher("/DeleteLikes.jsp").forward(req, resp);
  }
}


