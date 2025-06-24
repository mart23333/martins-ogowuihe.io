// import necessary classes and interface used for scheduling algorithm
// user input handling process modeling and utility functions
import interfaces.Scheduler;
import logic.FCFS;
import logic.RoundRobin;
import logic.SJF;
import model.Process;
import service.CLI;

import java.util.List;
import java.util.Scanner; //

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
// main class to run the scheduling simulation
public class Main {
    public static void main(String[] args) {

        CLI cli = new CLI(); // CLI to get user input
        List<Process> processes = cli.getUserInput(); // get process  list from user

        Scanner scanner = new Scanner(System.in); // initialize scanner for algorithm selection
        System.out.print("Please Choose Scheduling Algorithm (1.FCFS 2.SJF 3.RoundRobin): ");
        int choice = scanner.nextInt(); // read users algorithm choice
// scheduling algorithm based on users choice
        Scheduler scheduler = switch (choice) {
            case 2 -> new SJF(); // uses sjf if user chooses 2
            case 3 -> new RoundRobin(2); // uses    Round robin if user chooses 3
            default -> new FCFS(); // uses FCFS if user fails to choose 1 or 2
        };
        // run chosen  scheduling algorithm
        scheduler.schedule(processes);
    }
}