package Dao;

import Model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class IngredientDao {
	protected ConnectionManager connectionManager;

	private static IngredientDao instance = null;
	protected IngredientDao() {
		connectionManager = new ConnectionManager();
	}
	public static IngredientDao getInstance() {
		if(instance == null) {
			instance = new IngredientDao();
		}
		return instance;
	}

	public Ingredient create(Ingredient ingredient) throws SQLException {
		String insertIngredient =
			"INSERT INTO Ingredient(Name,AllergyTypesId) " +
			"VALUES(?,?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			insertStmt = connection.prepareStatement(insertIngredient,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, ingredient.getName());
			insertStmt.setInt(2, ingredient.getAllergyTypesId());
			insertStmt.executeUpdate();
			
			// Retrieve the auto-generated key and set it, so it can be used by the caller.
			resultKey = insertStmt.getGeneratedKeys();
			int ingredientId = -1;
			if(resultKey.next()) {
				ingredientId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			ingredient.setIngredientId(ingredientId);
			return ingredient;
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
	 * Update the Name of the Ingredient instance.
	 * This runs a UPDATE statement.
	 */
	public Ingredient updateName(Ingredient ingredient, String newName) throws SQLException {
		String updateIngredient = "UPDATE Ingredient SET Name? WHERE IngredientId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateIngredient);
			updateStmt.setString(1, newName);
			updateStmt.setInt(2, ingredient.getIngredientId());
			updateStmt.executeUpdate();

			// Update the ingredient param before returning to the caller.
			ingredient.setName(newName);
			return ingredient;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection != null) {
				connection.close();
			}
			if(updateStmt != null) {
				updateStmt.close();
			}
		}
	}

	/**
	 * Delete the Ingredient instance.
	 * This runs a DELETE statement.
	 */
	public Ingredient delete(Ingredient ingredient) throws SQLException {
		String deleteIngredient = "DELETE FROM Ingredient WHERE ingredientId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteIngredient);
			deleteStmt.setInt(1, ingredient.getIngredientId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the Ingredient instance.
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
	 * Get the Ingredient record by fetching it from your MySQL instance.
	 * This runs a SELECT statement and returns a single Ingredient instance.
	 * Note that we use BlogPostsDao and BlogUsersDao to retrieve the referenced
	 * BlogPosts and BlogUsers instances.
	 * One alternative (possibly more efficient) is using a single SELECT statement
	 * to join the Ingredient, BlogPosts, BlogUsers tables and then build each object.
	 */
	public Ingredient getIngredientById(int ingredientId) throws SQLException {
		String selectIngredient =
			"SELECT IngredientId,Name,AllergyTypesId " +
			"FROM Ingredient " +
			"WHERE IngredientId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIngredient);
			selectStmt.setInt(1, ingredientId);
			results = selectStmt.executeQuery();
			AllergyTypesDao allergyTypesDao = AllergyTypesDao.getInstance();
			if(results.next()) {
				int resultIngredientId = results.getInt("IngredientId");
				String Name = results.getString("Name");
				int allergyTypesId = results.getInt("AllergyTypesId");
				
				AllergyTypes allergyTypes = allergyTypesDao.getAllergyTypesById(allergyTypesId);
				Ingredient ingredient = new Ingredient(resultIngredientId, Name, allergyTypes);
				return ingredient;
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
	 * Get the all the Ingredient for a given allergy type.
	 */
	public List<Ingredient> getIngredientForAllergyType (AllergyTypes allergyTypes) throws SQLException {
		List<Ingredient> ingredients = new ArrayList<Ingredient>();
		String selectIngredient =
			"SELECT IngredientId,Name,AllergyTypesId " +
			"FROM Ingredient " +
			"WHERE AllergyTypesId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectIngredient);
			selectStmt.setInt(1, allergyTypes.getAllergyTypesId();
			results = selectStmt.executeQuery();
			AllergyTypesDao allergyTypesDao = AllergyTypesDao.getInstance();
			while(results.next()) {
				int ingredientId = results.getInt("IngredientId");
				String Name = results.getString("Name");
				int resultAllergyTypesId = results.getInt("AllergyTypesId");
				
				AllergyTypes resultAllergyTypes = allergyTypesDao.getAllergyTypesById(resultAllergyTypesId);
				Ingredient ingredient = new Ingredient(ingredientId, Name, resultAllergyTypes);
				ingredients.add(ingredient);
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
		return ingredients;
	
}
