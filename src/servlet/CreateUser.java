package servlet;

import Dao.*;
import Model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

	public class CreateUser extends HttpServlet {
		  protected UsersDao usersDao;
		  @Override
		  public void init() throws ServletException {
			  usersDao = usersDao.getInstance();
		  }

			@Override
			public void doGet(HttpServletRequest req, HttpServletResponse resp)
					throws ServletException, IOException {
		        Map<String, String> messages = new HashMap<String, String>();
		        req.setAttribute("messages", messages);
		  
		        req.getRequestDispatcher("/CreateUser.jsp").forward(req, resp);
			}

			
			@Override
		    public void doPost(HttpServletRequest req, HttpServletResponse resp)
		    		throws ServletException, IOException {
		        // Map for storing messages.
		        Map<String, String> messages = new HashMap<String, String>();
		        req.setAttribute("messages", messages);

		        // Retrieve and validate name.
		        String userName = req.getParameter("username");
		        if (userName == null || userName.trim().isEmpty()) {
		            messages.put("success", "Invalid UserName");
		        } else {
		        	
		        	String userIdString = req.getParameter("userId");
		        	String username = req.getParameter("userName");
		        	String password = req.getParameter("password");
		        	String name = req.getParameter("name");
		        	String email = req.getParameter("email");
		       
		        	Timestamp createdTime = Timestamp.from(Instant.now());  
		        	Timestamp lastModifiedTime = Timestamp.from(Instant.now());  
		        	Timestamp lastLogin = Timestamp.from(Instant.now());  
		        	int userId = Integer.parseInt(userIdString);
		        	try {
		        		name = req.getParameter("name");
			        	Users user = new Users(userId, userName, password, name, email, createdTime, 
			        			lastModifiedTime, lastLogin);
			        	user = usersDao.create(user);
			        	messages.put("success", "Successfully created " + userId);
			        } catch (SQLException e) {
								e.printStackTrace();
								throw new IOException(e);
			        }
		        }
		        
		        req.getRequestDispatcher("/CreateUser.jsp").forward(req, resp);
		    }
}
