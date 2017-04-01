package com.company;

/**
 * Created by Ramzan Dieze on 01/07/2016.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Hotel {
    Scanner input;
    Scanner sc = new Scanner(System.in);
    static Rooms[] myHotel = new Rooms[10];
    File file = new File("src\\ro.txt");
    PrintWriter pw = null;
    FileWriter fw = null;
    Queue mq = new Queue();

    // adding the customer to the room
    public void addCust() {
        input = new Scanner(System.in);
        System.out.println("Enter room number: ");
        String sNmb;
// validating the users command
        try {
            sNmb = sc.next();
            int nmb = Integer.parseInt(sNmb) - 1;
// validate the room number
            if (!(nmb < 10 && nmb >= 0)) {
                System.out.println("Enter valid room number");
                addCust();
            } else {
// checking whether room is empty
                if (myHotel[nmb].getName().equals("e")) {
                    System.out.println("Enter customer sur name : ");
                    String sirName = sc.next();
                    System.out.println("Enter customer name: ");
                    String name = sc.next();
                    System.out.println("Enter customer id: ");
                    String id = sc.next();
                    myHotel[nmb].setName(sirName);
                    myHotel[nmb].setSurName(name);
                    myHotel[nmb].setId(id);
                    mq.addqueue(sirName, name, id);
                } else {
                    System.out.println("That room is already occupied");
                    System.out.println("Do you want continue?");
                    String ans = sc.next();
                    if (ans.equalsIgnoreCase("yes"))
                        addCust();
                }
            }
        } catch (Exception e) {
            System.out.println("Enter valid room numbers");
            addCust();
        }
    }

    // display all empty rooms
    public void viewEmptyRooms() {
        boolean flag = true;
        for (int x = 0; x < myHotel.length; x++) {
            if (myHotel[x].getName().equals("e")) {
                System.out.println("room " + (x + 1) + " is empty");
                flag = false;
            }
        }
        if (flag) {
            System.out.println("No rooms are empty right now.");
        }
    }

    //display customer details who is in the room
    public void displayRooms() {
        boolean flag = true;
        for (int x = 0; x < myHotel.length; x++) {
            if (!(myHotel[x].getName().equals("e"))) {
                System.out.println("room " + (x + 1) + " occupied by " + myHotel[x].getName());
                flag = false;
            }
        }
        if (flag) {
            System.out.println("All the rooms are empty.");
        }
    }

    // delete customer details from the room
    public void deleteCustomer() {
        System.out.println("Enter the room number to delete customer ");
        String room = sc.next();
        try {
// validating the user input
            int rm = Integer.parseInt(room) - 1;
// validate the room number
            if (rm >= 0 && rm < 10) {
                if (!(myHotel[rm].getName().equals("e"))) {
                    myHotel[rm].setName("e");
                    System.out.println("Deleted successfully");
                } else
                    System.out.println("This room is empty");
            } else {
                System.out.println(" ");
                System.out.println("The number you enter is invlid please try again. ");
                System.out.println(" ");
                System.out.println("Do you want continue?");
                String ans = sc.next();
                if (ans.equalsIgnoreCase("yes"))
                    deleteCustomer();
            }
        } catch (Exception e) {
            System.out.println("You have enterned invalid input. Please try again. ");
            System.out.println(" ");
            System.out.println("Do you want continue?");
            String ans = sc.next();
            if (ans.equalsIgnoreCase("yes"))
                deleteCustomer();
        }
    }

    // to find the customer details and room number by their name
    public void findCustomer() {
        boolean flag = false;
        System.out.println("Enter the customer name: ");
        String sName = sc.next();
        int i = 0;
        try {
            for (int j = 0; j < myHotel.length; j++) {
                String name = myHotel[i].getName();
                String surName = myHotel[i].getSurName();
                i++;
                String name1 = surName + " " + name;
                if ((sName.equalsIgnoreCase(name)) ||
                        sName.equalsIgnoreCase(surName)
                        || sName.equalsIgnoreCase(name1)) {
                    System.out.println(name + " is in room " + (j + 1));
                    flag = true;
                }
            }
            if ((!(flag))) {
                System.out.println(sName + " is not found");
            }
        } catch (Exception e) {
            System.out.println("Error.Please Try again.");
        }
    }

    // to display customers name alphabetical order
    public void displayCustomersOrderly() {
        String[] dupRooms = new String[10];
        int[] roomNmb = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        try {
            for (int j = 0; j < myHotel.length; j++) {
                String name = myHotel[j].getName();
                String surName = myHotel[j].getSurName();
                dupRooms[j] = name + " " + surName;
            }
        } catch (Exception e) {
        }
        for (int i = 0; i < (myHotel.length - 1); i++) {
            for (int j = 0; j < ((myHotel.length - 1)); j++) {
                if (dupRooms[j].compareTo(dupRooms[j + 1]) > 0) {
                    String temp = dupRooms[j];
                    int temp1 = roomNmb[j];
                    dupRooms[j] = dupRooms[j + 1];
                    roomNmb[j] = roomNmb[j + 1];
                    dupRooms[j + 1] = temp;
                    roomNmb[j + 1] = temp1;
                }
            }
        }
        for (int i = 0; i < myHotel.length; i++) {
            if (!(dupRooms[i].equals("e null")))
                System.out.println("Room 0" + roomNmb[i] + " : " +
                        dupRooms[i]);
        }
        System.out.println();
    }

    // save current customer details to a plain text file
    public void saveRooms() {
// to handle the exception that might cause
        try {
            fw = new FileWriter(file); // to open the file in append  mode
            pw = new PrintWriter(fw, true); // second parameter is used to auto flush
            for (int i = 0; i < myHotel.length; i++) {
                if (myHotel[i].getName() == null)
                    pw.println("e");
                else
                    pw.println(myHotel[i].getName() + " " +
                            myHotel[i].getSurName() + " " + myHotel[i].getId());
            }
            System.out.println("Successfully written to the file.");
            fw.close();
            pw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "File not found,Please check the file" + file.getName());
        }
    }

    // retrieve back customer details from the text file
    public void loadRoams() {
        Scanner scx;
        try {
            scx = new Scanner(file);
            int j = 0;
            try {
                while (true) {
                    String namex = scx.nextLine();
                    String[] data = namex.split(" ", -1);
                    myHotel[j].setName(data[0]);
                    myHotel[j].setSurName(data[1]);
                    myHotel[j].setId(data[2]);
                    j++;
                }
            } catch (Exception e) {
            }
            System.out.println("File loaded to the array successfully");
        } catch (FileNotFoundException e1) {
            System.out.println("File not found ");
        }
    }

    public void initialise() {
// initializing the array
        myHotel[0] = new Rooms();
        myHotel[1] = new Rooms();
        myHotel[2] = new Rooms();
        myHotel[3] = new Rooms();
        myHotel[4] = new Rooms();
        myHotel[5] = new Rooms();
        myHotel[6] = new Rooms();
        myHotel[7] = new Rooms();
        myHotel[8] = new Rooms();
        myHotel[9] = new Rooms();
        for (int x = 0; x < myHotel.length; x++)
            myHotel[x].setName("e");
    }
}