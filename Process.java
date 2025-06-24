package model; //  shows that this class is part of a model and help organise the code

public class Process { // shows class process which represent a stimulated process in the OS

  public int pid; // process ID which shows a unique identifier for the process
  public int arrivalTime; // shows the time the process will arrive in the queue
  public int burstTime; // shows the time the process requires on the cpu
  public int memoryRequired;// shows the amount of memory the process require during execution
  public int completionTime; // shows the time the process completes execution
  public int waitingTime;// shows the total time the process spends waiting in thr queue
  public int turnaroundTime; // shows the total time from arrival time  to completion
  public int cpuUsage;// shows how much CPU time the process used
  public int memoryUtilization;// shows how much memory the process has used

  public Process(int pid, int arrivalTime, int burstTime, int memoryRequired) {// constructor to initialise the process with essential attributes : pid, arrival time ,burst time and memory required
    this.pid = pid;
    this.arrivalTime = arrivalTime;
    this.burstTime = burstTime;
    this.memoryRequired = memoryRequired;
 }
     // public int pid;// public int arrivalTime;// public int burstTime;
    // public int memoryRequired;

    // public Process(int pid, int arrivalTime, int burstTime, int memoryRequired) {
    // this.pid = pid;
    // this.arrivalTime = arrivalTime;
    // this.burstTime = burstTime;
    // this.memoryRequired = memoryRequired;
    // }

}
