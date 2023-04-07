package net.unamed.cmps455.project3;

import net.unamed.cmps455.project3.cpu.Core;
import net.unamed.cmps455.project3.cpu.Dispatcher;
import net.unamed.cmps455.project3.cpu.ReadyQueue;

public class OperatingSystem {

    /**
     * @param args Arguments for Simulator Control
     *     <ul>
     *       <li> {@code -S <algorithm>}
     *             Scheduling algorithm (<b>required</b>) - {@code <algorithm>: integer (1-4)}
     *         <ul>
     *           <li>{@code -S 1}
     *             First-Come, First-Served</li>
     *           <li>{@code -S 2 <quantum>}
     *             Round-Robin - {@code <quantum>: integer (2-10)}</li>
     *           <li>{@code -S 3}
     *             Non-Preemptive, Shortest Job First</li>
     *           <li>{@code -S 4}
     *             Preemptive, Shortest Job First</li>
     *         </ul>
     *       </li>
     *       <li>{@code -C <cores>}
     *             Number of cores (<b>optional</b>, <i>default 1</i>) - {@code <cores>: integer (1-4)}</li>
     *     </ul>
     */
    public static void main(String[] args) {

    }

    private ReadyQueue readyQueue;
    private Core[] cores;
    private Dispatcher[] dispatchers;

    private OperatingSystem(int cores) {
        this.cores = new Core[cores];
        this.dispatchers = new Dispatcher[cores];

        // Create Ready Queue


        // Create Cores & Dispatchers


        // Start Threads


    }

    /**
     * Get the ready queue for the CPU's task scheduling.
     * @return the ReadyQueue
     */
    public ReadyQueue getReadyQueue() {
        return readyQueue;
    }
}
