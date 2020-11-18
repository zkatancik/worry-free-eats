package eat.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eat.dal.ReviewsDao;
import eat.model.Reviews;

/**
 * Servlet implementation class CreateReview
 */
@WebServlet("/createreview")
public class CreateReview extends HttpServlet {
	protected ReviewsDao reviewsDao;

	@Override
	public void init() throws ServletException {
		reviewsDao = ReviewsDao.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.   
        req.getRequestDispatcher("/CreateReview.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

    	String reviewText = req.getParameter("reviewtext");
    	Integer rating = Integer.parseInt(req.getParameter("rating"));
    	Integer userId = Integer.parseInt(req.getParameter("userid"));
    	Integer recipeId = Integer.parseInt(req.getParameter("recipeid"));
        try {
        	// Exercise: parse the input for StatusLevel.
        	Reviews review = new Reviews(reviewText,recipeId,rating,userId);
        	review = reviewsDao.create(review);
        	messages.put("success", "Successfully created review for recipe: " + recipeId);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        
        req.getRequestDispatcher("/CreateReview.jsp").forward(req, resp);
	}

}
