[return](../README.md)

# Project 3 Rubric


# Task 1: Single Core (Output & Code)
| Item           | Description                                                                                                                                      |  Points<br>Possible   |
|----------------|:-------------------------------------------------------------------------------------------------------------------------------------------------|:---------------------:|
| Initialization | `T` randomly generated in `[1,25]` <br> `B` randomly generated in `[1,50]`                                                                       |           5           |
| Threads        | Single (*General*) Thread used                                                                                                                   |           5           |
| Ready Queue    | Single Read Queue                                                                                                                                |           5           |
|                | Used Dispatcher correctly selects a thread from the ready queue                                                                                  |           5           |
| Algorithms     | FCFS works correctly<br>RR works correctly<br>NSJF works correctly<br>PSJF works correctly<Threads randomly arrive throughout simulation in PSJF | 5<br>5<br>5<br>5<br>5 |
| Output         | Outputs ready queue<br>Output shows thread creation, task selection, burst executed                                                              |        5<br>5         |
| ***Total***    |                                                                                                                                                  |       ***55***        |

# Task 2: Multi-Core
| Item           | Description                                                                         | Points<br>Possible |
|----------------|:------------------------------------------------------------------------------------|:------------------:|
| Initialization | `T` randomly generated in `[1,25]` <br> `B` randomly generated in `[1,50]`          |         5          |
| Threads        | Single (*General*) Thread used                                                      |         5          |
| Ready Queue    | Single Read Queue                                                                   |         5          |
| Multi-Core     | `4` CPUs run concurrently<br>`4` Dispatchers Forked                                 |       5<br>5       |
|                | Used Dispatcher correctly selects a thread from the ready queue                     |         5          |
| Algorithms     | FCFS works correctly<br>RR works correctly<br>NSJF works correctly                  |    5<br>5<br>5     |
| Output         | Outputs ready queue<br>Output shows thread creation, task selection, burst executed |       5<br>5       |
| Protection     | Ready Queue protected with lock/semaphore                                           |         5          |
| ***Total***    |                                                                                     |      ***60***      |

# Task 3: Command Line
| Item           | Description                                                           | Points<br>Possible |
|----------------|:----------------------------------------------------------------------|:------------------:|
| Task Select    | Able to select proper task. (`-S`)                                    |         5          |
| Error Checking | Invalid options produce error message.                                |         5          |
| Empty Input    | Empty inputs does not segfault (`IOOB`).<br>(Assertion Failure: `-2`) |         5          |
| `RR`           | `RR` quantum input works                                              |         5          |
| `-C`           | Able to select core count                                             |         5          |
| ***Total***    |                                                                       |      ***25***      |  

# Task 4: Report
| Item        | Description                                                                                              | Points<br>Possible |
|-------------|:---------------------------------------------------------------------------------------------------------|:------------------:|
| Task 1      | `Q1`: Runtime / Table for provided                                                                       |         5          |       
| Task 2      | `Q1`: Any working method acceptable.<br>Reasoning required for credit.                                   |         5          |    
| Task 3      | `Q1`: Answers may vary<br>`Q2`: Implementation, bugs, solutions<br>`Q3`: Data structures and algorithms. |    5<br>5<br>5     |
| ***Total*** |                                                                                                          |      ***25***      |