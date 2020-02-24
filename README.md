# Squad22

Repository: https://github.com/TamaraWallace/Squad22

All code is contained in the 233GroupProject folder, which is formatted as an Eclipse project.

After downloading the repository, the TextBasedApp can be run as a Java Application directly from an Eclipse IDE. Alternatively, it can be run from the command line/terminal by doing the following:
  1. Move the *tasks.txt* and *users.txt* files from the 233GroupProject folder into the src folder contained within
  2. After navigating the the src directory, enter *javac TextBasedApp.java* to compile the file
  3. enter *java TextBasedApp* to launch the program
  
Once the program is launched, the user will be greeted in the startMenu and be prompted to Login, Create New Account, or Quit.
- Existing users should select *Login*, enter their username (case insensitive) and enter their password (case sensitive)
- New users should select *Create New Account*, enter their desired username, and enter their desired password.

Onced logged in (either with a new or existing account), the user can:
  1. View All Tasks --> displays all of the user's tasks, active and completed
  2. Add a New Task --> create a new task
  3. Select a Task  --> select one of their active tasks which they can then complete and edit (editTask is NOT functional)
  4. Logout         --> saves all changes and returns the user to the startMenu 
  5. Quit           --> saves all changes and exits the program 
