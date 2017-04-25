/**
 * 
 */
package model.bean;

import java.io.Serializable;

/**
 * This class represents the Task table
 * inside database
 * @author Anas Faris
 * @version 1.0
 * @serial 1.0
 */
public final class Task implements Serializable {
	
	/**
	 * List of all private variable
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private String status;
	private String important;
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the important
	 */
	public String getImportant() {
		return important;
	}
	/**
	 * @param important the important to set
	 */
	public void setImportant(String important) {
		this.important = important;
	}

}
