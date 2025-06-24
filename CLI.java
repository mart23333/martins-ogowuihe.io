package service;// package declaration
// import statements

import model.Process;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner; //  //
// CLI class to handle command line communication
public class CLI {
// reads input from user
    private final Scanner scanner = new Scanner(System.in);
// method to get user input and return the list of process object
    public List<Process> getUserInput() {
        List<Process> processes = new ArrayList<>();
        System.out.print("Please Enter number of processes: ");
        var s = scanner.nextInt();
// loop to collect data for each process
        for (int i = 0; i < s; i++){
            System.out.println("Process " + (i + 1));
            System.out.print("Arrival Time: "); // collects data for arrival time
            int arrival = scanner.nextInt();
            System.out.print("Burst Time: "); // collects data for burst time
            int burst = scanner.nextInt();
            System.out.print("Memory Required: "); // collects data for memory required
            int mem = scanner.nextInt();
            processes.add(new Process(i+1, arrival, burst, mem));
        }
        return  processes; // return the list of collected processes
    }

}
