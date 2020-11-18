package servlet;

import Dao.ReviewsDao;
import Model.Reviews;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * Servlet implementation class DeleteReview
 */
@WebServlet("/deletereview")
public class DeleteReview extends HttpServlet {
	protected ReviewsDao reviewsDao = null;
       
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteReview() {
        super();
        // TODO Auto-generated constructor stub
    	reviewsDao = ReviewsDao.getInstance();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Review");        
        req.getRequestDispatcher("/DeleteReview.jsp").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String, String> message = new HashMap<String, String>();
		req.setAttribute("messages", message);
		Integer reviewId = Integer.parseInt(req.getParameter("reviewid"));
		try {
			reviewsDao.delete(new Reviews(reviewId));
			message.put("success", "Successfully deleted review id: "+reviewId);
        } catch (SQLException e) {
			e.printStackTrace();
			throw new IOException(e);
        }
		req.getRequestDispatcher("DeleteReview.jsp").forward(req, resp);
	}
}
