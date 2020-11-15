package servlet;

import Dao.RecipesDao;
import Model.Recipes;
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

@WebServlet("/findrecipes")
public class FindRecipes extends HttpServlet {
  protected RecipesDao recipesDao;

  @Override
  public void init() throws ServletException {
    recipesDao = RecipesDao.getInstance();
  }

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    doPost(req, resp);
  }

  @Override
  protected void doPost(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    Map<String, String> messages = new HashMap<>();
    req.setAttribute("messages", messages);
    List<Recipes> recipesList = new ArrayList<>();
    int pageSize = 10;

    int totalRecipesCnt = 0;
    int recordOffset = 0;
    int pre = 0;
    int next = 0;
    int last = 0;

    String keyword = req.getParameter("keyword");
    String pageStr = req.getParameter("pageindex");

    if (null != pageStr && !pageStr.trim().isEmpty()) {
      try {
        recordOffset = Integer.parseInt(pageStr);
      } catch (NumberFormatException e) {
        e.printStackTrace();
      }
    }

    if (null == keyword || keyword.trim().isEmpty()) {
      messages.put("success", "Please input a valid keyword");
    } else {
      try {
        totalRecipesCnt = recipesDao.getTotalRecipesCnt(keyword);
        if(0 == totalRecipesCnt % pageSize) {
          last = totalRecipesCnt - pageSize;
        } else {
          last = totalRecipesCnt - totalRecipesCnt % pageSize;
        }
        pre = recordOffset - pageSize;
        pre = Math.max(pre, 0);
        next = recordOffset + pageSize;
        next = Math.min(next, last);
        recipesList = recipesDao.getRecipesByKeyword(keyword, recordOffset, pageSize);
      } catch (SQLException e) {
        e.printStackTrace();
      }
      messages.put("success", "Displaying " + totalRecipesCnt + " result(s) for keyword \"" + keyword + "\"");
    }
    req.setAttribute("recipesList", recipesList);
    req.setAttribute("pageIndex", recordOffset / pageSize + 1);
    req.setAttribute("pageCnt", (int) Math.ceil((double)totalRecipesCnt/pageSize));
    req.setAttribute("pre", pre);
    req.setAttribute("next", next);
    req.setAttribute("last", last);
    req.setAttribute("total", totalRecipesCnt);
    if (recipesList.size() > 0) {
      req.setAttribute("gotAny", true);
    } else {
      req.setAttribute("gotAny", false);
    }

    req.getRequestDispatcher("FindRecipes.jsp").forward(req, resp);
  }
}
