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

import worryFreeEats.dal.*;
import worryFreeEats.model.AllergyTypes;
import worryFreeEats.model.AllergyTypes.Allergy;

@WebServlet("/allergytypescreate")
public class AllergyTypesCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/AllergyTypesCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String stringId = req.getParameter("allergytypesid");
        if (stringId == null || stringId.trim().isEmpty()) {
            messages.put("success", "Invalid allergyTypesId");
        } else {
	        String stringAllergy = req.getParameter("allergy");
	        int allergyTypesId = 0;
	        Allergy allergy = null;
	        allergyTypesId = Integer.parseInt(stringId);
	        allergy = Allergy.valueOf(stringAllergy);
	        try {
	        	AllergyTypes allergyType = new AllergyTypes(allergyTypesId, allergy);
	        	allergyType = allergyTypesDao.create(allergyType);
	        	messages.put("success", "Successfully created " + allergyType);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
  
        req.getRequestDispatcher("/AllergyTypesCreate.jsp").forward(req, resp);
    }
}
