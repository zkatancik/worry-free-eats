package servlet;

import Dao.NutritionsDao;
import Model.Nutritions;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Nutrition")
public class FindNutrition extends HttpServlet {
    protected NutritionsDao nutritionsDao;

    @Override
    public void init() {
      nutritionsDao = NutritionsDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        Integer id = Integer.parseInt(req.getParameter("recipeId"));
        try {
            Nutritions nutrition = nutritionsDao.getNutritionByRecipeId(id);
            if (nutrition == null) {
                messages.put("success", "Please enter a valid id");
            } else {
                messages.put("success", "Display result");
            }
            req.setAttribute("nutrition", nutrition);
            req.getRequestDispatcher("/FindRecipeNutrition.jsp").forward(req, resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) {
        doGet(req, resp);
    }
}
