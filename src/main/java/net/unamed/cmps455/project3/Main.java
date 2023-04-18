package net.unamed.cmps455.project3;

import net.unamed.cmps455.project3.cpu.*;
import net.unamed.cmps455.project3.cpu.algorithm.FCFSAlgorithm;
import net.unamed.cmps455.project3.cpu.algorithm.NSJFAlgorithm;
import net.unamed.cmps455.project3.cpu.algorithm.PSJFAlgorithm;
import net.unamed.cmps455.project3.cpu.algorithm.RRAlgorithm;

import java.util.Random;

public class Main {

    private static int algorithm = 0;
    private static int quantum = 10;
    private static int cores = 1;

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

        if (!checkArguments(args))
            return;

        if (algorithm < 1 || algorithm > 4) {
            System.out.println("Error: Algorithm must be in range [1-4]");
            return;
        }

        if (quantum < 1 || quantum > 10) {
            System.out.println("Warning: Quantum must be in range [1-10]");
            quantum = Math.max(Math.min(10,quantum), 0);
            System.out.printf("\tQuantum set to %d%n", quantum);
        }

        if (cores < 1 || cores > 4) {
            System.out.println("Warning: Cores must be in range [1-4]");
            quantum = Math.max(Math.min(4,quantum), 0);
            System.out.printf("\tCores set to %d%n", cores);
        }

        DispatchAlgorithm dispatchAlgorithm = switch (Main.algorithm) {
            case 1 -> new FCFSAlgorithm();
            case 2 -> new RRAlgorithm(quantum);
            case 3 -> new NSJFAlgorithm();
            case 4 -> new PSJFAlgorithm();
            default -> null;
        };

        if (dispatchAlgorithm == null) {
            System.out.println("Error: Invalid Algorithm");
        }

        OperatingSystem os = new OperatingSystem(cores, dispatchAlgorithm);

        Random random = new Random();
        int taskCount = random.nextInt(25) + 1;
        System.out.printf("# task_threads = %d%n", taskCount);

        for (int i = 0; i < taskCount; i++) {
            //os.log("Creating process thread %d", i);
            os.scheduleTask(new Task(i, random.nextInt(50) + 1));
        }

        os.enter();

        if (algorithm == 4) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
            }
            for (int i = 0; i < taskCount; i++) {
                try {
                    Thread.sleep(random.nextLong(5) * 10);
                } catch (InterruptedException ignored) {
                    System.out.println("???");
                }
                os.scheduleTask(new Task(i + taskCount, random.nextInt(8) + 8));
            }
        }

        os.exit(2);
    }

    private static boolean checkArguments(String[] args) {
        if (args.length == 0) {
            return false;
        }

        boolean algorithmSpec = false;
        boolean coresSpec = false;

        int algorithm = 0;
        int quantum = 10;
        int cores = 1;

        if (args.length == 1) {
            if (args[0].equals("-S")) {
                System.out.println("Error: Must Specify Algorithm");
                System.out.println("\tUsage: -S <algorithm> [quantum]");
            } else if (args[0].equals("-C")) {
                System.out.println("Error: Must Specify Cores");
                System.out.println("\tUsage: -C <cores>");
            }
        }

        for (int i = 0; i < args.length;) {
            if (args[i].equals("-S") && !algorithmSpec) {
                try {
                    algorithm = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                    System.out.printf("Error: \"%s\" is not a number%n", args[i+1]);
                    System.out.println("\tUsage: -S <algorithm> [quantum]");
                    return false;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Error: Missing Argument <algorithm>");
                    System.out.println("\tUsage: -S <algorithm> [quantum]");
                }

                algorithmSpec = true;

                if (algorithm == 2) {
                    try {
                        quantum = Integer.parseInt(args[i + 2]);

                        i += 3;
                        continue;
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.out.println("Quantum not specified. Defaulting to <10>");
                    }
                }
                i += 2;
            } else if (args[i].equals("-S")) {
                System.out.println("Error: Algorithm already specified!");
            } else if (args[i].equals("-C") && !coresSpec) {
                try {
                    cores = Integer.parseInt(args[i + 1]);
                } catch (NumberFormatException e) {
                    System.out.printf("Error: \"%s\" is not a number%n", args[i+1]);
                    System.out.println("\tUsage: -C <cores>");
                    return false;
                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println("Cores not specified. Defaulting to <1>");
                }

                coresSpec = true;
                i += 2;
            } else if (args[i].equals("-C")) {
                System.out.println("Error: Cores already specified!");
                return false;
            } else {
                System.out.printf("Error: Invalid Argument \"%s\"%n", args[i]);
                return false;
            }
        }

        if (!algorithmSpec) {
            System.out.println("Error: Algorithm not specified!");
            return false;
        }

        Main.algorithm = algorithm;
        Main.quantum = quantum;
        Main.cores = cores;

        return true;
    }
}
