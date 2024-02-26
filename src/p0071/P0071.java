/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0071;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author 1234
 */
public class P0071 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        ArrayList<Task> taskList = new ArrayList<>();
        
        while (true) {
      //1. Display a menu and ask users to select an option.
            //Display menu
            displayData.displayMenu();
            //Get user's choice
            System.out.print("Your choice: ");
            int choice = GetData.getChoice(1, 4);
      //2. Perform function based on the selected option.
            switch (choice) {
        //2.1 Add Task
                case 1:
                    taskList = TaskManager.addTask(taskList);
                    break;
        //2.2 delete task
                case 2:
                    TaskManager.deleteTask(taskList);
                    break;
        //2.3 displayData task
                case 3:
                    TaskManager.getDataTasks();
                    break;
        //2.4 Exit
                case 4:
                    System.exit(0);
                    break;
            }

        }

    }

}
