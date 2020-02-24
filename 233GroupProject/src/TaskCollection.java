import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; // import the ArrayList class
import java.util.Date; // import date utility, copied from Task class
import java.util.stream.Stream;
import java.io.File;
import java.text.SimpleDateFormat;

public class TaskCollection {

	private ArrayList<Task> tasks; // An ArrayList of all the user's tasks.
	
	public TaskCollection() {
		tasks = new ArrayList<Task>();
	}
	
	// Method Name: loadUsrTasks
	// Parameters: fname (the name of the file to load tasks from) and userID (the id of the user whose tasks are being loaded)
	// Return: a TaskCollection object
	// Functionality: load a file containing all tasks, save all tasks to current arraylist of tasks, save users tasks to new collection and return object
	public static TaskCollection loadUsrTasks(String fname, int userID) {
		TaskCollection usersTasks = new TaskCollection();
		File f = new File(fname);
		if(!(f.exists())) {
			try{
				f.createNewFile();
			} catch (IOException io){}
		} else if(f.exists()) {
			// based on method 3 from this website:https://examples.javacodegeeks.com/core-java/java-8-read-file-line-line-example/ is used for basic file reading
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				Stream <String> lines = br.lines();
				String[] taskList = lines.toArray(String[] :: new);
				lines.close();
				br.close();
				for(String task : taskList) {
					String[] splitTask = task.split(",");
					Date due = new Date();
					try {
						due = new SimpleDateFormat("yyyy-MM-dd").parse(splitTask[splitTask.length-1]);
					} catch (Exception ParseException){}
					Task next = new Task(Integer.parseInt(splitTask[1]),splitTask[2], splitTask[3],Boolean.parseBoolean(splitTask[4]),due);
		            if (splitTask[1].equals(String.valueOf(userID))) {
		            	usersTasks.tasks.add(next);
		            }
				}
			} catch (IOException io) {}
		}
		return usersTasks;
	}
	
	// Method Name: saveTasks
	// Parameters: fname (the name of the file to save tasks to)
	// Return: void
	// Functionality: save all the users tasks to the file, including new tasks and updates
	public void saveTasks(String fname) {
		SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
		File f = new File(fname);
		if(!(f.exists())) {
			try{
				f.createNewFile();
			} catch (IOException io){}
		} 
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
            Stream <String> lines = br.lines();
            String[] taskList = lines.toArray(String[] :: new);
            lines.close();
            br.close();
            FileWriter bw = new FileWriter(fname);
            int lastKey = taskList.length - 1;
            ArrayList<String> tList = new ArrayList<String>();
            for(String s: taskList) {
            	for(Task task: this.tasks) {
            		if(Character.getNumericValue(s.charAt(0))==task.getTaskID()) {
            			Date d = task.getDueDate();
            			s = String.valueOf(task.getTaskID())+","+String.valueOf(task.getUserID())+","
            			+task.getName()+","+task.getNotes()+","+String.valueOf(task.isComplete())+","
            			+form.format(d);
            		}
            	}
            	tList.add(s+"\n");
            }
            
            for(Task t : this.tasks) {
            	if (lastKey<t.getTaskID()) {
            		Date d = t.getDueDate();
            		tList.add(String.valueOf(t.getTaskID())+","+String.valueOf(t.getUserID())+","
                			+t.getName()+","+t.getNotes()+","+String.valueOf(t.isComplete())+","
                			+form.format(d)+"\n");
            	}
            }
            
            for(String str : tList) {
            	bw.write(str);
            }
	            
            bw.close();
		} catch (IOException io) {}
	}
	
	// Method Name: display
	// Parameters: none
	// Return: void
	// Functionality: display all tasks in the current task collection
	public void display( ) {
		for (Task t: tasks) {
			System.out.println("\n"+t.toString());
		} if (tasks.isEmpty()) System.out.println("There are no tasks to display");
	}
	
	// Method Name: getTask
	// Parameters: key (key of the task to retrieve)
	// Return: the task requested
	// Functionality: given a task key, return a task from a task collection
	public Task getTask(int key) {
		Task currentTask = null;
		for (Task t: tasks) {
			if (t.getTaskID() == key) {
				currentTask = new Task(t);
			}
		}
		return currentTask;
	}
	
	// Method Name: addTask
	// Parameters: name, notes and date (all the respective fields for a task)
	// Return: void
	// Functionality: creates a new task and adds it to the tasks collection
	public void addTask(Task task) {
		tasks.add(task);
	}
	
	// Method Name: getActiveTasks
	// Parameters: none
	// Return: a task collection containing only non-completed tasks
	// Functionality: go through all tasks and add active tasks to a new task collection
	public ArrayList<Task> getActiveTasks() {
		ArrayList<Task> activeTasks = new ArrayList<Task>();
		for(Task t: this.tasks) {
			if(!(t.isComplete())){
				activeTasks.add(t);
			}
		}
		return activeTasks;
	}
	
	//Method Purpose: indicate whether or not the given TaskCollection is empty
	//Parameters:
	//Return: boolean
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
}
