package net.unamed.cmps455.project3;

import net.unamed.cmps455.project3.cpu.*;
import net.unamed.cmps455.project3.cpu.algorithm.FCFSAlgorithm;
import net.unamed.cmps455.project3.cpu.algorithm.NSJFAlgorithm;
import net.unamed.cmps455.project3.cpu.algorithm.PSJFAlgorithm;
import net.unamed.cmps455.project3.cpu.algorithm.RRAlgorithm;

import java.util.Random;

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

        if (args.length == 0 || (args.length == 1 && args[0].equals("-S"))) {
            System.out.println("Error: Algorithm must be selected \n\tUsage: \"-S <algorithm>\"");
            return;
        }

        DispatchAlgorithm algorithm = null;

        if (args.length == 2 && args[0].equals("-S")) {
            int index = 0;
            try {
                index = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Error: Algorithm selection must be an integer in range [1-4] \n\t Usage: \"-S <algorithm>\"");
                return;
            }

            algorithm = switch (index) {
                case 1 -> new FCFSAlgorithm();
                case 2 -> new RRAlgorithm(4);
                case 3 -> new NSJFAlgorithm();
                case 4 -> new PSJFAlgorithm();
                default -> null;
            };
        }

        OperatingSystem os = new OperatingSystem(1, algorithm);

        Random random = new Random();
        int taskCount = random.nextInt(8) + 4;
        System.out.printf("# task_threads = %d%n", taskCount);

        for (int i = 0; i < taskCount; i++) {
            //os.log("Creating process thread %d", i);
            os.scheduleTask(new Task(i, random.nextInt(8) + 8));
        }

        os.enter();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored){}
        for (int i = 0; i < taskCount; i++) {
            try {
                Thread.sleep(random.nextLong(5)*10);
            } catch (InterruptedException ignored){
                System.out.println("???");
            }
            os.scheduleTask(new Task(i + taskCount, random.nextInt(8) + 8));
        }
//        os.exitOnQueueEmpty();
        os.exit(2);
    }
}
