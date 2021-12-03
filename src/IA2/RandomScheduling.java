package IA2;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * File:	RandomScheduling.java
 * Course: 	20HT - Operating Systems - 1DV512
 * Author: 	xz222bb
 * Date: 	November 2020
 */

// TODO: put this source code file into a new Java package with meaningful name (e.g., dv512.YourStudentID)!

// You can implement additional fields and methods in code below, but
// you are not allowed to rename or remove any of it!

// Additionally, please remember that you are not allowed to use any third-party libraries

public class RandomScheduling {

    public static class ScheduledProcess {
        int processId;
        int burstTime;
        int arrivalMoment;
        boolean completed;

        // The total time the process has waited since its arrival
        int totalWaitingTime;

        // The total CPU time the process has used so far
        // (when equal to burstTime -> the process is complete!)
        int allocatedCpuTime;

        public ScheduledProcess(int processId, int burstTime, int arrivalMoment) {
            this.processId = processId;
            this.burstTime = burstTime;
            this.arrivalMoment = arrivalMoment;
            this.completed = false;
        }
        // ... add further fields and methods, if necessary
    }

    // Random number generator that must be used for the simulation
    Random rng;

    // ... add further fields and methods, if necessary
    List<ScheduledProcess> list = new ArrayList<ScheduledProcess>();
    ScheduledProcess process;
    int tick;
    int completeExecutionTime;
    int averageProcessWaitingTime;

    public RandomScheduling(long rngSeed) {
        this.rng = new Random(rngSeed);
    }

    public void reset() {
        // TODO - remove any information from the previous simulation, if necessary
        this.list.clear();
        this.completeExecutionTime = 0;
        this.averageProcessWaitingTime = 0;
    }

    public void runNewSimulation(final boolean isPreemptive, final int timeQuantum,
                                 final int numProcesses,
                                 final int minBurstTime, final int maxBurstTime,
                                 final int maxArrivalsPerTick, final double probArrival) {

        reset();

        // TODO:
        int processStartTime = 0;
        int arrivedProcesses = 0;
        int completedProcesses = 0;
        int restNumProcesses = numProcesses;
        boolean isIdle = true;

        while (true) {
            // 1. Run a simulation as a loop, with one iteration considered as one unit of time (tick)
            // 2. The simulation should involve the provided number of processes "numProcesses"
            // 3. The simulation loop should finish after the all of the processes have completed
            if (completedProcesses == numProcesses) {
                break;
            }

            for (int i = 0; i < restNumProcesses; i++) {
                // 4. On each tick, a new process might arrive with the given probability (chance)
                double chance = this.rng.nextDouble();

                // 5. However, the max number of new processes per tick is limited
                // by the given value "maxArrivalsPerTick"
                int arrivedProcessesPerTick = 0;
                if (arrivedProcessesPerTick >= maxArrivalsPerTick) {
                    break;
                }

                if (chance == probArrival) {
                    arrivedProcesses++;
                    arrivedProcessesPerTick++;
                    // 6. The burst time of the new process is chosen randomly in the interval
                    // between the values "minBurstTime" and "maxBurstTime" (inclusive)
                    int burstTime = this.rng.nextInt(maxBurstTime - minBurstTime + 1) + minBurstTime;
                    this.process = new ScheduledProcess(arrivedProcesses, burstTime, tick);
                    this.list.add(this.process);
                }

                restNumProcesses = numProcesses - arrivedProcesses;
            }

            // 7. When the CPU is idle and no process is running, the scheduler
            // should pick one of the available processes *at random* and start its execution
            if (isIdle) {
                int indexOfRunningProcess = this.rng.nextInt(list.size());
                while (list.get(indexOfRunningProcess).completed) {
                    indexOfRunningProcess = this.rng.nextInt(list.size());
                }
                list.get(indexOfRunningProcess).completed = false;
            }

            // 8. If the preemptive version of the scheduling algorithm is invoked, then it should
            // allow up to "timeQuantum" time units (loop iterations) to the process,
            // and then preempt the process (pause its execution and return it to the queue)
            if (!isPreemptive) {
                if (!this.process.completed) {
                    processStartTime = this.tick;
                }

                this.process.totalWaitingTime = processStartTime - this.process.arrivalMoment;
                this.process.allocatedCpuTime++;

                if (this.process.allocatedCpuTime == this.process.burstTime) {
                    this.process.completed = true;
                    isIdle = true;
                    processStartTime = 0;
                    completedProcesses++;
                }
                else {
                    isIdle = false;
                }
            }
            else {
                this.process.allocatedCpuTime++;
                this.process.totalWaitingTime = tick + 1 - this.process.arrivalMoment - this.process.allocatedCpuTime;
                processStartTime++;
                isIdle = false;
                if (processStartTime >= timeQuantum) {
                    isIdle = true;
                    processStartTime = 0;
                }
                if (this.process.allocatedCpuTime == this.process.burstTime) {
                    this.process.completed = true;
                    isIdle = true;
                    completedProcesses++;
                }
            }

            this.tick++;

            // If necessary, add additional fields (and methods) to this class to
            // accomodate your solution

            // Note: for all of the random number generation purposes in this method,
            // use "this.rng" !

        }
    }

    public void printResults() {
        // TODO:
        // 1. For each process, print its ID, burst time, arrival time, and total waiting time
        // 2. Afterwards, print the complete execution time of the simulation
        // and the average process waiting time
//        System.out.println("ID\tBurst Time\tArrival Time\tTotal Waiting Time");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("ID: " + this.list.get(i).processId +
                    "\nBurst Time: " + this.list.get(i).burstTime +
                    "\nArrival Time: " + this.list.get(i).arrivalMoment +
                    "\nTotal Waiting Time: " + this.list.get(i).totalWaitingTime);
        }
        System.out.println("Complete Execution Time: " + this.completeExecutionTime +
                "\nAverage Process Waiting Time: " + this.averageProcessWaitingTime);

    }


    public static void main(String args[]) {
        // TODO: replace the seed value below with your birth date, e.g., "20001001"
        final long rngSeed = 19990524;


        // Do not modify the code below — instead, complete the implementation
        // of other methods!
        RandomScheduling scheduler = new RandomScheduling(rngSeed);

        final int numSimulations = 5;

        final int numProcesses = 10;
        final int minBurstTime = 2;
        final int maxBurstTime = 10;
        final int maxArrivalsPerTick = 2;
        final double probArrival = 0.75;

        final int timeQuantum = 2;

        boolean[] preemptionOptions = {false, true};

        for (boolean isPreemptive: preemptionOptions) {

            for (int i = 0; i < numSimulations; i++) {
                System.out.println("Running " + ((isPreemptive) ? "preemptive" : "non-preemptive")
                        + " simulation #" + i);

                scheduler.runNewSimulation(
                        isPreemptive, timeQuantum,
                        numProcesses,
                        minBurstTime, maxBurstTime,
                        maxArrivalsPerTick, probArrival);

                System.out.println("Simulation results:"
                        + "\n" + "----------------------");
                scheduler.printResults();

                System.out.println("\n");
            }
        }

    }
}
