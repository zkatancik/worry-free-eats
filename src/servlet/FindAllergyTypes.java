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

import worryFreeEats.dal.*;
import worryFreeEats.model.*;

@WebServlet("/findallergytypes")
public class FindAllergyTypes  extends HttpServlet {
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

        AllergyTypes allergyType = null;
        
        String stringId = req.getParameter("allergytypesid");
        if (stringId == null || stringId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid AllergyTypesId.");
        } else {
        	// Retrieve BlogUsers, and store as a message.
        	try {
        		allergyType = allergyTypesDao.getAllergyByAllergyTypesId(Integer.parseInt(stringId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for AllergyTypesId " + stringId);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindUsers.jsp.
        	messages.put("previousAllergyTypesId", stringId);
        }
        req.setAttribute("allergytype", allergyType);
        
        req.getRequestDispatcher("/FindAllergyTypes.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        AllergyTypes allergyType = null;
        
        String stringId = req.getParameter("allergytypesid");
        if (stringId == null || stringId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid AllergyTypesId.");
        } else {
        	try {
        		allergyType = allergyTypesDao.getAllergyByAllergyTypesId(Integer.parseInt(stringId));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for AllergyTypesId " + stringId);
        }
        req.setAttribute("allergytype", allergyType);
        
        req.getRequestDispatcher("/FindAllergyTypes.jsp").forward(req, resp);
    }
}
