/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0071;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author 1234
 */
class TaskManager {

    static ArrayList<Task> addTask(ArrayList<Task> taskList) throws IOException {
        //Read last id 
        int id = 1;
        try {
            FileInputStream fis = new FileInputStream("lastid.txt");
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader bfr = new BufferedReader(isr);
            String line = bfr.readLine();
            if (line != null) {
                id = Integer.parseInt(line);
            }
            fis.close();
            isr.close();
            bfr.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("-----------Add Task----------");
        String requirementName = GetData.getString("Requirement Name: ");
        //Task type number from 1 to 4  
        System.out.print("Task Type: ");
        String typeTask = GetData.GetTaskType();
        //Date format dd-MM-yyyy
        String date = GetData.getDate();
        //Plan from , only 8h ---> 17h30 
        System.out.print("From: ");
        Double planFrom = GetData.getDouble(8.0, 17.5);
        //Plan To  > From and  only 8h ---> 17h30 
        System.out.print("To: ");
        Double planTo = GetData.getDouble(8.0, 17.5);
        //Name of assigned person : all character from a-zA-Z
        String assignee = GetData.getString("Assignee: ");
        //all character from a-zA-Z
        String reviewer = GetData.getString("Reviewer: ");
        int check = checkDuplicateTask(date, assignee, planFrom, planTo, taskList);

        //compare value of variable check 
        if (check == -1) {
            System.out.println("Duplicate!!");
        } else {
            Task newTask = new Task(id, requirementName, typeTask, date, planFrom, planTo, assignee, reviewer);
            taskList.add(newTask);

            //FIle output
            FileWriter out = null;

            try {
                out = new FileWriter("data.txt", true);
                //BufferedWriter writer file

                String s = id + ";" + requirementName + ";" + typeTask + ";" + date + ";" + planFrom + ";" + planTo + ";" + assignee + ";" + reviewer + "\n";
                out.write(s);

            } finally {
                if (out != null) {
                    out.close();
                }
            }

        }
        System.out.println("Add successful!");
        //Write last id 
        //FIle output
        FileWriter out = null;

        try {
            out = new FileWriter("lastid.txt");
            id += 1;
            out.write(String.valueOf(id));
        } finally {
            if (out != null) {
                out.close();
            }
        }

        return taskList;
    }

    public static void deleteTask(ArrayList<Task> taskList) throws IOException {
        taskList.clear();
        //đọc file
        try {
            FileReader fr = new FileReader("data.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                String[] array = line.split(";");

                Task kh = new Task(Integer.parseInt(array[0]), array[1], array[2], array[3], Double.parseDouble(array[4]), Double.parseDouble(array[5]), array[6], array[7]);
                taskList.add(kh);
                line = bfr.readLine();
            }
            fr.close();
            bfr.close();
        } catch (Exception ex) {
            System.out.println("List task is empty!");
        }
        
            System.out.println("-----------Del Task------------");
            int ID = GetData.getInt("ID: ");
            int index = -1;
            //loop from the first to last index of taskList
            for (int i = 0; i < taskList.size(); i++) {
                Task get = taskList.get(i);
                //compare the id of the current index to the variable ID
                if (get.TaskID == ID) {
                    index = i;
                    break;
                }
            }
            //check the value of variable index
            if (index == -1) {
                System.out.println("This ID is not exist!");
            } else {
                taskList.remove(index);
                //
                //FIle output
                try {
                    FileWriter fw = new FileWriter("data.txt");
                    PrintWriter pr = new PrintWriter(fw);
                    //BufferedWriter writer file
                    for (Task i : taskList) {
                        String s = i.getTaskID() + ";" + i.getRequirementName() + ";" + i.getTaskTypeID() + ";" + i.getDate() + ";" + i.getPlanFrom() + ";" + i.getPlanTo() + ";" + i.getAssignee() + ";" + i.getReviewer() + "\n";
                        pr.print(s);
                    }
                    fw.close();
                    pr.close();
                }catch (IOException ex) {
                    ex.printStackTrace();
                }
                
                //
                System.out.println("Delete successful!");
                System.out.println("");
            }
        }


    static void getDataTasks() {
        ArrayList<Task> taskList = new ArrayList<Task>();
        taskList.clear();
        //đọc file
        try {
            FileReader fr = new FileReader("data.txt");
            BufferedReader bfr = new BufferedReader(fr);
            String line = bfr.readLine();
            while (line != null) {
                String[] array = line.split(";");

                Task kh = new Task(Integer.parseInt(array[0]), array[1], array[2], array[3], Double.parseDouble(array[4]), Double.parseDouble(array[5]), array[6], array[7]);
                taskList.add(kh);
                line = bfr.readLine();
            }
            fr.close();
            bfr.close();
        } catch (Exception ex) {
            System.out.println("List task is empty!");
        }

        //...................
        System.out.println("----------------------------Task-------------------------------------");
        System.out.format("%-7s%-20s%-12s%-15s%-7s%-15s%-15s\n", "Id", "Name", "Task Type", "Date", "Time", "Assignee", "Reviewer");
        //loop from first to last object task in TaskList
        //check taskList is empty
        if (taskList.isEmpty()) {
            System.out.println("List task is empty!");
            return;
        } else {
            for (Task task : taskList) {
                System.out.println(task.toString());
            }
        }
    }

    private static int checkDuplicateTask(String date, String assignee, Double planFrom, Double planTo, ArrayList<Task> taskList) {
        int count = 0;
        //loop from the first to last index of taskList
        for (int i = 0; i < taskList.size(); i++) {
            Task get = taskList.get(i);
            //compare date end assignee of the current index to the variable date and assignee
            if (date.equals(get.date) && assignee.equalsIgnoreCase(get.Assignee)) {
                //compare time of of the current index to the variable planFrom and planTo
                if ((planTo > get.planFrom && planTo < get.planTo) || (planFrom > get.planFrom && planFrom < get.planTo)) {
                    count = -1;
                } else {
                    count = 1;
                }
            }
        }
        return count;
    }

}
