import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; // import the ArrayList class
import java.util.Date; // import date utility, copied from Task class
import java.util.stream.Stream;

/* Team Questions: (Also posted on Slack)
 * -need to be able to get user id to create new task
 * -how do we want the tasks to be displayed
 * -how do we want the tasks to be stored in the csv file
 * -when saving, I was thinking it would be best to only save/append new tasks, does this sound 
 *  reasonable? if so, need a way to track which tasks are new
 *  -do we want tasks to be sorted in the task collection and if so by what value
*/
public class TaskCollection {

	private ArrayList<Task> tasks; // An ArrayList of all the user's tasks.
	
	public TaskCollection() {
		tasks = new ArrayList<Task>();
	}
	
	// Method Name: loadUsrTasks
	// Parameters: fname (the name of the file to load tasks from) and userID (the id of the user whose tasks are being loaded)
	// Return: a TaskCollection object
	// Functionality: load a file containing all tasks, determine the value of the nextKey, store user's tasks in an ArrayList
	public static TaskCollection loadTasks(String fname) {
		TaskCollection usersTasks = new TaskCollection();
		// based on method 3 from this website:https://examples.javacodegeeks.com/core-java/java-8-read-file-line-line-example/ is used for basic file reading
				try {
		            BufferedReader br = new BufferedReader(new FileReader(fname));
		            Stream <String> lines = br.lines();
		            String[] taskList = lines.toArray(String[] :: new);
		            lines.close();
		            br.close();
		            //
		            for(String task : taskList) {
		            	//System.out.println(task.getClass().getSimpleName());
		            	String[] splitTask = task.split(",");
	            		String[] d = splitTask[splitTask.length-1].split("-");
	            		Date due = new Date(Integer.parseInt(d[0].trim()), Integer.parseInt(d[1].trim())-1,Integer.parseInt(d[2].trim()));
	            		Task next = new Task(Integer.parseInt(splitTask[1].trim()),splitTask[2], splitTask[3],Boolean.parseBoolean(splitTask[4].trim()),due);
	            		usersTasks.tasks.add(next);
	            	}
		        } catch (IOException io) {
		            io.printStackTrace();
		        }
		return usersTasks;
	}
	
	// Method Name: saveTasks
	// Parameters: fname (the name of the file to save tasks to)
	// Return: void
	// Functionality: ?
	// NOTE: I know it needs to save the tasks, but I'm not sure what the best implementation is
	public void saveTasks(String fname) {
		try {
			FileWriter bw = new FileWriter(fname, true);
			BufferedReader br = new BufferedReader(new FileReader(fname));
            Stream <String> lines = br.lines();
            String[] taskList = lines.toArray(String[] :: new);
            lines.close();
            br.close();
            Task.setNextID(taskList.length);
            //change to if line key is in tasks array update line
            for(Task task: this.tasks) {
            	if(task.getNextID()<task.getTaskID()) {
            		Date d = task.getDueDate();
            		bw.write(String.valueOf(task.getTaskID())+", "+String.valueOf(task.getUserID())+", "+task.getName()+", "+task.getNotes()+", "+String.valueOf(task.isCompleted())+", "+String.valueOf(d.getYear())+"-"+String.valueOf(d.getMonth()+1)+"-"+String.valueOf(d.getDate())+"\n");
            	}
            }
            bw.close();
		} catch (IOException io) {
			
		}
	}
	
	// Method Name: display
	// Parameters: none
	// Return: void
	// Functionality: display all tasks in the current task collection
	public void display( ) {

		for (Task t: tasks) {
			System.out.println("***********************");
			System.out.println(t.toString());
		}
		/*for(int i = 0; i<this.tasks.size(); i++) {
			System.out.println(String.valueOf(i+1)+".  "+tasks.get(i).getName()+"\t"+String.valueOf(tasks.get(i).getDueDate().getYear())+"-"+String.valueOf(tasks.get(i).getDueDate().getMonth()+1)+"-"+String.valueOf(tasks.get(i).getDueDate().getDate()));
			System.out.println("\t\t"+tasks.get(i).getNotes());
			if(!(tasks.get(i).getNotes().equals(""))) {
				System.out.println("");
			}
		}*/
	}
	
	// Method Name: getTask
	// Parameters: key (key of the task to retrieve)
	// Return: the task requested
	// Functionality: given a task key, return a task from a task collection
	// NOTE: Possibly use a more efficient search method? Also, need some way to get the userID
	public Task getTask(int key) {
		Task currentTask = null; //temporarily assigning null to the object to be returned
		for(int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).getTaskID() == key) {
				currentTask = tasks.get(i);
			}
		}
		return currentTask;
	}
	
	// Method Name: addTask
	// Parameters: name, notes and date (all the respective fields for a task)
	// Return: void
	// Functionality: creates a new task and adds it to the tasks collection
	// NOTE: Although not mentioned in the UML, it needs to be passed the userID as well
	public void addTask(Task t) {
		tasks.add(t);
	}
	
	// Method Name: getActiveTasks
	// Parameters: none
	// Return: a task collection containing only non-completed tasks
	// Functionality: go through all tasks and add active tasks to a new task collection
	public ArrayList<Task> getActiveTasks() {
		ArrayList<Task> activeTasks = new ArrayList<Task>();
		for(Task t: tasks) {
			if(!(t.isComplete())){
				activeTasks.add(new Task(t));;
			}
		}
		return activeTasks;
	}
	
	//Method Purpose: given a TaskCollection, return all the tasks belonging to a particular user.
	//Parameters: int
	//Return: TaskCollection
	public TaskCollection getUserTasks(int usrID) {
		TaskCollection userTasks = new TaskCollection();
		for (Task t: tasks) {
			if (t.getUserID() == usrID) {
				userTasks.addTask(t);
			}
		}
		return userTasks;
	}
	
	//Method Purpose: indicate whether or not the given TaskCollection is empty
	//Parameters:
	//Return: boolean
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
}
