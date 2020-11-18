package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Dao.UsersDao;
import Model.Recipes;
import Model.Users;


@WebServlet("/updateuser")
public class UpdateUser extends HttpServlet {
	protected UsersDao usersDao;
	
	 @Override
	  public void init() throws ServletException {
		 usersDao = usersDao.getInstance();
	  
	 
	 
	  @Override
	  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<>();
	    req.setAttribute("messages", messages);

	    try {
	      int userId = Integer.parseInt(req.getParameter("userId"));
	      Users user = usersDao.getUserById(userId);
	      if (null == user) {
	        messages.put("success", "User Id does not found.");
	      }
	    } catch (NumberFormatException | SQLException e) {
	      e.printStackTrace();
	      throw new IOException(e);
	    }

	    req.getRequestDispatcher("UpdateUser.jsp").forward(req, resp);
	  }

	  @Override
	  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
	      throws ServletException, IOException {
	    Map<String, String> messages = new HashMap<>();
	    req.setAttribute("messages", messages);

	    try {
	      int userId = Integer.parseInt(req.getParameter("userId"));
	      Users user = usersDao.getUserById(userId);
	      if (null == user) {
	        messages.put("success", "Please enter a valid userId");
	      } else {
	          String newUserName = req.getParameter("userName");
              user = usersDao.updateUserUsername(user, newUserName);
	            messages.put("success", "Successfully updated userName for userId " + userId);
	          }
	        }
	       catch (NumberFormatException | SQLException e) {
	        e.printStackTrace();
	        throw new IOException(e);
	      }

	      req.getRequestDispatcher("UpdateUser.jsp").forward(req, resp);
	    }
}


	     
