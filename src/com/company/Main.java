package com.company;

/**
 * Created by Ramzan Dieze on 01/07/2016.
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Hotel rooms = new Hotel();
        Queue mq = new Queue();
        Scanner sc = new Scanner(System.in);
        rooms.initialise();
// mq.initialise();
        while (true) {
//Display the options for the user to choose
            System.out.println();
            System.out.println();
            System.out.println("                 Dieze Hotel");
            System.out.println("            <-------------------->");
            System.out.println("=============================================");
            System.out.println("A : Add a customer ");
            System.out.println("V : View all the rooms ");
            System.out.println("E : Display empty rooms ");
            System.out.println("D : Delete customer ");
            System.out.println("F : Find room from customer ");
            System.out.println("O : View rooms Ordered alphabetically ");
            System.out.println("S : Store program details to text file ");
            System.out.println("L : Load program data back from the file ");
            System.out.println("3 : Display the names of the first 3 customers who have been allocated to a room");
            System.out.println("=============================================");
            String command = sc.next();
//making users command to lowwer case
            String nCmd = command.toLowerCase();
//navigating according to the users command
            switch (nCmd) {
                case "a":
                    rooms.addCust();
                    break;
                case "v":
                    rooms.displayRooms();
                    break;
                case "e":
                    rooms.viewEmptyRooms();
                    break;
                case "d":
                    rooms.deleteCustomer();
                    break;
                case "f":
                    rooms.findCustomer();
                    break;
                case "o":
                    rooms.displayCustomersOrderly();
                    break;
                case "s":
                    rooms.saveRooms();
                    break;
                case "l":
                    rooms.loadRoams();
                    break;
                case "3":
                    mq.displayqueue();
                    break;
                default:
                    System.out.println("Invalid selection.Please try again.");
            }
        }
    }
}

class Queue {
    int qitems[] = new int[7];
    static String[] x = new String[7];
    int front = 0, end = 0;

    //adding customers to the queue
    void addqueue(String name, String surName, String id) {
//if display method reach the last element
        if (front == 7) {
            front = 0;
            end = 0;
        }
//until the queue ends
        if (end < 7) {
            x[end] = name + " " + surName + " " + id;
        }
//after getting the end element reset the end to the beging
        else {
            System.out.println("Your queue is already fulled, removing the old items.");
            end = 0;
            x[end] = name + " " + surName + " " + id;
        }
        end++;
    }

    void displayqueue() {
        boolean flag = true;
        if (front != 7) {
//to track the last customer on the queue
            if (front < 6) {
//displaying the 1st three customers
                for (int look = 0; look < 3; look++) {
                    if (x[front] != null) {
                        System.out.println(x[front] + " ");
                        front++;
                        flag = false;
                    }
                }
            } else {
                System.out.println(" " + x[front]);
                front++;
            }
        } else {
            System.out.println("Queue is empty");
            flag = false;
        }
//if the queue is empty
        if (flag) {
            System.out.println("Queue is empty");
        }

        System.out.println();
        System.out.println();

    }
}
