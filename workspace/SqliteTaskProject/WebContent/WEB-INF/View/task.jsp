<%@ include file="header.jsp" %>
<%@ page import="model.bean.Task"%>
<%
	Task[] tasks = (Task[]) request.getAttribute("tasks");
	String message = (String) request.getAttribute("message");
	String editTask = (String) request.getAttribute("editTask");
	Task taskedit = (Task) request.getAttribute("task");
	String addTask = (String) request.getAttribute("addTask");

	if (message != null)
		out.println(message);
%>


	<h2>List of Task</h2>	
	<br>
	
	<a href="TaskServlet?action=add">Create New Task </a>

	<table>
		<thead>
			<tr>
				<th>No.</th>
				<th>Name</th>
				<th>Status</th>
				<th>Important</th>
				<th colspan="2">Action</th>
			</tr>
		</thead>
		<tbody>
			<%
				if (tasks != null && tasks.length != 0) {
					int num = 1;
					for (Task task : tasks) {
			%>
			<tr>
				<td><%=num%></td>
				<td><%=task.getName()%></td>
				<td><%=task.getStatus()%></td>
				<td><%=task.getImportant()%></td>
				<td><a href="TaskServlet?taskid=<%=task.getId()%>&action=delete">
						Delete </a></td>
				<td><a href="TaskServlet?taskid=<%=task.getId()%>&action=edit"> Edit
				</a></td>
			</tr>
			<%
					num++;
					}
				} else {
					message = "<h2 style='color: red'>There is no task</h2>";
					out.println(message);
				}
			%>
		</tbody>
	</table>

	<%
	if(addTask != null){
	%>
	<br><br>
	<h2>Create New Task</h2>
	<br>

	<form method="post" action="TaskServlet">
		<label>Task Name</label>
		<input type="text" name="taskname" placeholder="Specified your task here" required>
	
		<label>Status</label>
		<select name="status" required>
			<option value="">--Select Here--</option>
			<option value="Done">Done</option>
			<option value="Half Done">Half Done</option>
			<option value="Not Done">Not Done</option>
		</select>
		
		<label>Important</label>
		<select name="important" required>
			<option value="">--Select Here--</option>
			<option value="High">High</option>
			<option value="Medium">Medium</option>
			<option value="Low">Low</option>
		</select>
		
		<input type="submit" name="action1" value="create">
	</form>
	<%
	}
	%>
	
	<%
	if(editTask != null){
	%>
	<br><br>
	<h2>Edit Task</h2>
	<br>

	<form method="post" action="TaskServlet">
		<label>Task Name</label>
		<input type="text" name="taskname" value="<%=taskedit.getName() %>" required>
	
		<label>Status</label>
		<select name="status" required>
			<option value="Done" <%= taskedit.getStatus().equals("Done") ? "selected='selected'" : "" %>>Done</option>
			<option value="Half Done" <%= taskedit.getStatus().equals("Half Done") ? "selected='selected'" : "" %>>Half Done</option>
			<option value="Not Done" <%= taskedit.getStatus().equals("Not Done") ? "selected='selected'" : "" %>>Not Done</option>
		</select>
		
		<label>Important</label>
		<select name="important" required>
			<option value="High" <%= taskedit.getImportant().equals("High") ? "selected='selected'" : "" %>>High</option>
			<option value="Medium" <%= taskedit.getImportant().equals("Medium") ? "selected='selected'" : "" %>>Medium</option>
			<option value="Low" <%= taskedit.getImportant().equals("Low") ? "selected='selected'" : "" %>>Low</option>
		</select>
		
		<input type="hidden" name="taskid" value="<%=taskedit.getId() %>">
		<input type="submit" name="action1" value="editTask">
	</form>
	<%	
	}
	%>
	
<jsp:include page="footer.jsp" />