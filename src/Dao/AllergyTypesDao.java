package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Model.AllergyTypes;
import Model.AllergyTypes.Allergy;

public class AllergyTypesDao {
	protected ConnectionManager connectionManager;
	
	private static AllergyTypesDao instance = null;
	protected AllergyTypesDao() {
		connectionManager = new ConnectionManager();
	}
	
	public static AllergyTypesDao getInstance() {
		if (instance == null) {
			instance = new AllergyTypesDao();
		}
		return instance;
	}
	
	public AllergyTypes create(AllergyTypes allergyType) throws SQLException {
		String insertAllergyType = "INSERT INTO AllergyTypes(Allergy) VALUES(?);";
		Connection connection = null;
		PreparedStatement insertStmt = null;
		ResultSet resultKey = null;
		try {
			connection = connectionManager.getConnection();
			// BlogPosts has an auto-generated key. So we want to retrieve that key.
			insertStmt = connection.prepareStatement(insertAllergyType,
				Statement.RETURN_GENERATED_KEYS);
			insertStmt.setString(1, allergyType.getAllergy().name());
			insertStmt.executeUpdate();
			
			resultKey = insertStmt.getGeneratedKeys();
			int allergyTypesId = -1;
			if(resultKey.next()) {
				allergyTypesId = resultKey.getInt(1);
			} else {
				throw new SQLException("Unable to retrieve auto-generated key.");
			}
			allergyType.setAllergyTypesId(allergyTypesId);
			return allergyType;
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
	
	public AllergyTypes updateAllergy(AllergyTypes allergyType, Allergy newAllergy) throws SQLException {
		String updateAllergy = "UPDATE AllergyTypes SET Allergy=? WHERE AllergyTypesId=?;";
		Connection connection = null;
		PreparedStatement updateStmt = null;
		try {
			connection = connectionManager.getConnection();
			updateStmt = connection.prepareStatement(updateAllergy);
			updateStmt.setString(1, newAllergy.name());
			updateStmt.setInt(2, allergyType.getAllergyTypesId());
			updateStmt.executeUpdate();
			
			allergyType.setAllergy(newAllergy);
			return allergyType;
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
	
	public AllergyTypes delete(AllergyTypes allergyType) throws SQLException {
		String deleteAllergyType = "DELETE FROM AllergyTypes WHERE AllergyTypesId=?;";
		Connection connection = null;
		PreparedStatement deleteStmt = null;
		try {
			connection = connectionManager.getConnection();
			deleteStmt = connection.prepareStatement(deleteAllergyType);
			deleteStmt.setInt(1, allergyType.getAllergyTypesId());
			deleteStmt.executeUpdate();

			// Return null so the caller can no longer operate on the BlogPosts instance.
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
	
	public AllergyTypes getAllergyByAllergyTypesId(int allergyTypesId) throws SQLException {
		String selectAllergyType = 
			"SELECT AllergyTypesId,Allergy " +
			"FROM AllergyTypes " +
			"WHERE AllergyTypesId=?;";
		Connection connection = null;
		PreparedStatement selectStmt = null;
		ResultSet results = null;
		try {
			connection = connectionManager.getConnection();
			selectStmt = connection.prepareStatement(selectAllergyType);
			selectStmt.setInt(1, allergyTypesId);
			results = selectStmt.executeQuery();
			
			if(results.next()) {
				int resultAllergyTypesId = results.getInt("AllergyTypesId");
				Allergy allergy = Allergy.valueOf(results.getString("Allergy"));
				
				AllergyTypes allergyType = new AllergyTypes(resultAllergyTypesId, allergy);
				return allergyType;
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
}
