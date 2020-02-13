import java.util.Date;  // https://stackabuse.com/how-to-get-current-date-and-time-in-java/

public class Task {

	
	private int key; // to identify the task
	private int userID;	// to identify the user
	private String name; // name of the task
	private String notes; // the description of the task
	private boolean completed; // if the task has been marked complete by the user or not 
	private Date dueDate; // the due date of the task, there is a pretty & easy way to format this from the website in line1
	private static int nextID = 0;
	

	
//	Method name: Task 
//	Parameters: key, userID, name, notes, completed, dueDate
//	Return: void
//	Functionality: class constructor
//	
	public Task(int userID, String name, String notes, boolean completed, Date dueDate) {
		this.key = nextID++;
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
		this.setKey(t.key);
		this.setUserID(t.userID);
		this.setName(t.name);
		this.setNotes(t.notes);
		this.setCompleted(t.completed);
		this.setDueDate(t.dueDate);
	}
	
	
// 	    Method name: toString 
//		Parameters: none 
//		Return: String 
//		Functionality: returns a string with the attributes of the object, this will be used to print information about the task.
	
	public String toString() {
		
		return "key:" + this.key  + " userID: "+ this.userID +  "name:"+this.name+  " description:"+this.notes +  " due date:"+ this.dueDate;
		
	}
	
	
//	Method name: isComplete
//	Parameters: none 
//	Return: boolean completed
//	Functionality: checks if a task is complete based on the completed boolean attribute
	
	public boolean isComplete() {
		if (this.completed) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	
	
	// setters and getters 
	
 
	
	
	public int getKey() {
		return key;
	}




	public void setKey(int key) {
		this.key = key;
	}




	public String getName() {
		return name;
	}




	public void setName(String name) {
		this.name = name;
	}




	public String getNotes() {
		return notes;
	}




	public void setNotes(String notes) {
		this.notes = notes;
	}




	public boolean isCompleted() {
		return completed;
	}




	public void setCompleted(boolean completed) {
		this.completed = completed;
	}




	public Date getDueDate() {
		return dueDate;
	}



	public int getUserID() {
		return this.userID;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}




	public void setUserID(int userID) {
		this.userID = userID;
	}




	public static void main(String[] args) {
	
}
}
