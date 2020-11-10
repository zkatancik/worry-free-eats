package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import eat.model.Reviews;

public class ReviewsDao {
	private ConnectionManager connectionManager;
	private static ReviewsDao instance = null;
	
	private ReviewsDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static ReviewsDao getInstance() {
		if(instance==null) {
			instance = new ReviewsDao();
		}
		return instance;
	}
	
	public Reviews create(Reviews review) throws SQLException {
		String insertReview = "INSERT INTO Reviews(ReviewText, RecipeId, Rating, UserId) VALUES(?,?,?,?);";
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet results = null;
		try {
			preparedStatement = connection.prepareStatement(insertReview, Statement.RETURN_GENERATED_KEYS);
			preparedStatement.setString(1, review.getReviewText());
			preparedStatement.setInt(2, review.getRecipeId());
			preparedStatement.setInt(3, review.getRating());
			preparedStatement.setInt(4, review.getUserId());
			preparedStatement.executeUpdate();
			results = preparedStatement.getGeneratedKeys();
			if(results.next()) {
				review.setReviewId(results.getInt(1));
			}
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection!=null) {
				connection.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(results!=null) {
				results.close();
			}
		}
	}
	
	public Reviews updateReviewById(Reviews review) throws SQLException {
		String updateReivew = "UPDATE Reviews SET ReviewText = ?, Rating = ? WHERE ReviewId = ?;";
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionManager.getConnection();
			preparedStatement = connection.prepareStatement(updateReivew);
			preparedStatement.setString(1, review.getReviewText());
			preparedStatement.setInt(2, review.getRating());
			preparedStatement.setInt(3, review.getReviewId());
			preparedStatement.executeUpdate();
			return review;
		} catch(SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection!=null) {
				connection.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
		}
	}
	
	public Reviews getReviewByReviewId(Integer reviewId) throws SQLException {
		String selectReview = "SELECT ReviewId, ReviewText, RecipeId, Rating, UserId "
				+ "FROM Reviews WHERE ReviewId = ?;";
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			preparedStatement = connection.prepareStatement(selectReview);
			preparedStatement.setInt(1, reviewId);
			resultSet = preparedStatement.executeQuery();
			if(resultSet.next()) {
				return new Reviews(resultSet.getInt("ReviewId"),
						resultSet.getString("ReviewText"), 
						resultSet.getInt("recipeId"), 
						resultSet.getInt("rating"), 
						resultSet.getInt("userId"));
			}
			return null;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection!=null) {
				connection.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(resultSet!=null) {
				resultSet.close();
			}
		}
	}
	
	public List<Reviews> getReviewsByRecipeId(Integer recipeId) throws SQLException{
		String selectReview = "SELECT ReviewId, ReviewText, RecipeId, Rating, UserId "
				+ "FROM Reviews WHERE RecipeId = ?;";
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			preparedStatement = connection.prepareStatement(selectReview);
			preparedStatement.setInt(1, recipeId);
			resultSet = preparedStatement.executeQuery();
			List<Reviews> reviews = new ArrayList<>();
			while(resultSet.next()) {
				reviews.add(new Reviews(resultSet.getInt("ReviewId"),
						resultSet.getString("ReviewText"), 
						resultSet.getInt("recipeId"), 
						resultSet.getInt("rating"), 
						resultSet.getInt("userId")));
			}
			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection!=null) {
				connection.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(resultSet!=null) {
				resultSet.close();
			}
		}
	}
	
	public List<Reviews> getReviewsByUserId(Integer userId) throws SQLException{
		String selectReview = "SELECT ReviewId, ReviewText, RecipeId, Rating, UserId "
				+ "FROM Reviews WHERE UserId = ?;";
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			connection = connectionManager.getConnection();
			preparedStatement = connection.prepareStatement(selectReview);
			preparedStatement.setInt(1, userId);
			resultSet = preparedStatement.executeQuery();
			List<Reviews> reviews = new ArrayList<>();
			while(resultSet.next()) {
				reviews.add(new Reviews(resultSet.getInt("ReviewId"),
						resultSet.getString("ReviewText"), 
						resultSet.getInt("recipeId"), 
						resultSet.getInt("rating"), 
						resultSet.getInt("userId")));
			}
			return reviews;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection!=null) {
				connection.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
			if(resultSet!=null) {
				resultSet.close();
			}
		}
	}
	
	public Reviews delete(Reviews review) throws SQLException {
		String deleteReview = "DELETE FROM Reviews WHERE ReviewId = ?;";
		Connection connection = connectionManager.getConnection();
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionManager.getConnection();
			preparedStatement = connection.prepareStatement(deleteReview);
			preparedStatement.setInt(1, review.getReviewId());
			preparedStatement.executeUpdate();
			return review;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			if(connection!=null) {
				connection.close();
			}
			if(preparedStatement!=null) {
				preparedStatement.close();
			}
		}
	}
}
