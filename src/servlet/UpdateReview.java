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

import Dao.ReviewsDao;
import Model.Reviews;

/**
 * Servlet implementation class UpdateReview
 */
@WebServlet("/updatereview")
public class UpdateReview extends HttpServlet {
       
	protected ReviewsDao reviewsDao;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateReview() {
        super();
        reviewsDao = ReviewsDao.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String> message = new HashMap<String, String>();
		req.setAttribute("message", message);
		String reviewId = req.getParameter("reviewid");
		try {
			Reviews review = reviewsDao.getReviewByReviewId(Integer.parseInt(reviewId));
			req.setAttribute("review", review);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		
		req.getRequestDispatcher("UpdateReview.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String> message = new HashMap<String, String>();
		req.setAttribute("message", message);
		Integer reviewId=Integer.parseInt(req.getParameter("reviewid"));
		String reviewText=req.getParameter("reviewtext");
		Integer recipeId=Integer.parseInt(req.getParameter("recipeid"));
		Integer rating=Integer.parseInt(req.getParameter("rating"));
		Integer userId=Integer.parseInt(req.getParameter("userid"));
		Reviews review = new Reviews(reviewId, reviewText, recipeId, rating, userId);
		try {
			review = reviewsDao.updateReviewById(review);
			req.setAttribute("review",review);
			message.put("success","Successfully updated review for recipe: "+recipeId);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
        req.getRequestDispatcher("/UpdateReview.jsp").forward(req, resp);
	}

}
