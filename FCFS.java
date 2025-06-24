package logic; // package declaration
// import all necessary interface and classes for the scheduling and os operation
import interfaces.Scheduler;
import model.Process;
import service.MemoryManager;
import service.Metrics;

import java.util.Comparator;
import java.util.List;

public class FCFS implements Scheduler {
// implement the scheduling method defined in the scheduler interface
    @Override
    public void schedule(List<Process> processes) {
        MemoryManager mem = new MemoryManager(); // manages memory allocation and deallocation
// sort the process list based on arrival time to follow standard FCFS logic
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime));
        int currentTime = 0; // shows current stimulation time
// goes through each process in order of arrival
        for (Process p : processes){
            // tries to allocate memory for the process
            var block = mem.allocateMemory(p.memoryRequired);
            if ( block == -1 ){
                // skip the process if the memory allocation fails
                System.out.println("Memory allocation failed for Process " + p.pid);
                continue;
            }
// if the process arrives after arrival time move  to the next time
            if (currentTime < p.arrivalTime)
                currentTime = p.arrivalTime;

            p.waitingTime = currentTime - p.arrivalTime; // waiting time calculation
            p.completionTime = currentTime + p.burstTime; // completion time calculation
            currentTime += p.burstTime; // update current time after process execution
// log process wait time
            System.out.println("Process " + p.pid + " Wait Time: " + p.waitingTime);
// deallocate the memory after process is complete
            mem.deallocateMemory(block, p.memoryRequired);
        }
        // calculate and print finial metrics for all completion time
        Metrics m = new Metrics();
        m.calculateMetrics(processes);
    }
}
