[return](../README.md)

<h2 id="sample-output"> Task 2 Sample Output </h2>

```
[taylor@taylor-desktop scheduler]$ ./main -S 1 -C 4
Scheduler Algorithm Select: FCFS
Core Count Select: quad core
# threads = 4
Main thread     | Creating process thread 0
Main thread     | Creating process thread 1
Main thread     | Creating process thread 2
Main thread     | Creating process thread 3

--------------- Ready Queue ---------------
ID:0, Max Burst:7, Current Burst:0
ID:1, Max Burst:8, Current Burst:0
ID:2, Max Burst:6, Current Burst:0
ID:3, Max Burst:4, Current Burst:0
-------------------------------------------

Main thread     | Forking dispatcher 0
Main thread     | Forking dispatcher 1
Dispatcher 0    | Using CPU 0
Main thread     | Forking dispatcher 2
Dispatcher 1    | Using CPU 1
Main thread     | Forking dispatcher 3
Dispatcher 2    | Using CPU 2
Dispatcher 3    | Using CPU 3
Dispatcher 3    | Now releasing dispatchers.

Dispatcher 3    | Running FCFS algorithm
Dispatcher 1    | Running FCFS algorithm
Dispatcher 0    | Running FCFS algorithm

Dispatcher 3    | Running process 0
Dispatcher 2    | Running FCFS algorithm

Dispatcher 0    | Running process 1
Proc. Thread 0  | On CPU: MB=7, CB=0, BT=7, BG:=7
Proc. Thread 0  | Using CPU 3; On burst 1.
Proc. Thread 0  | Using CPU 3; On burst 2.
Proc. Thread 0  | Using CPU 3; On burst 3.
Proc. Thread 0  | Using CPU 3; On burst 4.
Proc. Thread 0  | Using CPU 3; On burst 5.
Proc. Thread 1  | On CPU: MB=8, CB=0, BT=8, BG:=8

Dispatcher 2    | Running process 2
Proc. Thread 0  | Using CPU 3; On burst 6.
Proc. Thread 0  | Using CPU 3; On burst 7.
Proc. Thread 2  | On CPU: MB=6, CB=0, BT=6, BG:=6
Proc. Thread 2  | Using CPU 2; On burst 1.
Proc. Thread 2  | Using CPU 2; On burst 2.
Proc. Thread 2  | Using CPU 2; On burst 3.
Proc. Thread 2  | Using CPU 2; On burst 4.
Proc. Thread 2  | Using CPU 2; On burst 5.
Proc. Thread 2  | Using CPU 2; On burst 6.

Dispatcher 1    | Running process 3
Proc. Thread 3  | On CPU: MB=4, CB=0, BT=4, BG:=4
Proc. Thread 3  | Using CPU 1; On burst 1.
Proc. Thread 3  | Using CPU 1; On burst 2.
Proc. Thread 3  | Using CPU 1; On burst 3.
Proc. Thread 3  | Using CPU 1; On burst 4.
Proc. Thread 1  | Using CPU 0; On burst 1.
Proc. Thread 1  | Using CPU 0; On burst 2.
Proc. Thread 1  | Using CPU 0; On burst 3.
Proc. Thread 1  | Using CPU 0; On burst 4.
Proc. Thread 1  | Using CPU 0; On burst 5.
Proc. Thread 1  | Using CPU 0; On burst 6.
Proc. Thread 1  | Using CPU 0; On burst 7.
Proc. Thread 1  | Using CPU 0; On burst 8.

Main thread     | Exiting.
```
[Return to Top](#sample-output)
```
[taylor@taylor-desktop scheduler]$ ./main -S 2 3 -C 4
Scheduler Algorithm Select: Round Robin. Time Quantum = 3
Core Count Select: quad core
# threads = 4
Main thread     | Creating process thread 0
Main thread     | Creating process thread 1
Main thread     | Creating process thread 2
Main thread     | Creating process thread 3

--------------- Ready Queue ---------------
ID:0, Max Burst:7, Current Burst:0
ID:1, Max Burst:8, Current Burst:0
ID:2, Max Burst:6, Current Burst:0
ID:3, Max Burst:4, Current Burst:0
-------------------------------------------

Main thread     | Forking dispatcher 0
Main thread     | Forking dispatcher 1
Dispatcher 0    | Using CPU 0
Main thread     | Forking dispatcher 2
Dispatcher 1    | Using CPU 1
Main thread     | Forking dispatcher 3
Dispatcher 2    | Using CPU 2
Dispatcher 3    | Using CPU 3
Dispatcher 3    | Now releasing dispatchers.

Dispatcher 0    | Running RR algorithm, Time Quantum = 3
Dispatcher 1    | Running RR algorithm, Time Quantum = 3
Dispatcher 2    | Running RR algorithm, Time Quantum = 3

Dispatcher 0    | Running process 0
Dispatcher 3    | Running RR algorithm, Time Quantum = 3
Proc. Thread 0  | On CPU: MB=7, CB=0, BT=3, BG:=3
Proc. Thread 0  | Using CPU 0; On burst 1.
Proc. Thread 0  | Using CPU 0; On burst 2.
Proc. Thread 0  | Using CPU 0; On burst 3.

Dispatcher 1    | Running process 1
Proc. Thread 1  | On CPU: MB=8, CB=0, BT=3, BG:=3
Proc. Thread 1  | Using CPU 1; On burst 1.
Proc. Thread 1  | Using CPU 1; On burst 2.
Proc. Thread 1  | Using CPU 1; On burst 3.

Dispatcher 2    |
Dispatcher 3    | Running process 2

Dispatcher 0    | Running process 0
Running process 3
Proc. Thread 0  | Proc. Thread 2        | On CPU: MB=7, CB=3, BT=3, BG:=6
Proc. Thread 0  | Using CPU 0; On burst 4.
Proc. Thread 0  | Using CPU 0; On burst 5.
Proc. Thread 0  | Using CPU 0; On burst 6.
On CPU: MB=6, CB=0, BT=3, BG:=3

Dispatcher 0    | Proc. Thread 3        | On CPU: MB=4, CB=0, BT=3, BG:=3
Proc. Thread 2  | Using CPU 2; On burst 1.
Proc. Thread 2  | Using CPU 2; On burst 2.
Proc. Thread 2  | Using CPU 2; On burst 3.
Running process 0

Dispatcher 1    | Running process 1
Proc. Thread 0  |
Dispatcher 2    | On CPU: MB=7, CB=6, BT=3, BG:=7
Proc. Thread 0  | Using CPU 0; On burst 7.
Proc. Thread 1  | On CPU: MB=8, CB=3, BT=3, BG:=6
Proc. Thread 1  | Using CPU 1; On burst 4.
Proc. Thread 1  | Using CPU 1; On burst 5.
Proc. Thread 1  | Using CPU 1; On burst 6.
Proc. Thread 3  | Using CPU 3; On burst 1.
Proc. Thread 3  | Using CPU 3; On burst 2.
Proc. Thread 3  | Using CPU 3; On burst 3.

Dispatcher 3    | Running process 3
Proc. Thread 3  | On CPU: MB=4, CB=3, BT=3, BG:=4
Proc. Thread 3  | Using CPU 3; On burst 4.

Dispatcher 1    | Running process 1
Proc. Thread 1  | On CPU: MB=8, CB=6, BT=3, BG:=8
Proc. Thread 1  | Using CPU 1; On burst 7.
Proc. Thread 1  | Using CPU 1; On burst 8.
Running process 2
Proc. Thread 2  | On CPU: MB=6, CB=3, BT=3, BG:=6
Proc. Thread 2  | Using CPU 2; On burst 4.
Proc. Thread 2  | Using CPU 2; On burst 5.
Proc. Thread 2  | Using CPU 2; On burst 6.

Main thread     | Exiting.
```
[Return to Top](#sample-output)
```
[taylor@taylor-desktop scheduler]$ ./main -S 3 -C 4
Scheduler Algorithm Select: Non Preemptive - Shortest Job First
Core Count Select: quad core
# threads = 4
Main thread     | Creating process thread 0
Main thread     | Creating process thread 1
Main thread     | Creating process thread 2
Main thread     | Creating process thread 3

--------------- Ready Queue ---------------
ID:0, Max Burst:7, Current Burst:0
ID:1, Max Burst:8, Current Burst:0
ID:2, Max Burst:6, Current Burst:0
ID:3, Max Burst:4, Current Burst:0
-------------------------------------------

Main thread     | Forking dispatcher 0
Main thread     | Forking dispatcher 1
Dispatcher 0    | Using CPU 0
Main thread     | Forking dispatcher 2
Dispatcher 1    | Using CPU 1
Dispatcher 2    | Using CPU 2
Main thread     | Forking dispatcher 3
Dispatcher 3    | Using CPU 3
Dispatcher 3    | Now releasing dispatchers.

Dispatcher 3    | Running Non Preemptive - Shortest Job First
Dispatcher 0    | Running Non Preemptive - Shortest Job First
Dispatcher 1    | Running Non Preemptive - Shortest Job First

Dispatcher 3    | Running process 3
Dispatcher 2    | Running Non Preemptive - Shortest Job First

Dispatcher 1    | Running process 2
Proc. Thread 3  | On CPU: MB=4, CB=0, BT=4, BG:=4
Proc. Thread 3  | Using CPU 3; On burst 1.

Dispatcher 2    | Proc. Thread 3        | Running process 0
Proc. Thread 2  | On CPU: MB=6, CB=0, BT=6, BG:=6
Proc. Thread 2  | Using CPU 1; On burst 1.
Using CPU 3; On burst 2.
Proc. Thread 3  | Using CPU 3; On burst 3.
Proc. Thread 3  | Using CPU 3; On burst 4.
Proc. Thread 0  | Proc. Thread 2        | On CPU: MB=7, CB=0, BT=7, BG:=7
Proc. Thread 0  | Using CPU 2; On burst 1.
Proc. Thread 0  | Using CPU 2; On burst 2.
Proc. Thread 0  | Using CPU 2; On burst 3.
Proc. Thread 0  | Using CPU 2; On burst 4.
Proc. Thread 0  | Using CPU 2; On burst 5.
Proc. Thread 0  | Using CPU 2; On burst 6.
Proc. Thread 0  | Using CPU 2; On burst 7.

Dispatcher 0    | Running process 1
Using CPU 1; On burst 2.
Proc. Thread 2  | Using CPU 1; On burst 3.
Proc. Thread 2  | Using CPU 1; On burst 4.
Proc. Thread 2  | Using CPU 1; On burst 5.
Proc. Thread 2  | Using CPU 1; On burst 6.
Proc. Thread 1  | On CPU: MB=8, CB=0, BT=8, BG:=8
Proc. Thread 1  | Using CPU 0; On burst 1.
Proc. Thread 1  | Using CPU 0; On burst 2.
Proc. Thread 1  | Using CPU 0; On burst 3.
Proc. Thread 1  | Using CPU 0; On burst 4.
Proc. Thread 1  | Using CPU 0; On burst 5.
Proc. Thread 1  | Using CPU 0; On burst 6.
Proc. Thread 1  | Using CPU 0; On burst 7.
Proc. Thread 1  | Using CPU 0; On burst 8.

Main thread     | Exiting.
```
[Return to Top](#sample-output)