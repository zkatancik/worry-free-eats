package servlet;

import Dao.*;
import Model.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/findingredient")
public class FindIngredient extends HttpServlet {
	
	protected IngredientDao ingredientDao;
	
	@Override
	public void init() throws ServletException {
		ingredientDao = IngredientDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String allergyTypeIdAsString = req.getParameter("allergytypeid");
        if (allergyTypeIdAsString == null || allergyTypeIdAsString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Ingredient, and store as a message.
        	try {
                AllergyTypesDao allergyTypesDao;
                allergyTypesDao = AllergyTypesDao.getInstance();
                AllergyTypes allergyType = allergyTypesDao.getAllergyByAllergyTypesId(Integer.parseInt(allergyTypeIdAsString));
            	ingredients = ingredientDao.getIngredientForAllergyType(allergyType);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + allergyTypeIdAsString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindIngredient.jsp.
        	messages.put("previousFirstName", allergyTypeIdAsString);
        }
        req.setAttribute("ingredients", ingredients);
        
        req.getRequestDispatcher("/FindIngredient.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindIngredient.jsp).
        String allergyTypeIdAsString = req.getParameter("allergytypesid");
        if (allergyTypeIdAsString == null || allergyTypeIdAsString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Ingredient, and store as a message.
        	try {
            	AllergyTypesDao allergyTypesDao;
                allergyTypesDao = AllergyTypesDao.getInstance();
                AllergyTypes allergyType = allergyTypesDao.getAllergyByAllergyTypesId(Integer.parseInt(allergyTypeIdAsString));
            	ingredients = ingredientDao.getIngredientForAllergyType(allergyType);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + allergyTypeIdAsString);
        }
        req.setAttribute("ingredients", ingredients);
        
        req.getRequestDispatcher("/FindIngredient.jsp").forward(req, resp);
    }
}
