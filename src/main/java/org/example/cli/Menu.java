package org.example.cli;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private final List<Command> commands;
    private final Scanner scanner;

    public Menu(List<Command> commands, Scanner scanner) {
        this.commands = commands;
        this.scanner = scanner;
    }

    public void show() {
        while (true) {
            System.out.println("\n=== PHONE DIRECTORY ===");
            for (int i = 0; i < commands.size(); i++) {
                System.out.println((i + 1) + ". " + commands.get(i).getTitle());
            }
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                if (choice == 0) {
                    System.out.println("Goodbye!");
                    break;
                }

                if (choice > 0 && choice <= commands.size()) {
                    commands.get(choice - 1).execute();
                } else {
                    System.out.println("Invalid option!");
                }
            } catch (Exception e) {
                System.out.println("Invalid input! Please enter a number.");
                scanner.nextLine(); // clear invalid input
            }
        }
    }
}
/*
import org.example.cli.Get.GetAllDepartments;
import org.example.cli.Get.GetDepartmentById;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    private static Scanner scn = new Scanner(System.in);
    private static Command[] commands = new Command[]{
            new GetAllDepartments(),
            new GetDepartmentById(),
    };

    public static void run(){
        while (true){
            System.out.println();
            for (int i = 1; i <= commands.length; i++) {
                System.out.println(i + " " + commands[i - 1].getCommandName());
            }
            int inputCommand = 0;
            try {
                inputCommand = scn.nextInt();
            } catch (InputMismatchException ime){
                System.out.println("wrong command");
                continue;
            }

            if(inputCommand == -1){
                System.out.println("Program exit");
                return;
            }

            if(inputCommand > commands.length){
                System.out.println("Wrong command");
                continue;
            }

            commands[inputCommand - 1].execute();

        }

    }
}
*/