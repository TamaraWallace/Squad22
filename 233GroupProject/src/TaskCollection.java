import java.util.Scanner;
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
	// Functionality: load a file containing all tasks, save all tasks to current arraylist of tasks, save users tasks to new collection and return object
	public static TaskCollection loadUsrTasks(String fname, int userID) {
		TaskCollection usersTasks = new TaskCollection();
		// based on method 3 from this website:https://examples.javacodegeeks.com/core-java/java-8-read-file-line-line-example/ is used for basic file reading
		try {
			BufferedReader br = new BufferedReader(new FileReader(fname));
			Stream <String> lines = br.lines();
			String[] taskList = lines.toArray(String[] :: new);
			lines.close();
			br.close();
			for(String task : taskList) {
				String[] splitTask = task.split(",");
				String[] d = splitTask[splitTask.length-1].split("-");
				Date due = new Date(Integer.parseInt(d[0].trim()), Integer.parseInt(d[1].trim())-1,Integer.parseInt(d[2].trim()));
				Task next = new Task(Integer.parseInt(splitTask[1].trim()),splitTask[2], splitTask[3],Boolean.parseBoolean(splitTask[4].trim()),due);
	            if (splitTask[1].trim().equals(String.valueOf(userID))) {
	            	usersTasks.tasks.add(next);
	            }
			}
		} catch (IOException io) {
			io.printStackTrace();
		}
		return usersTasks;
	}
	
	// Method Name: saveTasks
	// Parameters: fname (the name of the file to save tasks to)
	// Return: void
	// Functionality: save all the users tasks to the file, including new tasks and updates
	public void saveTasks(String fname) {
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
            			s = String.valueOf(task.getTaskID())+", "+String.valueOf(task.getUserID())+", "
            			+task.getName()+", "+task.getNotes()+", "+String.valueOf(task.isCompleted())+", "
            			+String.valueOf(d.getYear())+"-"+String.valueOf(d.getMonth()+1)+"-"+String.valueOf(d.getDate());
            		}
            	}
            	tList.add(s+"\n");
            }
            
            for(Task t : this.tasks) {
            	if (lastKey<t.getTaskID()) {
            		Date d = t.getDueDate();
            		tList.add(String.valueOf(t.getTaskID())+", "+String.valueOf(t.getUserID())+", "
                			+t.getName()+", "+t.getNotes()+", "+String.valueOf(t.isCompleted())+", "
                			+String.valueOf(d.getYear())+"-"+String.valueOf(d.getMonth()+1)+"-"+String.valueOf(d.getDate())+"\n");
            	}
            }
            
            for(String str : tList) {
            	bw.write(str);
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
			System.out.println("--------------------------------");
			System.out.println(t.toString());
		}
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
	public void addTask() {
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Task Title: ");
		String task_name = keyboard.nextLine();
		System.out.println("Notes: ");
		String task_notes = keyboard.nextLine();
		System.out.println("Due date: ");
		String task_date = keyboard.nextLine();
		
		System.out.println("Is the following information correct?");
		System.out.println(task_name+System.lineSeparator()+task_notes+System.lineSeparator()+task_date);
			
		String answer = keyboard.nextLine();
		
		while(answer.toLowerCase()!="yes") {
			System.out.println("What would you like to edit?");
			String response = keyboard.nextLine();
			if (response.toLowerCase()=="title") {
				System.out.println("Task Title: ");
				task_name = keyboard.nextLine();
			} else if(response.toLowerCase()=="notes"){
				System.out.println("Notes: ");
				task_notes = keyboard.nextLine();
			} else if(response.toLowerCase()=="due date" || response.toLowerCase()=="date") {
				System.out.println("Due date: ");
				task_date = keyboard.nextLine();
			}
			System.out.println("Is the following information correct?");
			System.out.println(task_name+System.lineSeparator()+task_notes+System.lineSeparator()+task_date);
				
			answer = keyboard.nextLine();
		}
		
		//Will need to correct this once compiled together 
		//String name = user.getUsrName();
		
		//Task new_task = new Task(name, task_name, task_notes, user.isComplete() , task_date);
		
		//tasks.add(new_task);
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
