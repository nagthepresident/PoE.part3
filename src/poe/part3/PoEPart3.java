/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package poe.part3;

import java.util.Scanner;
import javax.swing.JOptionPane;

/**
 *
 * @author RC_Student_lab
 */
public class PoEPart3 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        UserLogin userLogin = new UserLogin();
        TaskManager taskManager = new TaskManager();
        Scanner input = new Scanner(System.in);

        //  Sir please note . the ristration part is a scanner then the log in part is JOption . so if u could kindly lock to the log in part on desktop 
        //
        System.out.println("Enter First Name:");
        userLogin.firstName = input.next();
        System.out.println("Enter Last Name:");
        userLogin.lastName = input.next();
        System.out.println("Enter Username:");
        userLogin.username = input.next();
        System.out.println("Enter Password:");
        userLogin.password = input.next();

        System.out.println(userLogin.registerUser());
        while (!userLogin.checkPasswordComplexity() || !userLogin.checkUsername()) {
            System.out.println("Try to register again!!!!!");
            System.out.println("Enter Username:");
            userLogin.username = input.next();
            System.out.println("Enter Password:");
            userLogin.password = input.next();
            System.out.println(userLogin.registerUser());
        }

        // User login
        System.out.println("Log in");
        System.out.println("Enter Username:");
        userLogin.enteredUsername = input.next();
        System.out.println("Enter Password:");
        userLogin.enteredPassword = input.next();
        System.out.println(userLogin.returnLoginStatus());
        
        System.out.println("Sir please note . the ristration part is a scanner then the log in part is JOption . so if u could kindly lock to the log in part on desktop");

        while (!userLogin.loginUser()) {
            System.out.println("Try to Login again ..........");
            System.out.println("Enter Username:");
            userLogin.enteredUsername = input.next();
            System.out.println("Enter Password:");
            userLogin.enteredPassword = input.next();
            System.out.println(userLogin.returnLoginStatus());
        }

        // If login is successful, display the main menu
        if (userLogin.loginUser()) {
            JOptionPane.showMessageDialog(null, "Welcome To EasyKanban");
            int mainChoice;
            do {
                taskManager.input = JOptionPane.showInputDialog("Choose an option:\n1. Add tasks\n2. Show report\n3. Quit");
                mainChoice = Integer.parseInt(taskManager.input);

                switch (mainChoice) {
                    case 1:
                        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));
                        int totalHours = 0;

                        for (int i = 0; i < numTasks; i++) {
                            String taskName = JOptionPane.showInputDialog("Enter task name:");
                            String taskDescription = JOptionPane.showInputDialog("Enter task description:");
                            String developerFirstName = JOptionPane.showInputDialog("Enter developer's first name:");
                            String developerLastName = JOptionPane.showInputDialog("Enter developer's last name:");
                            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration:"));
                            String taskID = taskManager.createTaskID(taskName, i, developerLastName);
                            String taskStatus = "";

                            int statusOption = Integer.parseInt(JOptionPane.showInputDialog("Please choose the Status of this task from the three options.\n1.To Do\n2.Doing\n3.Done"));
                            switch (statusOption) {
                                case 1:
                                    taskStatus = "To Do";
                                    break;
                                case 2:
                                    taskStatus = "Doing";
                                    break;
                                case 3:
                                    taskStatus = "Done";
                                    break;
                            }

                            taskManager.addTask(taskName, taskDescription, developerFirstName + " " + developerLastName, taskID, taskDuration, taskStatus);
                            String taskDetails = taskManager.printTaskDetails(taskStatus, developerFirstName, developerLastName, i, taskName, taskDescription, taskID, taskDuration);
                            JOptionPane.showMessageDialog(null, taskDetails);
                            JOptionPane.showMessageDialog(null, "Task successfully captured."); // Task captured message
                            totalHours += taskDuration;
                        }

                        JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
                        break;

                    case 2:
                        int reportChoice;
                        do {
                            reportChoice = Integer.parseInt(JOptionPane.showInputDialog("Show Report Options:\n1. Display all tasks\n2. Display done tasks\n3. Display longest task\n4. Search task by name\n5. Search tasks by developer\n6. Delete task\n7. Go back"));

                            switch (reportChoice) {
                                case 1:
                                    taskManager.showTaskReport();
                                    break;
                                case 2:
                                    taskManager.displayDoneTasks();
                                    break;
                                case 3:
                                    taskManager.displayLongestTask();
                                    break;
                                case 4:
                                    String searchTaskName = JOptionPane.showInputDialog("Enter task name to search:");
                                    taskManager.searchTaskByName(searchTaskName);
                                    break;
                                case 5:
                                    String searchDeveloper = JOptionPane.showInputDialog("Enter developer name to search tasks:");
                                    taskManager.searchTasksByDeveloper(searchDeveloper);
                                    break;
                                case 6:
                                    String deleteTaskName = JOptionPane.showInputDialog("Enter task name to delete:");
                                    taskManager.deleteTask(deleteTaskName);
                                    break;
                                case 7:
                                    JOptionPane.showMessageDialog(null, "Returning to main menu.");
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                                    break;
                            }
                        } while (reportChoice != 7);
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Exiting the application.");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                        break;
                }
            } while (mainChoice != 3);
        }
    }

    }
    

