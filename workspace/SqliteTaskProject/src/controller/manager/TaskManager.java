package controller.manager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.bean.Task;

/**
 * @author Anas Faris
 * This class will handle module Task
 * This class set protected not public
 */
class TaskManager {

	private Facade facade;

	/**
	 * Constructor
	 * @param facade
	 */
	TaskManager(Facade facade){
		this.facade = facade;
	}

	/**
	 * Function getAllTask
	 * @return All task in the Table Task
	 * @throws SQLException
	 */
	List<Task> getAllTask() throws SQLException{		
		// define sql statement
		String sql = "SELECT ID, Name, Status, Important FROM Task2;";
		
		// Create a statement object to be sent to database
		PreparedStatement ps = facade.getPreparedStatement(sql);

		// Send the statement and retrieve the results
		ResultSet rs = ps.executeQuery();

		// Create an array list to store the results
		List<Task> tasks = new ArrayList<Task>();
		
		Task task = null;

		// Read the results
		while (rs.next()) {
			// Create a new Task object
			task = new Task();
			
			// Assign the value from the database
			task.setId(rs.getInt("ID"));
			task.setName(rs.getString("Name"));
			task.setStatus(rs.getString("Status"));
			task.setImportant(rs.getString("Important"));
			
			tasks.add(task);
		}		
		// Return read result
		return tasks;
	}
	
	/**
	 * Function delete Task by ID
	 * @param ID
	 * @return status 1 = success, -1 = failed
	 * @throws SQLException
	 */
	int deleteTask(String ID) throws SQLException{
		// define sql statement
		String sql = "DELETE FROM Task2 WHERE ID = ?;";

		// Create a statement object to be sent to database
		PreparedStatement ps = facade.getPreparedStatement(sql);

		// Set the values of the statement
		ps.setString(1, ID);

		// execute the query on prepared statement
		int status = ps.executeUpdate();

		// if sql statement successfully committed
		if (status > 0) {
			return 1;
		} else {
			// sql statement failed
			return -1;
		}
	}
	
	/**
	 * Function create new Task
	 * @param task
	 * @return status 1 = success, -1 = failed
	 * @throws SQLException
	 */
	int createTask(Task task) throws SQLException{
		// define sql statement
		String sql = "INSERT INTO Task2(Name, Status, Important) VALUES (?,?,?);";

		// Create a statement object to be sent to database
		PreparedStatement ps = facade.getPreparedStatement(sql);

		// Set the values of the statement
		ps.setString(1, task.getName());
		ps.setString(2, task.getStatus());
		ps.setString(3, task.getImportant());

		// Send the statement and retrieve the status
		int status = ps.executeUpdate();

		// if sql statement successfully committed
		if (status > 0) {
			return 1;
		} else {
			// sql statement failed
			return -1;
		}
	}
	
	/**
	 * Function edit Task based on specific ID
	 * @param task
	 * @return status 1 = success, -1 = failed
	 * @throws SQLException
	 */
	int editTask(Task task) throws SQLException{
		// define sql statement
		String sql = "UPDATE Task2 SET Name =?, Status=?, Important=? WHERE ID = ?;";

		// Create a statement object to be sent to database
		PreparedStatement ps = facade.getPreparedStatement(sql);

		// Set the values of the statement
		ps.setString(1, task.getName());
		ps.setString(2, task.getStatus());
		ps.setString(3, task.getImportant());
		ps.setInt(4, task.getId());

		// Send the statement and retrieve the status
		int status = ps.executeUpdate();

		// if sql statement successfully committed
		if (status > 0) {
			return 1;
		} else {
			// sql statement failed
			return -1;
		}
	}
	
	/**
	 * Function get specific Task based on ID
	 * @param id
	 * @return task
	 * @throws SQLException
	 */
	Task getTaskById(Integer id) throws SQLException{
		// define sql statement
		String sql = "SELECT ID, Name, Status, Important FROM Task2 WHERE ID = ?;";

		// Create a statement object to be sent to database
		PreparedStatement ps = facade.getPreparedStatement(sql);
		
		// Set the values of the statement
		ps.setInt(1, id);

		// Send the statement and retrieve the results
		ResultSet rs = ps.executeQuery();
		
		Task task = null;

		// Read the result
		if (rs.next()) {
			// Create a new Task object
			task = new Task();

			// Assign the value from the database
			task.setId(rs.getInt("ID"));
			task.setName(rs.getString("Name"));
			task.setStatus(rs.getString("Status"));
			task.setImportant(rs.getString("Important"));
		}		
		// Return read result
		return task;
	}



}
