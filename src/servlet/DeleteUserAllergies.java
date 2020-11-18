package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import Dao.UserAllergiesDao;
import Model.UserAllergies;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deleteUserAllergies")
public class DeleteUserAllergies extends HttpServlet {
	

		protected UserAllergiesDao userAllergiesDao;
		
		@Override
		public void init() throws ServletException {
			userAllergiesDao = userAllergiesDao.getInstance();
		}
		
		@Override
		public void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			// Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);
	        // Provide a title and render the JSP.
	        messages.put("title", "Delete UserAllergies");        
	        req.getRequestDispatcher("/DeleteUserAllergies.jsp").forward(req, resp);
		}
		
		@Override
	    public void doPost(HttpServletRequest req, HttpServletResponse resp)
	    		throws ServletException, IOException {
	        // Map for storing messages.
	        Map<String, String> messages = new HashMap<String, String>();
	        req.setAttribute("messages", messages);

	        String stringId = req.getParameter("id");
	        if (stringId == null || stringId.trim().isEmpty()) {
	            messages.put("title", "Invalid UserAllergyId");
	            messages.put("disableSubmit", "true");
	        } else {
	        	// Delete the UserAllergies
	        	UserAllergies userAllergies = new UserAllergies(Integer.parseInt(stringId));
		        try {
		        	userAllergies = userAllergiesDao.delete(userAllergies);
		        	
			        if (userAllergies == null) {
			            messages.put("title", "Successfully deleted AllegyType " + stringId);
			            messages.put("disableSubmit", "true");
			        } else {
			        	messages.put("title", "Failed to delete " + stringId);
			        	messages.put("disableSubmit", "false");
			        }
		        } catch (SQLException e) {
					e.printStackTrace();
					throw new IOException(e);
		        }
	        }
	        
	        req.getRequestDispatcher("/DeleteUserAllergies.jsp").forward(req, resp);
	    }
	}



