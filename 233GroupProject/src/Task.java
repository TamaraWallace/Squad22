import java.util.Date;  // https://stackabuse.com/how-to-get-current-date-and-time-in-java/

public class Task {

	
	int key; // to identify the user 
	int userID;	// The UMI saays int, shouldnt it be string?    	to identify the user
	String name; // name of the task
	String notes; // the description of the task
	boolean completed; // if the task has been marked complete by the user or not 
	Date dueDate; // the due date of the task, there is a pretty & easy way to format this from the website in line1
	
	
	
	// FOR GROUP MEMBERS: 
	// I'm not sure how to set the due date to be a user chosen one, because I have to get that from the key board input
	// which needs to be imported from the textbased app class i guess? so i just set them to the parameters from the constructor 
	// I can change this easily once we can agree on how we want to implement this 
	// SUGGESTION: make two variables, one is a due date from user input which I can get from the constructor and a variable called current date 
	// then we can compare those to see if a task is past its due date or not 
	Date currentDate;
	
	
//	Method name: Task 
//	Parameters: key, userID, name, notes, completed, dueDate
//	Return: void
//	Functionality: class constructor
//	
	public Task(int key, int userID, String name, String notes, boolean completed, Date dueDate) {
		
		this.key = key;
		this.userID = userID;
		this.name = name;
		this.notes = notes;
		this.completed = completed;
		this.dueDate = dueDate;
		
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
	
	
	
	
//	Method name: complete 
//	Parameters: none 
//	Return: void
//	Functionality: I have no idea, gonna fill this in when I know :)
	public void complete() {
		
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




	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}




	public void setUserID(int userID) {
		this.userID = userID;
	}




	public static void main(String[] args) {
	
}
}
