package interfaces; // package declaration
// import the process class and list interface for defining the scheduling method
import model.Process;

import java.util.List;

public interface Scheduler {
    // schedule the given list of processes according to a specific CPU scheduling algorithm
    void schedule(List<Process> processes);
}
