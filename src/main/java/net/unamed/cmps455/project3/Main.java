package net.unamed.cmps455.project3;

public class Main {

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
        TaskThread[] threads = {
                new TaskThread("Alpha"),
                new TaskThread("Beta")
        };
        OperatingSystem os = new OperatingSystem(1, threads);
    }
}
