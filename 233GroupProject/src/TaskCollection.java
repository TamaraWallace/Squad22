import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList; // import the ArrayList class
import java.util.Date; // import date utility, copied from Task class
import java.util.stream.Stream;

public class TaskCollection {

	private ArrayList<Task> tasks = new ArrayList<Task>(); // An ArrayList of all the user's tasks.
	
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
		            	if(splitTask[1].trim().equals(String.valueOf(userID))) {
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
		System.out.println("Saving. Please do not close the program.");
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
            System.out.println("Saving Complete.\nIt is now safe to close the program.");
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
		//NOTE: Keeping old display method just in case.
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
	public Task getTask(int key) {
		Task currentTask = null; //temporarily assigning null to the object to be returned
		for(Task t : tasks) {
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
	public void addTask(Task t) {
		tasks.add(t);
	}
	
	// Method Name: getActiveTasks
	// Parameters: none
	// Return: a task collection containing only non-completed tasks
	// Functionality: go through all tasks and add active tasks to a new task collection
	public TaskCollection getActiveTasks() {
		TaskCollection activeTasks = new TaskCollection();
		for(Task task : tasks) {
			if(!(task.isComplete())){
				activeTasks.addTask(new Task(task));;
			}
		}
		return activeTasks;
	}
	
	/*public ArrayList<Task> getTasks() {
		TaskCollection temp = new TaskCollection();
		for(Task t : this.tasks) {
			temp.addTask(new Task(t));
		}
		return temp.tasks;
	}

	public void setTasks(ArrayList<Task> tasks) {
		this.tasks = tasks;
	}*/
	
}
