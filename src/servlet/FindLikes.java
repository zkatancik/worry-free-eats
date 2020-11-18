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

@WebServlet("/findlikes")
public class FindLikes extends HttpServlet {
	
	protected LikesDao likesDao;
	
	@Override
	public void init() throws ServletException {
		likesDao = LikesDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Likes> likesList = new ArrayList<Likes>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the URL query string.
        String userIdAsString = req.getParameter("userId");
        if (userIdAsString == null || userIdAsString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Ingredient, and store as a message.
        	try {
                UsersDao usersDao;
                usersDao = UsersDao.getInstance();
            	likesList = likesDao.getLikesForUser(Integer.parseInt(userIdAsString));
        	} catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userIdAsString);
        	// Save the previous search term, so it can be used as the default
        	// in the input box when rendering FindRecipeIngredients.jsp.
        	messages.put("previousUserId", userIdAsString);
        }
        req.setAttribute("likes", likesList);
        
        req.getRequestDispatcher("/FindLikes.jsp").forward(req, resp);
	}
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
    		throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Likes> likesList = new ArrayList<Likes>();
        
        // Retrieve and validate name.
        // firstname is retrieved from the form POST submission. By default, it
        // is populated by the URL query string (in FindRecipeIngredients.jsp).
        String userIdAsString = req.getParameter("userId");
        if (userIdAsString == null || userIdAsString.trim().isEmpty()) {
            messages.put("success", "Please enter a valid name.");
        } else {
        	// Retrieve Ingredient, and store as a message.
        	try {
            	likesList = likesDao.getLikesForUser(Integer.parseInt(userIdAsString));
            } catch (SQLException e) {
    			e.printStackTrace();
    			throw new IOException(e);
            }
        	messages.put("success", "Displaying results for " + userIdAsString);
        }
        req.setAttribute("likes", likesList);
        
        req.getRequestDispatcher("/FindLikes.jsp").forward(req, resp);
    }
}

