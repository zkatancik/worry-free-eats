package servlet;

import Dao.*;
import Model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/findreviews")
public class FindReviews extends HttpServlet {
	
	protected ReviewsDao reviewsDao;

	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
		
        List<Reviews> reviews = new ArrayList<Reviews>();
        
        String userId = req.getParameter("userid");
        String recipeId = req.getParameter("recipeid");
        
        try {
	        if (userId != null && !userId.trim().isEmpty()) {
	        	reviews = reviewsDao.getReviewsByUserId(Integer.parseInt(userId));
	        	messages.put("title", "Reviews for UserId " + userId);
	        } else if (recipeId != null && !recipeId.trim().isEmpty()) {
	        	reviews = reviewsDao.getReviewsByRecipeId(Integer.parseInt(recipeId));
	        	messages.put("title", "Reviews for RecipeId " + recipeId);
	        } else {
	        	messages.put("title", "Invalid PostId and UserName.");
	        }
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.setAttribute("reviews", reviews);
        req.getRequestDispatcher("/FindReviews.jsp").forward(req, resp);
	}
}
