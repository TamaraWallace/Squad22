package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Stream;
import java.io.File;

public class TaskCollection {

	private static ArrayList<Task> tasks; // An ArrayList of all the user's tasks.
	
	public TaskCollection() {
		tasks = new ArrayList<Task>();
	}
	
	// Method Name: loadUsrTasks
	// Parameters: fname (the name of the file to load tasks from) and userID (the id of the user whose tasks are being loaded)
	// Return: a TaskCollection object
	// Functionality: load a file containing all tasks, save all tasks to current arraylist of tasks, save users tasks to new collection and return object
	public static TaskCollection loadUsrTasks(String fname, UUID userID) {
		TaskCollection usersTasks = new TaskCollection();
		File f = new File(fname);
		if(!(f.exists())) {
			try{
				f.createNewFile();
			} catch (IOException io){}
		} else {
			// based on method 3 from this website:https://examples.javacodegeeks.com/core-java/java-8-read-file-line-line-example/ is used for basic file reading
			try {
				BufferedReader br = new BufferedReader(new FileReader(f));
				Stream <String> lines = br.lines();
				String[] taskList = lines.toArray(String[] :: new);
				lines.close();
				br.close();
				for(String taskSaveString : taskList) {
					if (UUID.fromString(taskSaveString.split(",")[1]).compareTo(userID) == 0) {
						Task next = Task.fromString(taskSaveString);
						usersTasks.addTask(next);
					}
				}
			} catch (IOException io) {}
		}
		usersTasks.sortTasks();
		return usersTasks;
	}
	
	// Method Name: saveTasks
	// Parameters: fname (the name of the file to save tasks to)
	// Return: void
	// Functionality: save all the users tasks to the file, including new tasks and updates
	public void saveTasks(String fname) {
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
            UUID usrID = tasks.get(0).getUserID();
            int lastIndSaved = 0;
            for (String s: taskList) {
            	UUID tempUsrId = UUID.fromString(s.split(",")[1]);
            	UUID tempID = UUID.fromString(s.split(",")[0]);
            	Task temp = getTaskByID(tempID);
            	if (!usrID.equals(tempUsrId)) {
            		bw.write(s + "\n");
            		System.out.println("Other user");
            	} else if(temp.getName().equals("")) {
            		lastIndSaved = tasks.indexOf(temp);
            		System.out.println("Deleting");
            	} else {
            		bw.write(temp.toSaveString());
            		lastIndSaved = tasks.indexOf(temp);
            		System.out.println("Current user");
            	}
            }
            for (int i = lastIndSaved + 1; i < tasks.size(); i++) {
            	if(!tasks.get(i).getName().equals("")) {
            		bw.write(tasks.get(i).toSaveString());
            		System.out.println("New");
            	}
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
	public Task getTaskByID(UUID uuid) {
		Task temp = null;
		for (Task t: tasks) {
			if (t.getTaskID().compareTo(uuid) == 0) temp = t;
		}
		return temp;
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
		for(Task t: TaskCollection.tasks) {
			if(!(t.isComplete())){
				activeTasks.add(t);
			}
		}
		return activeTasks;
	}
	
	//Method Purpose: indicate whether or not the given TaskCollection is empty
	//Parameters: none
	//Return: boolean
	public boolean isEmpty() {
		return tasks.isEmpty();
	}
	
	// Method Name: sortTasks
	// Parameters: none
	// Return: nothing
	// Functionality: sort tasks according to date using a bubble sort algorithm
	// Bubble Sort Algorithm Source: https://www.geeksforgeeks.org/bubble-sort/
	public void sortTasks() {
		boolean swapped;
		int n = tasks.size();
		 for (int i = 0; i < n - 1; i++)  { 
	            swapped = false; 
	            for (int j = 0; j < n - i - 1; j++)  { 
	                if (tasks.get(j).getDueDate().isAfter(tasks.get(j+1).getDueDate()))  { 
	                	Task temp = tasks.get(j);
	                	tasks.set(j, tasks.get(j+1));
	                    tasks.set(j+1, temp); 
	                    swapped = true; 
	                } 
	            } 
	            if (swapped == false) 
	                break; 
	        }
	}
	
}
