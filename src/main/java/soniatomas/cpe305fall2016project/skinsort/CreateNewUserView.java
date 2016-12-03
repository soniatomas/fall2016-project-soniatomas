package main.java.soniatomas.cpe305fall2016project.skinsort;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

public class CreateNewUserView extends View implements Observer {
  private CreateNewUserOperation newUserOperation;
  private boolean status;
  private Scanner input;

  public CreateNewUserView(Scanner scanner) {
    this.newUserOperation = new CreateNewUserOperation();
    newUserOperation.addObserver(this);
    this.input = scanner;
    this.status = false;
  }
  
  public void update(Observable obs, Object obj) {
    String status = (String)newUserOperation.getVariables().get("status");
    if (status.equals("FAILURE"))
    {
      System.out.println("Error: There is an account associted with the inputted email.");
      System.out.println("Unable to create new user.");
      this.status = false;
    }
    else if (status.equals("DATABASE_ERROR")) {
      System.out.println("Error connecting to the database. Unable to create new user.");
      this.status = false;
    }
    else this.status = true;
  }

  public void display() {
    String email = null;
    String password = null;
    String firstName = null;
    String lastName = null;
    
    do {
      System.out.println();
      System.out.println("\nCREATE NEW USER");
      System.out.print("First Name: ");
      if (input.hasNextLine()) {
        firstName = input.nextLine().trim();
      }
      System.out.print("Last Name: ");
      if (input.hasNextLine()) {
        lastName = input.nextLine().trim();
      }
      System.out.print("Email: ");
      if (input.hasNextLine()) {
        email = input.nextLine().trim();
      }
      System.out.print("Password: ");
      if (input.hasNextLine()) {
        password = input.nextLine().trim();
      }
      HashMap<String, String> parameters = new HashMap<String, String>();
      parameters.put("email", email);
      parameters.put("password", password);
      parameters.put("first_name", firstName);
      parameters.put("last_name", lastName);
      newUserOperation.execute(parameters);
    } while (!status);
  }
}
