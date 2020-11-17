package servlet;

import Dao.*;
import Model.Ingredient;
import model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ingredientupdate")
public class UpdateIngredient extends HttpServlet {
	
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

        // Retrieve user and validate.
        String ingredientIdAsString = req.getParameter("ingredientid");
        if (ingredientIdAsString == null || ingredientIdAsString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Ingredient.");
        } else {
        	try {
        		Ingredient ingredient = ingredientDao.getIngredientById(Integer.parseInt(ingredientIdAsString));
        		if(ingredient == null) {
        			messages.put("success", "Ingredient does not exist.");
        		}
        		req.setAttribute("ingredient", ingredient);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateIngredient.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve user and validate.
        String ingredientIdAsString = req.getParameter("ingredientid");
        if (ingredientIdAsString == null || ingredientIdAsString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid Ingredient.");
        } else {
        	try {
        		Ingredient ingredient = ingredientDao.getIngredientById(Integer.parseInt(ingredientIdAsString));
        		if(ingredient == null) {
        			messages.put("success", "Ingredient does not exist. No update to perform.");
        		} else {
        			String newName = req.getParameter("name");
        			if (newName == null || newName.trim().isEmpty()) {
        	            messages.put("success", "Please enter a valid name.");
        	        } else {
        	        	ingredient = ingredientDao.updateName(ingredient, newName);
        	        	messages.put("success", "Successfully updated " + ingredientIdAsString);
        	        }
        		}
        		req.setAttribute("ingredient", ingredient);
        	} catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/UpdateIngredient.jsp").forward(req, resp);
    }
}
