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
@WebServlet("/updatelikes")
public class UpdateLikes extends HttpServlet {
       
	protected LikesDao likesDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLikes() {
        likesDao = LikesDao.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String> message = new HashMap<String, String>();
		req.setAttribute("message", message);
		int likeId = Integer.parseInt(req.getParameter("likeId"));
		try {
			Likes likes = likesDao.getLikesById(likeId);
			req.setAttribute("likes", likes);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		
		req.getRequestDispatcher("UpdateLikes.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String> message = new HashMap<String, String>();
		req.setAttribute("message", message);
		int likeId=Integer.parseInt(req.getParameter("likeId"));
		int recipeID=Integer.parseInt(req.getParameter("recipeID"));
		int userId=Integer.parseInt(req.getParameter("userId"));
		RecipesDao recipesDao = RecipesDao.getInstance();
        UsersDao usersDao = UsersDao.getInstance();
        try {
        	Recipes recipe = recipesDao.getRecipeById(recipeID);
        	Users users = usersDao.getUserById(userId);
        	Likes likes = new Likes(likeId, recipe, users);
        	likes = likesDao.updateLikes(likes, recipeID);
			req.setAttribute("likes",likes);
			message.put("success","Successfully updated recipeId for likes: " + likeId);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.getRequestDispatcher("/UpdateLikes.jsp").forward(req, resp);
	}

}
