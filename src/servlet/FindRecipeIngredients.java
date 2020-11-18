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

@WebServlet("/findrecipeingredients")
public class FindRecipeIngredients extends HttpServlet {
	
	protected RecipeIngredientsDao recipeIngredientsDao;
	
	@Override
	public void init() throws ServletException {
		recipeIngredientsDao = RecipeIngredientsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<RecipeIngredients> recipeIngredients = new ArrayList<RecipeIngredients>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String recipeIdAsString = req.getParameter("recipeid");
        if (recipeIdAsString == null || recipeIdAsString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Ingredient, and store as a message.
        	try {
                RecipesDao recipesDao;
                recipesDao = RecipesDao.getInstance();
                Recipes recipe = recipesDao.getRecipeById(Integer.parseInt(recipeIdAsString));
            	recipeIngredients = recipeIngredientsDao.getRecipeIngredientsForRecipe(recipe);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + recipeIdAsString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindRecipeIngredients.jsp.
        	messages.put("previousFirstName", recipeIdAsString);
        }
        req.setAttribute("recipeIngredients", recipeIngredients);
        
        req.getRequestDispatcher("/FindRecipeIngredients.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<RecipeIngredients> recipeIngredients = new ArrayList<RecipeIngredients>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindRecipeIngredients.jsp).
        String recipeIdAsString = req.getParameter("recipeid");
        if (recipeIdAsString == null || recipeIdAsString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Ingredient, and store as a message.
        	try {
            	RecipesDao recipesDao;
                recipesDao = RecipesDao.getInstance();
                Recipes recipe = recipesDao.getRecipeById(Integer.parseInt(recipeIdAsString));
            	recipeIngredients = recipeIngredientsDao.getRecipeIngredientsForRecipe(recipe);
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + recipeIdAsString);
        }
        req.setAttribute("recipeIngredients", recipeIngredients);
        
        req.getRequestDispatcher("/FindRecipeIngredients.jsp").forward(req, resp);
    }
}
