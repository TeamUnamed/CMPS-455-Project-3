[return](../README.md)

<h2 id="sample-output"> Task 1 Sample Output </h2>

```
[taylor@taylor-desktop scheduler]$ ./main -S 1
Scheduler Algorithm Select: FCFS
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
Dispatcher 0    | Using CPU 0
Dispatcher 0    | Now releasing dispatchers.

Dispatcher 0    | Running FCFS algorithm

Dispatcher 0    | Running process 0
Proc. Thread 0  | On CPU: MB=7, CB=0, BT=7, BG:=7
Proc. Thread 0  | Using CPU 0; On burst 1.
Proc. Thread 0  | Using CPU 0; On burst 2.
Proc. Thread 0  | Using CPU 0; On burst 3.
Proc. Thread 0  | Using CPU 0; On burst 4.
Proc. Thread 0  | Using CPU 0; On burst 5.
Proc. Thread 0  | Using CPU 0; On burst 6.
Proc. Thread 0  | Using CPU 0; On burst 7.

Dispatcher 0    | Running process 1
Proc. Thread 1  | On CPU: MB=8, CB=0, BT=8, BG:=8
Proc. Thread 1  | Using CPU 0; On burst 1.
Proc. Thread 1  | Using CPU 0; On burst 2.
Proc. Thread 1  | Using CPU 0; On burst 3.
Proc. Thread 1  | Using CPU 0; On burst 4.
Proc. Thread 1  | Using CPU 0; On burst 5.
Proc. Thread 1  | Using CPU 0; On burst 6.
Proc. Thread 1  | Using CPU 0; On burst 7.
Proc. Thread 1  | Using CPU 0; On burst 8.

Dispatcher 0    | Running process 2
Proc. Thread 2  | On CPU: MB=6, CB=0, BT=6, BG:=6
Proc. Thread 2  | Using CPU 0; On burst 1.
Proc. Thread 2  | Using CPU 0; On burst 2.
Proc. Thread 2  | Using CPU 0; On burst 3.
Proc. Thread 2  | Using CPU 0; On burst 4.
Proc. Thread 2  | Using CPU 0; On burst 5.
Proc. Thread 2  | Using CPU 0; On burst 6.

Dispatcher 0    | Running process 3
Proc. Thread 3  | On CPU: MB=4, CB=0, BT=4, BG:=4
Proc. Thread 3  | Using CPU 0; On burst 1.
Proc. Thread 3  | Using CPU 0; On burst 2.
Proc. Thread 3  | Using CPU 0; On burst 3.
Proc. Thread 3  | Using CPU 0; On burst 4.

Main thread     | Exiting.
```
[Return to Top](#sample-output)
```
[taylor@taylor-desktop scheduler]$ ./main -S 2 3
Scheduler Algorithm Select: Round Robin. Time Quantum = 3
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
Dispatcher 0    | Using CPU 0
Dispatcher 0    | Now releasing dispatchers.

Dispatcher 0    | Running RR algorithm, Time Quantum = 3

Dispatcher 0    | Running process 0
Proc. Thread 0  | On CPU: MB=7, CB=0, BT=3, BG:=3
Proc. Thread 0  | Using CPU 0; On burst 1.
Proc. Thread 0  | Using CPU 0; On burst 2.
Proc. Thread 0  | Using CPU 0; On burst 3.

Dispatcher 0    | Running process 1
Proc. Thread 1  | On CPU: MB=8, CB=0, BT=3, BG:=3
Proc. Thread 1  | Using CPU 0; On burst 1.
Proc. Thread 1  | Using CPU 0; On burst 2.
Proc. Thread 1  | Using CPU 0; On burst 3.

Dispatcher 0    | Running process 2
Proc. Thread 2  | On CPU: MB=6, CB=0, BT=3, BG:=3
Proc. Thread 2  | Using CPU 0; On burst 1.
Proc. Thread 2  | Using CPU 0; On burst 2.
Proc. Thread 2  | Using CPU 0; On burst 3.

Dispatcher 0    | Running process 3
Proc. Thread 3  | On CPU: MB=4, CB=0, BT=3, BG:=3
Proc. Thread 3  | Using CPU 0; On burst 1.
Proc. Thread 3  | Using CPU 0; On burst 2.
Proc. Thread 3  | Using CPU 0; On burst 3.

Dispatcher 0    | Running process 0
Proc. Thread 0  | On CPU: MB=7, CB=3, BT=3, BG:=6
Proc. Thread 0  | Using CPU 0; On burst 4.
Proc. Thread 0  | Using CPU 0; On burst 5.
Proc. Thread 0  | Using CPU 0; On burst 6.

Dispatcher 0    | Running process 1
Proc. Thread 1  | On CPU: MB=8, CB=3, BT=3, BG:=6
Proc. Thread 1  | Using CPU 0; On burst 4.
Proc. Thread 1  | Using CPU 0; On burst 5.
Proc. Thread 1  | Using CPU 0; On burst 6.

Dispatcher 0    | Running process 2
Proc. Thread 2  | On CPU: MB=6, CB=3, BT=3, BG:=6
Proc. Thread 2  | Using CPU 0; On burst 4.
Proc. Thread 2  | Using CPU 0; On burst 5.
Proc. Thread 2  | Using CPU 0; On burst 6.

Dispatcher 0    | Running process 3
Proc. Thread 3  | On CPU: MB=4, CB=3, BT=3, BG:=4
Proc. Thread 3  | Using CPU 0; On burst 4.

Dispatcher 0    | Running process 0
Proc. Thread 0  | On CPU: MB=7, CB=6, BT=3, BG:=7
Proc. Thread 0  | Using CPU 0; On burst 7.

Dispatcher 0    | Running process 1
Proc. Thread 1  | On CPU: MB=8, CB=6, BT=3, BG:=8
Proc. Thread 1  | Using CPU 0; On burst 7.
Proc. Thread 1  | Using CPU 0; On burst 8.

Main thread     | Exiting.
```
[Return to Top](#sample-output)
```
[taylor@taylor-desktop scheduler]$ ./main -S 3
Scheduler Algorithm Select: Non Preemptive - Shortest Job First
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
Dispatcher 0    | Using CPU 0
Dispatcher 0    | Now releasing dispatchers.

Dispatcher 0    | Running Non Preemptive - Shortest Job First

Dispatcher 0    | Running process 3
Proc. Thread 3  | On CPU: MB=4, CB=0, BT=4, BG:=4
Proc. Thread 3  | Using CPU 0; On burst 1.
Proc. Thread 3  | Using CPU 0; On burst 2.
Proc. Thread 3  | Using CPU 0; On burst 3.
Proc. Thread 3  | Using CPU 0; On burst 4.

Dispatcher 0    | Running process 2
Proc. Thread 2  | On CPU: MB=6, CB=0, BT=6, BG:=6
Proc. Thread 2  | Using CPU 0; On burst 1.
Proc. Thread 2  | Using CPU 0; On burst 2.
Proc. Thread 2  | Using CPU 0; On burst 3.
Proc. Thread 2  | Using CPU 0; On burst 4.
Proc. Thread 2  | Using CPU 0; On burst 5.
Proc. Thread 2  | Using CPU 0; On burst 6.

Dispatcher 0    | Running process 0
Proc. Thread 0  | On CPU: MB=7, CB=0, BT=7, BG:=7
Proc. Thread 0  | Using CPU 0; On burst 1.
Proc. Thread 0  | Using CPU 0; On burst 2.
Proc. Thread 0  | Using CPU 0; On burst 3.
Proc. Thread 0  | Using CPU 0; On burst 4.
Proc. Thread 0  | Using CPU 0; On burst 5.
Proc. Thread 0  | Using CPU 0; On burst 6.
Proc. Thread 0  | Using CPU 0; On burst 7.

Dispatcher 0    | Running process 1
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
```
[taylor@taylor-desktop scheduler]$ ./main -S 4
Scheduler Algorithm Select: Preemptive - Shortest Job First
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
Dispatcher 0    | Using CPU 0
Dispatcher 0    | Now releasing dispatchers.

Dispatcher 0    | Running Preemptive - Shortest Job First

Dispatcher 0    | Running process 3
Proc. Thread 3  | On CPU: MB=4, CB=0, BT=4, BG:=4
Proc. Thread 3  | Using CPU 0; On burst 1.
Proc. Thread 3  | Using CPU 0; On burst 2.
Proc. Thread 3  | Using CPU 0; On burst 3.
Proc. Thread 3  | Using CPU 0; On burst 4.

Dispatcher 0    | Running process 2
Proc. Thread 2  | On CPU: MB=6, CB=0, BT=6, BG:=6
Proc. Thread 2  | Using CPU 0; On burst 1.
Proc. Thread 2  | Using CPU 0; On burst 2.
Proc. Thread 2  | Using CPU 0; On burst 3.
Proc. Thread 2  | Using CPU 0; On burst 4.
Proc. Thread 2  | Using CPU 0; On burst 5.
Proc. Thread 2  | Using CPU 0; On burst 6.

Dispatcher 0    | Running process 0
Proc. Thread 0  | On CPU: MB=7, CB=0, BT=7, BG:=7
Proc. Thread 0  | Using CPU 0; On burst 1.
Proc. Thread 0  | Using CPU 0; On burst 2.
Proc. Thread 0  | Using CPU 0; On burst 3.
Proc. Thread 0  | Using CPU 0; On burst 4.
Proc. Thread 0  | Using CPU 0; On burst 5.
Proc. Thread 0  | Using CPU 0; On burst 6.

--------------- Ready Queue ---------------
ID:1, Max Burst:8, Current Burst:0
ID:11, Max Burst:9, Current Burst:0
-------------------------------------------

Proc. Thread 0  | Using CPU 0; On burst 7.

Dispatcher 0    | Running process 1
Proc. Thread 1  | On CPU: MB=8, CB=0, BT=8, BG:=8
Proc. Thread 1  | Using CPU 0; On burst 1.
Proc. Thread 1  | Using CPU 0; On burst 2.
Proc. Thread 1  | Using CPU 0; On burst 3.
Proc. Thread 1  | Using CPU 0; On burst 4.
Proc. Thread 1  | Using CPU 0; On burst 5.
Proc. Thread 1  | Using CPU 0; On burst 6.
Proc. Thread 1  | Using CPU 0; On burst 7.
Proc. Thread 1  | Using CPU 0; On burst 8.

Dispatcher 0    | Running process 11
Proc. Thread 11 | On CPU: MB=9, CB=0, BT=9, BG:=9
Proc. Thread 11 | Using CPU 0; On burst 1.

--------------- Ready Queue ---------------
ID:10, Max Burst:3, Current Burst:0
-------------------------------------------


Dispatcher 0    | Running process 10
Proc. Thread 10 | On CPU: MB=3, CB=0, BT=3, BG:=3
Proc. Thread 10 | Using CPU 0; On burst 1.
Proc. Thread 10 | Using CPU 0; On burst 2.
Proc. Thread 10 | Using CPU 0; On burst 3.

Dispatcher 0    | Running process 11
Proc. Thread 11 | On CPU: MB=9, CB=1, BT=9, BG:=9
Proc. Thread 11 | Using CPU 0; On burst 2.
Proc. Thread 11 | Using CPU 0; On burst 3.
Proc. Thread 11 | Using CPU 0; On burst 4.

--------------- Ready Queue ---------------
ID:10, Max Burst:3, Current Burst:0
-------------------------------------------


Dispatcher 0    | Running process 10
Proc. Thread 10 | On CPU: MB=3, CB=0, BT=3, BG:=3
Proc. Thread 10 | Using CPU 0; On burst 1.
Proc. Thread 10 | Using CPU 0; On burst 2.
Proc. Thread 10 | Using CPU 0; On burst 3.

Dispatcher 0    | Running process 11
Proc. Thread 11 | On CPU: MB=9, CB=4, BT=9, BG:=9
Proc. Thread 11 | Using CPU 0; On burst 5.
Proc. Thread 11 | Using CPU 0; On burst 6.
Proc. Thread 11 | Using CPU 0; On burst 7.
Proc. Thread 11 | Using CPU 0; On burst 8.
Proc. Thread 11 | Using CPU 0; On burst 9.

Main thread     | Exiting.
```
[Return to Top](#sample-output)
```
[taylor@taylor-desktop scheduler]$ gcc -o main -pthread main.c -Iinc/ -Ilib/ inc/*.h lib/*.c
[taylor@taylor-desktop scheduler]$ ./main -S 4
Scheduler Algorithm Select: Preemptive - Shortest Job First
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
Dispatcher 0    | Using CPU 0
Dispatcher 0    | Now releasing dispatchers.

Dispatcher 0    | Running Preemptive - Shortest Job First

Dispatcher 0    | Running process 3
Proc. Thread 3  | On CPU: MB=4, CB=0, BT=4, BG:=4
Proc. Thread 3  | Using CPU 0; On burst 1.
Proc. Thread 3  | Using CPU 0; On burst 2.
Proc. Thread 3  | Using CPU 0; On burst 3.
Proc. Thread 3  | Using CPU 0; On burst 4.

--------------- Ready Queue ---------------
ID:0, Max Burst:7, Current Burst:0
ID:1, Max Burst:8, Current Burst:0
ID:2, Max Burst:6, Current Burst:0
-------------------------------------------


Dispatcher 0    | Running process 2
Proc. Thread 2  | On CPU: MB=6, CB=0, BT=6, BG:=6
Proc. Thread 2  | Using CPU 0; On burst 1.
Proc. Thread 2  | Using CPU 0; On burst 2.
Proc. Thread 2  | Using CPU 0; On burst 3.
Proc. Thread 2  | Using CPU 0; On burst 4.
Proc. Thread 2  | Using CPU 0; On burst 5.
Proc. Thread 2  | Using CPU 0; On burst 6.

--------------- Ready Queue ---------------
ID:0, Max Burst:7, Current Burst:0
ID:1, Max Burst:8, Current Burst:0
-------------------------------------------


Dispatcher 0    | Running process 0
Proc. Thread 0  | On CPU: MB=7, CB=0, BT=7, BG:=7
Proc. Thread 0  | Using CPU 0; On burst 1.
Proc. Thread 0  | Using CPU 0; On burst 2.
Proc. Thread 0  | Using CPU 0; On burst 3.
Proc. Thread 0  | Using CPU 0; On burst 4.
Proc. Thread 0  | Using CPU 0; On burst 5.
Proc. Thread 0  | Using CPU 0; On burst 6.

--------------- Ready Queue ---------------
ID:1, Max Burst:8, Current Burst:0
ID:11, Max Burst:9, Current Burst:0
-------------------------------------------

Proc. Thread 0  | Using CPU 0; On burst 7.

--------------- Ready Queue ---------------
ID:1, Max Burst:8, Current Burst:0
ID:11, Max Burst:9, Current Burst:0
-------------------------------------------


Dispatcher 0    | Running process 1
Proc. Thread 1  | On CPU: MB=8, CB=0, BT=8, BG:=8
Proc. Thread 1  | Using CPU 0; On burst 1.
Proc. Thread 1  | Using CPU 0; On burst 2.
Proc. Thread 1  | Using CPU 0; On burst 3.
Proc. Thread 1  | Using CPU 0; On burst 4.
Proc. Thread 1  | Using CPU 0; On burst 5.
Proc. Thread 1  | Using CPU 0; On burst 6.
Proc. Thread 1  | Using CPU 0; On burst 7.
Proc. Thread 1  | Using CPU 0; On burst 8.

--------------- Ready Queue ---------------
ID:11, Max Burst:9, Current Burst:0
-------------------------------------------


Dispatcher 0    | Running process 11
Proc. Thread 11 | On CPU: MB=9, CB=0, BT=9, BG:=9
Proc. Thread 11 | Using CPU 0; On burst 1.

--------------- Ready Queue ---------------
ID:10, Max Burst:3, Current Burst:0
-------------------------------------------


--------------- Ready Queue ---------------
ID:10, Max Burst:3, Current Burst:0
ID:11, Max Burst:9, Current Burst:1
-------------------------------------------


Dispatcher 0    | Running process 10
Proc. Thread 10 | On CPU: MB=3, CB=0, BT=3, BG:=3
Proc. Thread 10 | Using CPU 0; On burst 1.
Proc. Thread 10 | Using CPU 0; On burst 2.
Proc. Thread 10 | Using CPU 0; On burst 3.

--------------- Ready Queue ---------------
ID:11, Max Burst:9, Current Burst:1
-------------------------------------------


Dispatcher 0    | Running process 11
Proc. Thread 11 | On CPU: MB=9, CB=1, BT=9, BG:=9
Proc. Thread 11 | Using CPU 0; On burst 2.
Proc. Thread 11 | Using CPU 0; On burst 3.
Proc. Thread 11 | Using CPU 0; On burst 4.

--------------- Ready Queue ---------------
ID:10, Max Burst:3, Current Burst:0
-------------------------------------------


--------------- Ready Queue ---------------
ID:10, Max Burst:3, Current Burst:0
ID:11, Max Burst:9, Current Burst:4
-------------------------------------------


Dispatcher 0    | Running process 10
Proc. Thread 10 | On CPU: MB=3, CB=0, BT=3, BG:=3
Proc. Thread 10 | Using CPU 0; On burst 1.
Proc. Thread 10 | Using CPU 0; On burst 2.
Proc. Thread 10 | Using CPU 0; On burst 3.

--------------- Ready Queue ---------------
ID:11, Max Burst:9, Current Burst:4
-------------------------------------------


Dispatcher 0    | Running process 11
Proc. Thread 11 | On CPU: MB=9, CB=4, BT=9, BG:=9
Proc. Thread 11 | Using CPU 0; On burst 5.
Proc. Thread 11 | Using CPU 0; On burst 6.
Proc. Thread 11 | Using CPU 0; On burst 7.
Proc. Thread 11 | Using CPU 0; On burst 8.
Proc. Thread 11 | Using CPU 0; On burst 9.

--------------- Ready Queue ---------------
-------------------------------------------

Main thread     | Exiting.
```
[Return to Top](#sample-output)