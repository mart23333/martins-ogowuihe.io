package logic; // package declaration

import interfaces.Scheduler;
import model.Process;
import service.MemoryManager;
import service.Metrics;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SJF implements Scheduler {

    @Override
    public void schedule(List<model.Process> processes) {
        // memory manager handles allocation logics
        MemoryManager mem = new MemoryManager();
        // ready queue holds processes that are to be scheduled in the os
        List<model.Process> readyQueue = new ArrayList<>();
        int currentTime = 0; // shows the stimulated system time

        int completed = 0;   // number of completed processes

        while (completed < processes.size()) { // runs until all processes are completed
            for (model.Process p : processes) { // tries to allocate memory to check if the process can be entered in the ready queue
                int block = mem.allocateMemory(p.memoryRequired);
                if (block == -1) { // skip if memory can't be allocated for a process
                    System.out.println("Memory allocation failed for process " + p.pid);
                    continue;
                }

                if (!readyQueue.contains(p) && p.arrivalTime <= currentTime) { // add process in the queue if it arrives and is not already in the ready queue
                    readyQueue.add(p);
                }
                mem.deallocateMemory(block, p.memoryRequired);
            }
            // selects the process with the shortest burst time  from the ready queue
            Process shortest = readyQueue.stream()
                    .min(Comparator.comparingInt(p -> p.burstTime))
                    .orElse(null);

            if (shortest != null) { // if CPU is idle and the next process has not arrived to the queue then move forward
                if (currentTime < shortest.arrivalTime)
                    currentTime = shortest.arrivalTime;

                shortest.waitingTime = currentTime - shortest.arrivalTime;// shows waiting time and completion time
                shortest.completionTime = currentTime + shortest.burstTime;
                currentTime += shortest.burstTime; // stimulates the execution of the process

                System.out.println("Process " + shortest.pid + "Wait Time: " + shortest.waitingTime); // log the wait time of the executed process
                readyQueue.remove(shortest);
                completed++;
            } else {
                currentTime++;
            }
        }
// if all process are completed calculate and print performance matrics
        Metrics m = new Metrics();
        m.calculateMetrics(processes);
    }
}