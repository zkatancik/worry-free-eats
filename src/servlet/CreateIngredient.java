package servlet;

import Dao.*;
import Model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ingredientcreate")
public class IngredientCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateIngredient.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String ingredientIdAsString = req.getParameter("ingredientId");
        if (ingredientIdAsString == null || ingredientIdAsString.trim().isEmpty()) {
            messages.put("success", "Invalid ingredientId");
        } else {
            // Create the BlogUser.
            int ingredientId = Integer.parseInt(ingredientIdAsString);
            String name = null;
            name = req.getParameter("name");
            AllergyTypes allergyTypes = null;
        	allergyTypes = req.getParameter("allergyTypes");
	        try {
	        	// Exercise: parse the input for StatusLevel.
	        	Ingredient ingredient = new Ingredient(ingredientId, name, allergyTypes);
	        	ingredient = ingredientDao.create(ingredient);
	        	messages.put("success", "Successfully created " + name);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/IngredientCreate.jsp").forward(req, resp);
    }
}
