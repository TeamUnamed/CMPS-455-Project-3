public class Argument {
    public static void main(String[] args) {

        // {@code -S <algorithm>}
        /* Argument Checking */
        System.out.println();
        int simulationIndex = 0;
        int cores = 1; // Default
        int quantum = 2; // Default

        if (args.length == 0) {
            System.out.println("ERROR: No Arguments Specified");
            return;
        }

        if (args.length == 1) {
            System.out.println("Error: Missing Argument Value for '-S <VALUE>' or '-C <VALUE>'");
            return;
        }

        if (args[0].equals("-S")) {
            try {
                simulationIndex = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                System.out.println("Error: Argument Value for '-S <" + simulationIndex + ">' is not a valid number.");
                return;
            }
            if (Integer.parseInt(args[1]) == 2) {
                try {
                    int inputQuantum = Integer.parseInt(args[2]);
                    if (inputQuantum >= 2 && inputQuantum <= 10) {
                        quantum = inputQuantum;
                    } else {
                        System.out.println("Error: Argument value for -S 2 '<QUANTUM>' should be between 2 and 10.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument Value for -S 2 '<QUANTUM>' is not a valid number. Input for <QUANTUM> is: " + args[2]);
                    return;
                }
                if (args[3].equals("-C")) {
                    try {
                        int inputCores = Integer.parseInt(args[4]);
                        if (inputCores >= 1 && inputCores <= 4) {
                            cores = inputCores;
                        } else {
                            System.out.println("Error: Argument value for '-C <CORES>' should be between 1 and 4.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Argument value for '-C <CORES>' is not a number.");
                        return;
                    }
                } else {
                    System.out.println("No core(-C) Argument Input, defaulting to 1");              // Defaulting to 1 core
                }
            }
            else if (args[2].equals("-C")) {
                try {
                    int inputCores = Integer.parseInt(args[3]);
                    if (inputCores >= 1 && inputCores <= 4) {
                        cores = inputCores;
                    } else {
                        System.out.println("Error: Argument value for '-C <CORES>' should be between 1 and 4.");
                        return;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument value for '-C <CORES>' is not a number.");
                    return;
                }
            }
            else if (args[2].isEmpty()){
                System.out.println("No core(-C) Argument Input, defaulting to 1");                            // Defaulting to 1 core
            } else {
                System.out.println("ERROR: Invalid Argument '" + args[2] + "'; Only '-C' is permitted.");
                return;
            }

            /* Simulation Index Checking */
            switch (simulationIndex) {
                case 1 -> System.out.println("TEMP -> STARTING SIMULATION 1 (First-Come, First-Served) with a core count of " + cores + " core(s).");
                case 2 -> System.out.println("TEMP -> STARTING SIMULATION 2 (Round-Robin) with a quantum of (" + quantum + ") and with a core count of " + cores + " core(s).");
                case 3 -> System.out.println("TEMP -> STARTING SIMULATION 3 (Non-Preemptive, Shortest Job First) with a core count of " + cores + " core(s).");
                case 4 -> System.out.println("TEMP -> STARTING SIMULATION 4 (Preemptive, Shortest Job First) with a core count of " + cores + " core(s).");
                default -> {
                    System.out.println("ERROR: Invalid Simulation (SIMULATION " + simulationIndex + ")");
                    return;
                }
            }
            System.out.println("Simulation " + simulationIndex + " selected!");
            System.out.println();

        }
        else if (args[0].equals("-C")) {
            try {
                int inputCores = Integer.parseInt(args[1]);
                if (inputCores >= 1 && inputCores <= 4) {
                    cores = inputCores;
                } else {
                    System.out.println("Error: Argument value for '-C <CORES>' should be between 1 and 4.");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Argument value for '-C <CORES>' is not a number.");
                return;
            }
            if (args[2].equals("-S")) {
                try {
                    simulationIndex = Integer.parseInt(args[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Error: Argument Value for '-S <VALUE>' is not a valid number.");
                    return;
                }
                if (args[3].equals("2")){
                    try {
                        int inputQuantum = Integer.parseInt(args[4]);
                        if (inputQuantum >= 2 && inputQuantum <= 10) {
                            quantum = inputQuantum;
                        } else {
                            System.out.println("Error: Argument value for -S 2 '<QUANTUM>' should be between 2 and 10.");
                            return;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Argument Value for -S 2 '<QUANTUM>' is not a valid number.");
                        return;
                    }
                }
                /* Simulation Index Checking */
                switch (simulationIndex) {
                    case 1 -> System.out.println("TEMP -> STARTING SIMULATION 1 (First-Come, First-Served) with a core count of " + cores + " core(s).");
                    case 2 -> System.out.println("TEMP -> STARTING SIMULATION 2 (Round-Robin) with a quantum of (" + quantum + ") and with a core count of " + cores + " core(s).");
                    case 3 -> System.out.println("TEMP -> STARTING SIMULATION 3 (Non-Preemptive, Shortest Job First) with a core count of " + cores + " core(s).");
                    case 4 -> System.out.println("TEMP -> STARTING SIMULATION 4 (Preemptive, Shortest Job First) with a core count of " + cores + " core(s).");
                    default -> {
                        System.out.println("ERROR: Invalid Simulation (SIMULATION " + simulationIndex + ")");
                        return;
                    }
                }
                System.out.println("Simulation " + simulationIndex + " selected!");
                System.out.println();

            } else {
                System.out.println("ERROR: Invalid Argument '" + args[2] + "'; Only '-S' or '-C' is permitted.");
            }
        }
        else {
            System.out.println("ERROR: Invalid Argument '" + args[0] + "'; Only '-S' or '-C' is permitted.");
        }
    }
}