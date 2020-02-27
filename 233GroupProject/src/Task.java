import java.time.LocalDate;  // https://stackabuse.com/how-to-get-current-date-and-time-in-java/
import java.util.UUID;

public class Task {

	
	private UUID taskID; // to identify the task
	private UUID userID;	// to identify the user
	private String name; // name of the task
	private String notes; // the description of the task
	private boolean completed; // if the task has been marked complete by the user or not 
	private LocalDate dueDate; // the due date of the task, there is a pretty & easy way to format this from the website in line1
	

	
	
	public Task() { }
	
//	Method name: Task 
//	Parameters: key, userID, name, notes, completed, dueDate
//	Return: void
//	Functionality: class constructor
//	
	public Task(UUID userID, String name, String notes, boolean completed, LocalDate dueDate) {
		this.taskID = UUID.randomUUID();
		this.userID = userID;
		this.name = name;
		this.notes = notes;
		this.completed = completed;
		this.dueDate = dueDate;
	}

//	Method name: Task 
//	Parameters: Task t
//	Return: void
//	Functionality: copy constructor

	public Task(Task t) {
		this.setTaskID(t.taskID);
		this.setUserID(t.userID);
		this.setName(t.name);
		this.setNotes(t.notes);
		this.setCompleted(t.completed);
		this.setDueDate(t.dueDate);
	}
	
	public String toSaveString() {
		return taskID.toString() + ","
				+ userID.toString() + ","
				+ name + ","
				+ notes + ","
				+ completed + ","
				+ dueDate.toString() + "\n";
	}
	
	public static Task fromString(String s) {
		Task temp = new Task();
		String[] vals = s.split(",");
		temp.taskID = UUID.fromString(vals[0]);
		temp.userID = UUID.fromString(vals[1]);
		temp.name = vals[2];
		temp.notes = vals[3];
		temp.completed = Boolean.valueOf(vals[4]);
		temp.dueDate = LocalDate.parse(vals[5]);
		return temp;
	}
	
	
// 	    Method name: toString 
//		Parameters: none 
//		Return: String 
//		Functionality: returns a string with the attributes of the object, this will be used to print information about the task.
	
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
	
	// setters and getters 

	public UUID getTaskID() {
		return this.taskID;
	}
	
	public UUID getUserID() {
		return this.userID;
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

	
	public void setTaskID(UUID taskID2) {
		this.taskID = taskID2;
	}
	
	public void setUserID(UUID userID2) {
		this.userID = userID2;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
}
