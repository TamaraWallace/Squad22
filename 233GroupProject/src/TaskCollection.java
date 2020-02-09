import java.util.ArrayList; // import the ArrayList class
import java.util.Date; // import date utility, copied from Task class

/* Team Questions: (Also posted on Slack)
 * -need to be able to get user id to create new task
 * -how do we want the tasks to be displayed
 * -how do we want the tasks to be stored in the csv file
 * -when saving, I was thinking it would be best to only save/append new tasks, does this sound 
 *  reasonable? if so, need a way to track which tasks are new
 *  -do we want tasks to be sorted in the task collection and if so by what value
*/
public class TaskCollection {

	private static int nextKey; // The key to use for the next task added.
	private ArrayList<Task> tasks = new ArrayList<Task>(); // An ArrayList of all the user's tasks.
	
	// Method Name: loadTasks
	// Parameters: fname (the name of the file to load tasks from) and userID (the id of the user whose tasks are being loaded)
	// Return: a TaskCollection object
	// Functionality: load a file containing all tasks, determine the value of the nextKey, store user's tasks in an ArrayList
	public TaskCollection loadTasks(String fname, int userID) {
		TaskCollection usersTasks = new TaskCollection();
		return usersTasks;
	}
	
	// Method Name: saveTasks
	// Parameters: fname (the name of the file to save tasks to)
	// Return: void
	// Functionality: ?
	// NOTE: I know it needs to save the tasks, but I'm not sure what the best implementation is
	public void saveTasks(String fname) {
		
	}
	
	// Method Name: display
	// Parameters: none
	// Return: void
	// Functionality: display all tasks in the current task collection
	public void display( ) {
		
	}
	
	// Method Name: getTask
	// Parameters: key (key of the task to retrieve)
	// Return: the task requested
	// Functionality: given a task key, return a task from a task collection
	// NOTE: Possibly use a more efficient search method? Also, need some way to get the userID
	public Task getTask(int key) {
		Task currentTask = null; //temporarily assigning null to the object to be returned
		for(int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getKey() == key) {
				// Copying the task to avoid privacy leak. 0 is used as a userID placeholder for now.
				currentTask = new Task(key, 0, tasks.get(i).getName(), tasks.get(i).getNotes(), tasks.get(i).isCompleted(), tasks.get(i).getDueDate());
			}
		}
		return currentTask;
	}
	
	// Method Name: addTask
	// Parameters: name, notes and date (all the respective fields for a task)
	// Return: void
	// Functionality: creates a new task and adds it to the tasks collection
	// NOTE: Although not mentioned in the UML, it needs to be passed the userID as well
	public void addTask(String name, String notes, Date date) {
		//0 is used as a userID placeholder for now.
		Task newTask = new Task(nextKey, 0, name, notes, false, date);
		tasks.add(newTask);
	}
	
	// Method Name: getActiveTasks
	// Parameters: none
	// Return: a task collection containing only non-completed tasks
	// Functionality: go through all tasks and add active tasks to a new task collection
	public TaskCollection getActiveTasks() {
		TaskCollection activeTasks = new TaskCollection();
		for(Task task : tasks) {
			if(!(task.isComplete())){
				//NOTE: Would it be better to use the addTask method and copy the task?
				activeTasks.tasks.add(task);
			}
		}
		return activeTasks;
	}
	
}
