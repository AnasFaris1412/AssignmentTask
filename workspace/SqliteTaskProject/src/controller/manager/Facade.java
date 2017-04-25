/**
 * Package Controller Manager contain
 * Facade and all modules involved
 */
package controller.manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import model.bean.Task;

/**
 * @author Anas Faris
 * Facade will hides the complexities of the system
 * Provides an interface to the client
 * This class is used to become the bridge of data manager
 * and the front-end.
 */
public class Facade {
	
	/**
	 * List of all private variable
	 */
	private Connection connection;
	private TaskManager taskManager;
	
	/**
	 * Load the database engine driver
	 * @throw new ExceptionInInitializerError
	 */
	static{
		try{
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e){
			throw new ExceptionInInitializerError("Unable to load database driver. The system will exit now.");
		}
	}
	
	/**
	 * Constructor of Facade to get Connection 
	 * Connect to the database using specified URL, user ID, and password
	 * setAutoCommit to false
	 * @throws SQLException
	 */
	public Facade() throws SQLException {
		connection = DriverManager.getConnection("jdbc:sqlite:D:/SQLite Project/TaskProject/taskproject.db");
		connection.setAutoCommit(false);
	}
	
	/**
	 * Get connection
	 * @return connection
	 */
	public Connection getConnection() {
		return connection;
	}
	
	/**
	 * If connection not null will close
	 * @throws SQLException
	 */
	public void close() throws SQLException	{
		if (connection != null && !connection.isClosed())
		{
			connection.commit();
			connection.close();
		}
	}
	
	/**
	 * Function getPreparedStatement
	 * @param query
	 * @param operation
	 * @return prepared Statement 
	 * with their get connection
	 * @throws SQLException
	 */
	PreparedStatement getPreparedStatement(String query, int... operation) throws SQLException	{
		return connection.prepareStatement(query, operation);
	}
	
	/*
	 * This part will start get all manager object
	 * whether the object is null or not
	 */
	
	/**
	 * getTaskManager
	 * Will check getTaskManager is null or not
	 * @return taskManager
	 */
	private TaskManager getTaskManager(){
		/*if(taskManager == null)
			taskManager = new TaskManager(this);
		return taskManager;*/
		
		return taskManager == null ? taskManager = new TaskManager(this) :taskManager;
	}
	
	/*
	 * This part will start all the method interface of Task Manager module
	 * This part all method are public
	 */
	
	public List<Task> getAllTask() throws SQLException{
		return getTaskManager().getAllTask();		
	}
	
	public int deleteTask(String ID) throws SQLException{
		return getTaskManager().deleteTask(ID);
	}
	
	public int createTask(Task task) throws SQLException{
		return getTaskManager().createTask(task);
	}
	
	public int editTask(Task task) throws SQLException{
		return getTaskManager().editTask(task);
	}
	
	public Task getTaskById(Integer id) throws SQLException{
		return getTaskManager().getTaskById(id);
	}
	
}
