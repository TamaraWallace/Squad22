# Taskilla-05-22

Last Updated: March 28th, 2020

Repository: https://github.com/TamaraWallace/Taskilla-05-22

======================================================================================

Welcome to Taskilla! This app aims to help users manage their busy lives and keep track of what they need to accomplish.

The program is composed of Users & Tasks. It allows for the creation of new Users and new Tasks and allows a logged in User to manage their tasks by viewing them, completing them, and adding new ones.

======================================================================================

Please start by downloading the code in the Repository.


Taskilla is formatted as an Eclipse project.
  1. Import 233GroupProject as a new Eclipse project.
  2. Configure the project's build path with **Java SE 8** and **JavaFX**.
  3. Create a new Run Configuration for the project with main class **Application.GuiBasedApp**.
  4. The program will launch when you **Run GuiBasedApp**.
  
Once the program is launched, the user will find themselves at our Login Screen. To explore the App, we recommend logging in using ***Username: Test*** and ***Password: test***. Otherwise, feel free to create your own account.

======================================================================================

The features and functionality of each of our Screens can be found below.

Login Screen:
  - Has TextFields for *Username* and *Password*.
  - Has a *Create New Account* Button for creating a new account
  - Has a *Login* Button to log in
  - When the *Create New Account* Button is pressed, the Create User Screen is launched.
  - When the *Login* Button is pressed, the values of the *Username* & *Password* are validated against the current collection of Users. If a User exists, whose username & password match the inputed values, then that User is logged in and the Home Screen is launched. Otherwise, the *Password* TextField is highlighted and a Label says "Password Invalid". After 4 unsuccessful login attempts, the program exits.
  
Create User Screen:
  - Has TextFields for *Username*, *Email*, *Password*, and *Confirm Password*
  - Has a *Back* Button to return to the Login Screen
  - Has a *Create New Account* Button to create a new account
  - When the *Back* button is pressed, the Login Screen is launched.
  - When the *Create New Account* Button is pressed, the values of all the TextFields are validated. The *Username* must be unique (i.e. there can't already exist a User with the same name) and the *Password* and *Confirm Password* must match and can't be empty. No other validation is implemented at present. If all fields are valid, a new User is created with the given values and the Home Screen is launched.
  
Home Screen:
  - Has three RadioButtons for three of the User's current tasks (the tasks are sorted to display upcoming tasks based on Due Date). If the User has fewer than three tasks, the buttons' Text reads "Add Tasks!"
  - Has a *View All* Button to display all tasks
  - Has an *Add New* Button to add a new task
  - Has a *Profile* Button to allow a User to access Settings, Log Out, or Close the program (exit).
  - Has an *Email* Button to allow a User to send task reminders to their email.
  - When the *View All* Button is pressed, the Display Tasks Screen is launched.
  - When the *Add New* Button is pressed, the Add Task screen is launched.
  - When one of the three radio buttons is pressed, assuming it is populated with a task, the Task Menu Screen is launched with the given task.
  - When the *Profile* Button is pressed, if a User chooses to access Settings, the Settings screen is launched. Otherwise, if Log Out is selected, the User is brought back to the Login screen. If Close if selected, the program closes. 
  - When the *Email* Button is pressed, the User's current and completed tasks are sent to their personal email as a reminder. 
  
Display Tasks Screen:
  - Has a ListView populated with all of the User's active tasks.
  - Has a *Back* Button to return the User to the Home Screen
  - Has a *Select* Button to launch the Task Menu screen with the currently selected task.
  - Has a *Complete* Button with no functionality
  - When the *Back* Button is pressed, the Home Screen is launched.
  - When the *Select* Button is pressed, if a Task from the ListView is selected, the Task Menu is launched with the given Task.
  
Add Task Screen:
  - Has a TextFields for *Name* and *Notes*
  - Has a DatePicker for *Date*
  - Has a *Back* Button to return the User to the HomeScreen
  - Has an *Add* Button to create a new Task.
  - When the *Back* Button is pressed, the Home Screen is launched.
  - When the *Add* Button is pressed, a new Task is created using the values of *Name*, *Notes*, and *Date*. The *Notes* field is optional, the other two are mandatory. If a new Task is successfully created, it is added to that User's tasks, and the Home Screen is launched.
  
Task Menu Screen:
  - Has Text for *Task Name*, *Task Due Date*
  - Has a TextArea for *Task Notes*
  - Has a *Back* Button to launch the previous Screen
  - Has a *Complete* Button to complete the Task
  - Has an *Edit* Button (looks like pen) that launches the Edit Task Screen when pressed.
  - Has a *Delete* Button that removes the respective task from the User's collection of tasks. 
  - When the *Back* Button is pressed, it will launch the previous screen
  - When the *Complete* Button is pressed, the Task will be marked as complete, and the Home Screen is launched.

Edit Task Screen:
  - Has TextFields for *Name* and *Notes*
  - Has a DatePicker for *Date*
  - Has a *Back* Button to return the User to the Task Menu Screen.
  - Has a *Done* Button that updates the selected task.
  - When the *Back* Button is pressed, the Task Menu is launched for that particular task.
  - When the *Done* Button is pressed, the current task is updated using the values of *Name*, *Notes*, and *Date*. The *Notes* field is optional, the other two are mandatory. Once the task is updated, the Home Screen is launched.

Settings Screen:
  - Has TextFields for *Username* and *Email*
  - Has a *Back* Button to return to the Home Screen. 
  - Has an *Update* Button to update the User's profile. 
  - When the *Update* Button is pressed, the User's profile data is updated. The *Email* field is optional, however, the *Username* field must be filled in order to organize and manage the User's information. 
  - When the *Back* Button is pressed, the Home Screen is launched. 


===================================================================================
File Input/Output:

users.txt
_________
  - Each line of the file contains information regarding a User's profile.
  - Each User has an auto-generated userID, username, password, and email.

tasks.txt
_________
  - Each line stores a Taskilla User's task. 
  - Tasks are organized and managed based on the Task Name, an auto-generated taskID, the User's userID, the description of the task, the completion status (boolean), and the due date.


