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

/**
 * Servlet implementation class UpdateReview
 */
@WebServlet("/updatefavorites")
public class UpdateFavorites extends HttpServlet {
       
protected FavoritesDao favoritesDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFavorites() {
        super();
        favoritesDao = FavoritesDao.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String> message = new HashMap<String, String>();
		req.setAttribute("message", message);
		int favoriteId = Integer.parseInt(req.getParameter("favoriteId"));
		try {
			Favorites favorites = favoritesDao.getFavoritesById(favoriteId);
			req.setAttribute("favorites", favorites);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		
		req.getRequestDispatcher("UpdateFavorites.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String> message = new HashMap<String, String>();
		req.setAttribute("message", message);
		int favoriteId=Integer.parseInt(req.getParameter("favoriteId"));
		int recipeID=Integer.parseInt(req.getParameter("recipeID"));
		int userId=Integer.parseInt(req.getParameter("userId"));
		RecipesDao recipesDao = RecipesDao.getInstance();
        UsersDao usersDao = UsersDao.getInstance();
        try {
        	Recipes recipe = recipesDao.getRecipeById(recipeID);
        	Users users = usersDao.getUserById(userId);
        	Favorites favorites = new Favorites(favoriteId, recipe, users);
        	favorites = favoritesDao.updateFavorites(favorites, recipeID);
			req.setAttribute("favorites",favorites);
			message.put("success","Successfully updated recipeId for likes: " + favoriteId);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.getRequestDispatcher("/UpdateFavorites.jsp").forward(req, resp);
	}

}

