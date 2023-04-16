import java.util.*;

class Task implements Runnable {
    int ID;
    int BurstTime;
    private Queue<Task> Queue;
    private int Cycles;

    public Task(int ID, int BurstTime) {
        this.ID = ID;
        this.BurstTime = BurstTime;
    }

    public Task(Queue<Task> Queue, int Cycles) {
        this.Queue = Queue;
        this.Cycles = Cycles;
    }

    @Override
    public void run() {
        while (!Queue.isEmpty()) {
            Task task = Queue.poll();
            int remainingBurstTime = task.BurstTime;
            while (remainingBurstTime > 0) {
                int timeSlice = Math.min(Cycles, remainingBurstTime);
                CPU.run(task, timeSlice);
                remainingBurstTime -= timeSlice;
            }
            System.out.println("Task " + task.ID + " finished.");
        }
    }
}

class CPU {
    public static synchronized void run(Task task, int TimeSlice) {
        System.out.println("Task " + task.ID + " running for " + TimeSlice + " cycles");
        try {
            Thread.sleep(TimeSlice * 10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Main {
    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int Limit = 20;
        int Cycles;

        // Generates a T value in the range [1, 25]
        int Tasks = random.nextInt(25) + 1;

        System.out.print("Enter amount of cycles (1-" + Limit + "): ");
        try {
            Cycles = scanner.nextInt();
            if (Cycles <= 0 || Cycles >= Limit) {
                System.out.println("Invalid input, Using random number");
                Cycles = random.nextInt(10) + 1;
            }
        } catch (Exception e) {
            System.out.println("Invalid input, Using random number");
            Cycles = random.nextInt(10) + 1;
        }
        System.out.println("Cycles: " + Cycles);
        scanner.close();
        System.out.println("Tasks: " + Tasks);
        System.out.println("-----------------------------");

        Random rand = new Random();
        Queue<Task> Queue = new LinkedList<>();
        for (int i = 1; i <= Tasks; i++) {
            System.out.println("Task " + i + " Created");
            // Generates a B value in the range [1, 50]
            int burstTime = rand.nextInt(50) + 1;
            Task task = new Task(i, burstTime);
            Queue.add(task);
        }

        Thread dispatcherThread = new Thread(new Task(Queue, Cycles));
        dispatcherThread.start();
    }
}

class RRAlgorithm {
    public String getName() {
        return "RR Algorithm";
    }
}


