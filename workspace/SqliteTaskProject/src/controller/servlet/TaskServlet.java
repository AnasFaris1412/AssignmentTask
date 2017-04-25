package controller.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Task;
import controller.manager.Facade;

/**
 * Servlet implementation class TaskServlet
 */
@WebServlet("/TaskServlet")
public class TaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String message = null;		
		String action = request.getParameter("action");
		
		if(action != null){
			
			String task_id = request.getParameter("taskid");
			
			if(action.equals("edit")){
				try {
					Facade facade = new Facade();
					Task task = facade.getTaskById(Integer.parseInt(task_id));
					
					if(task != null){
						request.setAttribute("task", task);
						request.setAttribute("editTask", "editTask");
					}
					else
						message = "<h2 style='color: red'> Specific Task Not Found </h2>";
					
					facade.close();
					
				} catch (SQLException e) {
					message = e.getMessage();
					request.setAttribute("message", "<h2 style='color: red'>" + message + "</h2>");
					request.getRequestDispatcher("WEB-INF/View/task.jsp").forward(request, response);
					return;
				}
			}
			else if(action.equals("delete")){
				
				try {
					Facade facade = new Facade();
					int status = facade.deleteTask(task_id);
					
					if(status == 1){
						message = "<h2 style='color: green'> Successfully delete Task </h2>";
					} else {
						message = "<h2 style='color: red'> Failed to delete Task </h2>";
					}
					request.setAttribute("message", message);
					facade.close();
					
				} catch (SQLException e) {	
					message = e.getMessage();
					request.setAttribute("message", "<h2 style='color: red'>" + message + "</h2>");
					request.getRequestDispatcher("WEB-INF/View/task.jsp").forward(request, response);
					return;
				}
			}
			else if(action.equals("add")){
				request.setAttribute("addTask", "addTask");
			}
			else if(action.equals("listAll")){
				// nothing	
			}
			
			try {
				Facade facade = new Facade();
				List<Task> tasks = facade.getAllTask();
				Task[] array = tasks.toArray(new Task[]{});
				request.setAttribute("tasks", array);			
				facade.close();
				
			} catch (SQLException e) {
				message = e.getMessage();
				request.setAttribute("message", "<h2 style='color: red'>" + message + "</h2>");
				request.getRequestDispatcher("WEB-INF/View/task.jsp").forward(request, response);
				return;
			}
			
			request.getRequestDispatcher("WEB-INF/View/task.jsp").forward(request, response);
		}	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action1");
		
		if(action != null){
			String message = null;
			
			if(action.equals("create")){
				
				String taskname = request.getParameter("taskname");
				String status = request.getParameter("status");
				String important = request.getParameter("important");				
				
				Task task = new Task();
				task.setName(taskname);
				task.setStatus(status);
				task.setImportant(important);
				
				try {
					Facade facade = new Facade();
					int status1 = facade.createTask(task);
					
					if(status1 == 1){
						message = "<h2 style='color: green'> Successfully create Task </h2>";
					} else {
						message = "<h2 style='color: red'> Failed to create Task </h2>";
					}
					request.setAttribute("message", message);
					facade.close();
					
				} catch (SQLException e) {
					message = e.getMessage();
					request.setAttribute("message", "<h2 style='color: red'>" + message + "</h2>");
					request.getRequestDispatcher("WEB-INF/View/task.jsp").forward(request, response);
					return;
				}
				
			}
			else if(action.equals("editTask")) {
				
				String taskid = request.getParameter("taskid");
				String taskname = request.getParameter("taskname");
				String status = request.getParameter("status");
				String important = request.getParameter("important");				
				
				Task task = new Task();
				
				task.setId(Integer.parseInt(taskid));
				task.setName(taskname);
				task.setStatus(status);
				task.setImportant(important);
				
				try {
					Facade facade = new Facade();
					int status1 = facade.editTask(task);
					
					if(status1 == 1){
						message = "<h2 style='color: green'> Successfully edit Task </h2>";
					} else {
						message = "<h2 style='color: red'> Failed to edit Task </h2>";
					}
					request.setAttribute("message", message);
					facade.close();				
					
				} catch (SQLException e) {
					message = e.getMessage();
					request.setAttribute("message", "<h2 style='color: red'>" + message + "</h2>");
					request.getRequestDispatcher("WEB-INF/View/task.jsp").forward(request, response);
					return;
				}
			}
			
			try {
				Facade facade = new Facade();
				List<Task> tasks = facade.getAllTask();
				Task[] array = tasks.toArray(new Task[]{});
				request.setAttribute("tasks", array);			
				facade.close();
				
			} catch (SQLException e) {
				message = e.getMessage();
				request.setAttribute("message", "<h2 style='color: red'>" + message + "</h2>");
				request.getRequestDispatcher("WEB-INF/View/task.jsp").forward(request, response);
				return;
			}
			
			request.getRequestDispatcher("WEB-INF/View/task.jsp").forward(request, response);
		}
	}

}
