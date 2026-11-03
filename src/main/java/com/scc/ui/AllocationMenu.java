package com.scc.ui;

import com.scc.dao.AllocationDao;
import com.scc.model.AllocationEntity;

import java.util.List;
import java.util.Scanner;

public class AllocationMenu {
    private final AllocationDao allocationDao = new AllocationDao();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("==================================================");
            System.out.println("         STUDENT DORMITORY BED ALLOCATION         ");
            System.out.println("==================================================");
            System.out.println("[1] Add New Allocation");
            System.out.println("[2] View All Allocations");
            System.out.println("[3] Exit System");
            System.out.println("==================================================");
            System.out.print("Enter choice: ");

            int choice;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
                System.out.println("\nInvalid choice. Please enter a valid option number.\n");
                continue;
            }

            System.out.println();

            switch (choice) {
                case 1:
                    System.out.println("--- ADD NEW BED ALLOCATION ---");
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Building Name: ");
                    String building = scanner.nextLine();

                    System.out.print("Enter Room Number: ");
                    String room = scanner.nextLine();

                    System.out.print("Enter Monthly Rent: ");
                    double rent = 0.0;
                    if (scanner.hasNextDouble()) {
                        rent = scanner.nextDouble();
                        scanner.nextLine();
                    } else {
                        scanner.nextLine();
                    }

                    allocationDao.insertAllocation(name, building, room, rent);
                    System.out.println("\nSUCCESS: Dorm bed allocated successfully!\n");
                    break;

                case 2:
                    System.out.println("=======================================================================");
                    System.out.println("                      DORMITORY BED ALLOCATIONS                        ");
                    System.out.println("=======================================================================");
                    System.out.println("+----+--------------------+-----------------+---------+--------------+");
                    System.out.println("| ID | STUDENT NAME       | BUILDING        | ROOM NO | MONTHLY RENT |");
                    System.out.println("+----+--------------------+-----------------+---------+--------------+");

                    List<AllocationEntity> allocations = allocationDao.getAllAllocations();
                    for (AllocationEntity a : allocations) {
                        System.out.printf("| %-2d | %-18s | %-15s | %-7s | %-12.2f |\n",
                                a.id(), a.studentName(), a.building(), a.roomNo(), a.monthlyRent());
                    }

                    System.out.println("+----+--------------------+-----------------+---------+--------------+");
                    System.out.println("Total Records Displayed: " + allocations.size());
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Exiting system... Thank you!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid option selected. Please choose 1, 2, or 3.\n");
                    break;
            }
        }
    }
}