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


@WebServlet("/favoritecreate")
public class CreateFavorites extends HttpServlet {

    protected FavoritesDao favoritesDao;

    @Override
    public void init() throws ServletException {
    	favoritesDao = FavoritesDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/CreateFavorites.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        String favoritesIdAsString = req.getParameter("favoriteId");
        if (favoritesIdAsString == null || favoritesIdAsString.trim().isEmpty()) {
            messages.put("success", "Invalid favoriteId");
        } else {
            int favoriteId = Integer.parseInt(favoritesIdAsString);
        	int recipeID = Integer.parseInt(req.getParameter("recipeID"));
            int userId = Integer.parseInt(req.getParameter("userId"));
            RecipesDao recipesDao = RecipesDao.getInstance();
            UsersDao usersDao = UsersDao.getInstance();
	        try {
	        	// Exercise: parse the input for StatusLevel.
				Recipes recipe = recipesDao.getRecipeById(recipeID);
				Users users = usersDao.getUserById(userId);
	        	Favorites favorites = new Favorites(favoriteId, recipe, users);
	        	favorites = favoritesDao.create(favorites);
	        	messages.put("success", "Successfully created " + favorites);
	        } catch (SQLException e) {
				e.printStackTrace();
				throw new IOException(e);
	        }
        }
        
        req.getRequestDispatcher("/CreateFavorites.jsp").forward(req, resp);
    }
}
