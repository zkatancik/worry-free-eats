package Dao;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class RecipeIngredientsDao {
	protected ConnectionManager connectionManager;

	private static RecipeIngredientsDao instance = null;
	protected RecipeIngredientsDao() {
		connectionManager = new ConnectionManager();
	}
	public static RecipeIngredientsDao getInstance() {
		if(instance == null) {
			instance = new RecipeIngredientsDao();
		}
		return instance;
	}

	public RecipeIngredients create(RecipeIngredients recipeIngredient) throws SQLException {
		String insertRecipeIngredient =
			"INSERT INTO RecipeIngredients(RecipeId,IngredientId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertRecipeIngredient,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setInt(1, recipeIngredient.getRecipe().getRecipeId());
			insertStmt.setInt(2, recipeIngredient.getIngredient().getIngredientId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int recipeIngredientId = -1;
			if(resultKey.next()) {
				recipeIngredientId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			recipeIngredient.setRecipeIngredientsId(recipeIngredientId);
			return recipeIngredient;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(insertStmt != null) {
				insertStmt.close();
			}
			if(resultKey != null) {
				resultKey.close();
			}
		}
    }

	/**
	 * Delete the RecipeIngredients instance.
	 * This runs a DELETE statement.
	 */
	public RecipeIngredients delete(RecipeIngredients recipeIngredient) throws SQLException {
		String deleteRecipeIngredients = "DELETE FROM RecipeIngredients WHERE recipeIngredientId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteRecipeIngredients);
			deleteStmt.setInt(1, recipeIngredient.getRecipeIngredientsId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the RecipeIngredients instance.
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(deleteStmt != null) {
				deleteStmt.close();
			}
		}
	}

	/**
	 * Get the RecipeIngredients record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single RecipeIngredients instance.
	 * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
	 * BlogPosts and BlogUsers instances.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the RecipeIngredients, BlogPosts, BlogUsers tables and then build each object.
	 */
	public RecipeIngredients getRecipeIngredientsById(int recipeIngredientId) throws SQLException {
		String selectRecipeIngredients =
			"SELECT RecipeIngredientsId,RecipeId,IngredientId " +
			"FROM RecipeIngredients " +
			"WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipeIngredients);
			selectStmt.setInt(1, recipeIngredientId);
			results = selectStmt.executeQuery();
            RecipesDao recipesDao = RecipesDao.getInstance();
            IngredientDao ingredientDao = IngredientDao.getInstance();
			if(results.next()) {
				int resultRecipeIngredientsId = results.getInt("RecipeIngredientsId");
				int recipeId = results.getInt("RecipeId");
				int ingredientId = results.getInt("IngredientId");
				
                Recipes recipe = recipesDao.getRecipeById(recipeId);
                Ingredient ingredient = ingredientDao.getIngredientById(ingredientId);
				RecipeIngredients recipeIngredient = new RecipeIngredients(resultRecipeIngredientsId, recipe, ingredient);
				return recipeIngredient;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
		return null;
	}

	/**
	 * Get the all the RecipeIngredients for a given allergy type.
	 */
	public List<RecipeIngredients> getRecipeIngredientsForRecipe (Recipes recipe) throws SQLException {
		List<RecipeIngredients> recipeIngredients = new ArrayList<RecipeIngredients>();
		String selectRecipeIngredients =
			"SELECT RecipeIngredientsId,RecipeId,IngredientId " +
			"FROM RecipeIngredients " +
			"WHERE RecipeId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectRecipeIngredients);
			selectStmt.setInt(1, recipe.getRecipeId());
			results = selectStmt.executeQuery();
            RecipesDao recipesDao = RecipesDao.getInstance();
            IngredientDao ingredientDao = IngredientDao.getInstance();
			while(results.next()) {
				int recipeIngredientsId = results.getInt("RecipeIngredientsId");
				int resultRecipeId = results.getInt("RecipeId");
				int ingredientId = results.getInt("IngredientId");
				
                Recipes resultRecipe = recipesDao.getRecipeById(resultRecipeId);
                Ingredient ingredient = ingredientDao.getIngredientById(ingredientId);
				RecipeIngredients recipeIngredient = new RecipeIngredients(recipeIngredientsId, resultRecipe, ingredient);
				recipeIngredients.add(recipeIngredient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(selectStmt != null) {
				selectStmt.close();
			}
			if(results != null) {
				results.close();
			}
		}
        return recipeIngredients;
    }
	
}
