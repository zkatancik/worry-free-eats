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


@WebServlet("/recipeingredientscreate")
public class RecipeIngredientsCreate extends HttpServlet {
	
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
        //Just render the JSP.   
        req.getRequestDispatcher("/RecipeIngredientsCreate.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String recipeIngredientsIdAsString = req.getParameter("recipeIngredientsId");
        if (recipeIngredientsIdAsString == null || recipeIngredientsIdAsString.trim().isEmpty()) {
            messages.put("success", "Invalid recipeIngredientsId");
        } else {
            // Create the BlogUser.
            int recipeIngredientsId = Integer.parseInt(recipeIngredientsIdAsString);
        	String recipeIdAsString = req.getParameter("recipe");
            String ingredientIdAsString = req.getParameter("ingredient");
            RecipesDao recipesDao = RecipesDao.getInstance();
            IngredientDao ingredientDao = IngredientDao.getInstance();
	        try {
	        	// Exercise: parse the input for StatusLevel.
						Recipes recipe = recipesDao.getRecipeById(Integer.parseInt(recipeIdAsString));
						Ingredient ingredient = ingredientDao.getIngredientById(Integer.parseInt(ingredientIdAsString));
	        	RecipeIngredients recipeIngredients = new RecipeIngredients(recipeIngredientsId, recipe, ingredient);
	        	recipeIngredients = recipeIngredientsDao.create(recipeIngredients);
	        	messages.put("success", "Successfully created " + recipeIngredientsIdAsString);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/RecipeIngredientsCreate.jsp").forward(req, resp);
    }
}
