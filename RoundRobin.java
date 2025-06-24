package logic; // package declaration
// import necessary interface and classes for scheduling ,memory and matrics calculation
import interfaces.Scheduler;
import model.Process;
import service.MemoryManager;
import service.Metrics;

import java.util.*;

public class RoundRobin implements Scheduler {
   // time slice or quantum for the Round robin scheduling algorithm
    private final int quantum;
     // constructor to set the time quantum
    public RoundRobin(int quantum){
        this.quantum = quantum;
    }

    @Override
    public void schedule(List<Process> processes) {
        MemoryManager mem = new MemoryManager(); // handles memory allocation and deallocation

        Queue<Process> queue = new LinkedList<>();// queue to manage process execution order
        int currentTime = 0; // tracks the current simulation  time
        // tracks remaining burst time and the last time each process was seen
        Map<Integer, Integer> remainingBurst = new HashMap<>();
        Map<Integer, Integer> lastSeen = new HashMap<>();
        // loop allocating memory and store burst time
        for (Process p : processes) {
            int block = mem.allocateMemory(p.memoryRequired);
            if (block == -1) {
                System.out.println("Memory allocation failed for process" + p.pid);
                continue;
            }
            remainingBurst.put(p.pid, p.burstTime);
            mem.deallocateMemory(block, p.memoryRequired);
        }
        // sort processes based on arrival time and add them to the ready queue
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        queue.addAll(processes);
       // main Round robin scheduling loop
        while (!queue.isEmpty()){
            Process p = queue.poll(); // get the next process
            int remaining = remainingBurst.get(p.pid); // get the process remaining burst time
            int execTime = Math.min(quantum, remaining); // execute for quantum or remaining time
          // wait until the process arrives
            if (currentTime < p.arrivalTime) {
                currentTime = p.arrivalTime;
            }
            currentTime += execTime; // advance time by the execution time
            remaining -= execTime; // update remaining burst time
           // first wait time calculation
            if (!lastSeen.containsKey(p.pid)) {
                p.waitingTime = currentTime - p.arrivalTime - p.burstTime + remaining;
            }
            if (remaining > 0) {
                // if not finished update burst time and requeue the process
                remainingBurst.put(p.pid, remaining);
                // if finished record completion time
                p.arrivalTime = currentTime;
                queue.add(p);
            } else {
                p.completionTime = currentTime;
            }
            // track last seen time
            lastSeen.put(p.pid, currentTime);
        }
        // calculate and print overall metrics after all processes is complete
        Metrics m = new Metrics();
        m.calculateMetrics(processes);
    }
}
