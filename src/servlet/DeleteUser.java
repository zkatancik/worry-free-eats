package servlet;


import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.UsersDao;
import Model.Reviews;
import Model.Users;

public class DeleteUser {

	@WebServlet("/deleteuser")
	public class UserDelete extends HttpServlet {
		
		protected UsersDao usersDao;
		
		@Override
		public void init() throws ServletException {
			usersDao = usersDao.getInstance();
		}
		
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        messages.put("Id", "Delete User");        
	        req.getRequestDispatcher("/DeleteUser.jsp").forward(req, resp);
		}
		
		@Override
	    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	      
	        String userIdString = req.getParameter("userId");
	        int userId = -1;
	        
	        try {
	          userId = Integer.parseInt(req.getParameter("userId"));
	        } catch (NumberFormatException e) {
	          e.printStackTrace();
	          throw new IOException(e);
	        }

	        try {
	          if (usersDao.delete(usersDao.getUserById(userId)) == null) {
	            messages.put("title", "Successfully deleted " + userId);
	            messages.put("disableSubmit", "true");
	          } else {
	            messages.put("title", "Failed to delete " + userId);
	            messages.put("disableSubmit", "false");
	          }
	        } catch (SQLException e) {
	          e.printStackTrace();
	          throw new IOException(e);
	        }


	        req.getRequestDispatcher("/DeleteUser.jsp").forward(req, resp);
	      }
	 }
}
