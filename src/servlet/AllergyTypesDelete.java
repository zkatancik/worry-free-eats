package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Dao.*;
import Model.*;

@WebServlet("/allergytypesdelete")
public class AllergyTypesDelete extends HttpServlet {
	protected AllergyTypesDao allergyTypesDao;
	
	@Override
	public void init() throws ServletException {
		allergyTypesDao = AllergyTypesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete AllergyTypes");        
        req.getRequestDispatcher("/AllergyTypesDelete.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String stringId = req.getParameter("allergytypesid");
        if (stringId == null || stringId.trim().isEmpty()) {
            messages.put("title", "Invalid AllergyTypesId");
            messages.put("disableSubmit", "true");
        } else {
        	// Delete the BlogUser.
        	AllergyTypes allergyTypes = new AllergyTypes(Integer.parseInt(stringId));
	        try {
	        	allergyTypes = allergyTypesDao.delete(allergyTypes);
	        	// Update the message.
		        if (allergyTypes == null) {
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
        
        req.getRequestDispatcher("/AllergyTypesDelete.jsp").forward(req, resp);
    }
}
