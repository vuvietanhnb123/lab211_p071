/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package p0071;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author 1234
 */
class GetData {

    public static int getChoice(int min, int max) {
        Scanner sc = new Scanner(System.in);
        int number;
        //loop until enter the correct choice in range [min, max]
        while (true) {
            try {
                number = Integer.parseInt(sc.nextLine().trim());
                //compare choice number with max and min value
                if (number <= max && number >= min) {
                    break;
                } else {
                    System.err.print("Please input value in rage [" + min + ", " + max + "]: ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Only number !");
            }
        }
        return number;
    }

    public static int getInt(String mess) {
        System.out.print(mess);
        Scanner sc = new Scanner(System.in);
        //loop until enter the correct integer
        while (true) {
            try {
                int number = Integer.parseInt(sc.nextLine().trim());
                //compare number to -1
                if (number > -1) {
                    return number;
                } else {
                    System.err.println("Must be enter positive");
                }
            } catch (NumberFormatException e) {
                System.err.print("Please re- Enter: ");
            }
        }
    }

    public static String getString(String mess) {
        System.out.print(mess);
        Scanner sc = new Scanner(System.in);
        //loop until string is not empty
        while (true) {
            String str = sc.nextLine().trim();
            //check string empty
            if (str.isEmpty()) {
                System.err.println("It cannot be empty. Re-enter: ");
            } else {
                return str;
            }
        }

    }

    public static String getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        sdf.setLenient(false);
        //loop until enter the correct date format dd-MM-yyyy
        while (true) {
            try {
                String str = getString("Date: ");
                Date date = sdf.parse(str);
                return new SimpleDateFormat("dd-MM-yyyy").format(date);
            } catch (ParseException e) {
                System.err.println("Re-enter the format dd-MM-yyyy: ");
            }
        }
    }

    public static String GetTaskType() {
        int tasktypeID = getChoice(1, 4);
        String result = "";
        switch (tasktypeID) {
            case 1:
                result = "Code";
                break;
            case 2:
                result = "Test";
                break;
            case 3:
                result = "Design";
                break;
            case 4:
                result = "Review";
                break;
        }
        return result;
    }

    public static double getDouble(double min, double max) {
        Scanner sc = new Scanner(System.in);
        double number;
        String str;
        //loop until enter the correct choice in range [min,max]
        while (true) {
            try {
                //\d: accept input be a digit from 0-9
                //\.: after digit is a dot
                //([5]|[0])): after a dot just accpet digit 5 or 0
                str = getStringRegex("^(\\d+\\.([5]|[0]))$");
                number = Double.parseDouble(str);
                //compare choice number with max and min value
                if (number <= max && number >= min) {
                    break;
                } else {
                    System.err.print("Please input value in rage [" + min + ", " + max + "]: ");
                }
            } catch (NumberFormatException e) {
                System.err.println("Please re- Enter: ");
            }
        }
        return number;
    }

    public static String getStringRegex(String regex) {
        Scanner sc = new Scanner(System.in);
        String str;
        //loop until enter the correct string by regex
        while (true) {
            str = sc.nextLine();
            //Check input fomat regex
            if (str.matches(regex)) {
                break;
            } else {
                System.err.print("Invalid Input, please try again: ");
            }
        }
        return str;
    }

}
