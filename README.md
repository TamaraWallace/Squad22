# Taskilla-05-22

Repository: https://github.com/TamaraWallace/Taskilla-05-22

All code is contained in the 233GroupProject folder, which is formatted as an Eclipse project.

  1. Import 233GroupProject as a new Eclipse project.
  2. Configure the project's build path with **Java SE 8** and **JavaFX**.
  3. Create a new Run Configuration for the project with main class **Application.GuiBasedApp**.

At this point, the program will launch when you *Run GuiBasedApp*.
Note: Depending on which version of JavaFX is installed, there may be some warnings (Specifically "WARNING: Loading FXML document with JavaFX API of version 11.0.1 by JavaFX runtime of version 8.0.181") but the program should launch regardless.

Once the program is launched, the user will find themselves in our Login Screen.
To explore the App, we recommend logging in using *Username*: Test and *Password*: test. Otherwise, feel free to user our "Create New Account" feature and create your own account.

Once the user has logged in (or created a new account) they are brought to the HomeScreen.
The HomeScreen displays 3 of their Active Tasks, has a button to Display All Active Tasks, and another to Add a New Task.

Login Screen:
  - Has TextFields for *Username* and *Password*.
  - Has a **Create New Account** Button for creating a new account
  - Has a **Login** Button to log in
  - When the **Create New Account** Button is pressed, the Create User Screen is launched.
  - When the **Login** Button is pressed, the values of the *Username* & *Password* and validated against the current collection of Users. If the inputed *Username* matches an existing User's username, and the *Password* matches their password, that User is logged in and the Home Screen is launched. If the inputted *Username* & *Password* are invalid, the *Password* TextField is highlighted and a Label says "Password Invalid". After 4 unsuccessful login attempts, the program exits.
  
Create User Screen:
  - Has TextFields for *Username*, *Email*, *Password*, and *Confirm Password*
  - Has a **Back** Button to return to the Login Screen
  - Has a **Create New Account** Button to create a new account
  - When the **Back** button is pressed, the Login Screen is launched.
  - When the **Create New Account** button is pressed, the values of all the TextFields are validated. The *Username* must be unique (i.e. there can't already exist a User with the same name), and the *Password* and *Confirm Password* must match and can't both be empty. No other validation is currently implemented. If all fields are valid, a new User is created with the given values and the Home Screen is launched.
