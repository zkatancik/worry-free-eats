package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

import Dao.UserAllergiesDao;
import Model.UserAllergies;
import Model.Users;

@WebServlet("/createUserAllergies")

public class CreateUserAllergies extends HttpServlet {
	protected UserAllergiesDao userAllergiesDao;
	
	@Override
	public void init() throws ServletException {
		userAllergiesDao = userAllergiesDao.getInstance();
		
	}
	
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
  
        req.getRequestDispatcher("/createUserAllergies.jsp").forward(req, resp);
	}
	
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate.
        String idString = req.getParameter("id");
        if (idString == null || idString.trim().isEmpty()) {
            messages.put("success", "Invalid AllergyId");
        } else {
        	
        	Users user = req.getParameter("user");
        	String allergyIdStr = req.getParameter("allergiesTypesId");
       
       
        	int id = Integer.parseInt(idString);
        	int allergyId = Integer.parseInt(allergyIdStr);
        	
        	try {
        	   user = req.getParameter("user");
        	} catch (ParseException e) {
        		e.printStackTrace();
				throw new IOException(e);
        	}
	        try {
	        	UserAllergies userAllergies = new UserAllergies(id, user, allergyId);
	        	userAllergies = userAllergiesDao.create(userAllergies);
	        	messages.put("success", "Successfully created " + id);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CreateUserAllergies.jsp").forward(req, resp);
    }
}

	

