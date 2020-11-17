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
import worryFreeEats.model.AllergyTypes.Allergy;

@WebServlet("/allergytypesupdate")
public class AllergyTypesUpdate extends HttpServlet {
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

        // Retrieve user and validate.
        String stringId = req.getParameter("allergytypesid");
        if (stringId == null || stringId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid AllergyTypesId.");
        } else {
        	try {
        		AllergyTypes allergyType = allergyTypesDao.getAllergyByAllergyTypesId(Integer.parseInt(stringId));
                if (allergyType == null) {
        			messages.put("success", "AllergyTypesId does not exist.");
        		}
        		req.setAttribute("allergyType", allergyType);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AllergyTypesUpdate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String stringId = req.getParameter("allergytypesid");
        if (stringId == null || stringId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid AllergyTypesId.");
        } else {
        	try {
        		AllergyTypes allergyType = allergyTypesDao.getAllergyByAllergyTypesId(Integer.parseInt(stringId));
        		if(allergyType == null) {
        			messages.put("success", "AllergyTypesId does not exist. No update to perform.");
        		} else {
        			String newAllergy = req.getParameter("newallergy");
        			if (newAllergy == null || newAllergy.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid Allergy.");
        	        } else {
        	        	allergyType = allergyTypesDao.updateAllergy(allergyType, Allergy.valueOf(newAllergy));
        	        	messages.put("success", "Successfully updated AllergyTypesId" + stringId);
        	        }
        		}
        		req.setAttribute("allergtypes", allergyType);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/AllergyTypesUpdate.jsp").forward(req, resp);
    }
}
