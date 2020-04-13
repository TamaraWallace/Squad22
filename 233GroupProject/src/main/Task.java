package main;

import java.time.LocalDate;  // https://stackabuse.com/how-to-get-current-date-and-time-in-java/
import java.util.UUID;

public class Task {

	
	private UUID taskID; // to identify the task
	private UUID userID; // to identify the user
	private String name; // name of the task
	private String notes; // the description of the task
	private boolean completed = false; // if the task has been marked complete by the user or not 
	private LocalDate dueDate; // the due date of the task, there is a pretty & easy way to format this from the website in line1
	
	//	Method name: Task 
	//	Parameters: key, userID, name, notes, completed, dueDate
	//	Return: void
	//	Functionality: class constructor
	public Task(UUID userID, String name, String notes, LocalDate dueDate) {
		this.taskID = UUID.randomUUID();
		this.userID = userID;
		this.name = name;
		this.notes = notes;
		this.dueDate = dueDate;
	}

	public Task(String saveString) {
		String[] vals = saveString.split(",");
		this.taskID = UUID.fromString(vals[0]);
		this.userID = UUID.fromString(vals[1]);
		this.name = vals[2];
		this.notes = vals[3];
		this.completed = Boolean.valueOf(vals[4]);
		this.dueDate = LocalDate.parse(vals[5]);
	}
	
	//	Method name: Task 
	//	Parameters: Task t
	//	Return: void
	//	Functionality: copy constructor without TaskID or UserID
	public Task(Task t) {
		this.taskID = null;
		this.userID = null;
		this.name = t.name;
		this.notes = t.notes;
		this.completed = t.completed;
		this.dueDate = t.dueDate;
	}
	
	protected String toSaveString() {
		return taskID.toString() + ","
				+ userID.toString() + ","
				+ name + ","
				+ notes + ","
				+ completed + ","
				+ dueDate.toString() + "\n";
	}	
	
// 	    Method name: toString 
//		Parameters: none 
//		Return: String 
//		Functionality: returns a string with the attributes of the object, this will be used to print information about the task.
	
	@Override
	public String toString() {
		return "\tName: " + this.name +
				"\n\tTaskID: " + this.taskID  +
				"\n\tUserID: " + this.userID +
				"\n\tDescription: " + this.notes + 
				"\n\tCompleted: " + this.completed +
				"\n\tDue Date: " + this.dueDate;
	}
	
	
//	Method name: isComplete
//	Parameters: none 
//	Return: boolean completed
//	Functionality: checks if a task is complete based on the completed boolean attribute
	public boolean isComplete() {
		return this.completed;
	}
	
//	Method name: delete
//	Parameters: none 
//	Return: void
//	Functionality: sets the task name to null and completed status to true so it is not saved or shown in active tasks
	public void delete() {
		setName("");
		complete();
	}
	
	// setters and getters 

	public UUID getTaskID() {
		return this.taskID;
	}
	
	public String getName() {
		return new String(name);
	}
	
	public String getNotes() {
		return new String(notes);
	}
	
	public LocalDate getDueDate() {
		return LocalDate.from(dueDate);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void complete() {
		this.completed = true;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
}
