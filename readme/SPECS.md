[return](../README.md)
# CPU Scheduling in Single Core and Multi-Core Systems
Assigned: `March 28, 2023` <br>
Due: `April 17, 2023`

- [Task 1](#task1)
  - [Report](#task1-report)
- [Task 2](#task2)
  - [Report](#task2-report)
- [Task 3](#task3)
- [Task 4](#task4)
---
<h2 id="instructions"> Detailed Instructions </h2>

[Back to Top](#instructions)

This project will help you understand scheduling performance, and further enforce multithreaded synchronization through 
the following tasks:
> 1.  Implementation of first-come, first-served (`FCFS`); round-robin (`RR`); non-preemptive shortest job first 
>     (`NSJF`); and preemptive shortest job first (`PSJF`) algorithms for a single-core processor
> 2.  Implementation of FCFS, RR, and NSJF for a multicore processor
> 3.  Command Line
> 4.  Report

This document will give a detailed specification of the above tasks. In addition, it will provide design and 
implementation hints as preparation for further work.

> ***Note:***
> - The `Project` will `utilize only one` `ready queue for` both `single and multicore systems`. 
> - The `Scheduler` is the part of an OS that decides which `Task` from the `Ready Queue` gets to run on a `Core` 
>   (or `CPU`) next. 
> - The `Scheduler` uses an algorithm such as `FCFS`, `RR`, or `SJF` (among others) to make its decision.
---
<h2 id="task1"> Task 1: <i>Implementation for a single-core CPU </i></h2>

[Back to Top](#instructions)

For this task, you will create a simulation of a single-core CPU and scheduler.
> You will be implementing the following scheduler algorithms: 
>  - `FCFS` (first-come, first-served)
>  - `RR`   (round-robin)
>  - `NSJF` (non-preemptive shortest job first)
>  - `PSJF` (and preemptive shortest job first)

> For each run, your program should dynamically generate
> - a value of `T` in the range `[1,25]`
> - a value of `B` in the range `[1,50]`

> You must create a dispatcher that will, depending on the selected algorithm:
> - select a `Task` thread from a `Ready Queue`
> - allow a `Task` thread to run on the `CPU`.

> You need to create a total of `T` task threads:
> 1. fork the thread
> 2. add the `Task` to a `Ready Queue`. 
> - Each `Task` should be modeled by a thread that runs for a total `CPU` `Burst Time` of `B` cycles before exiting.
> - Each `Task` thread should implement a loop, where each iteration of the loop represents `one` `CPU` `Burst Cycle`. 

> After populating the ready queue, `fork a` `Dispatcher` thread which 
> - selects a `Task` thread from the ready queue
> - allows a `Task` thread to `run on` `the CPU` `for a specified time`.

>The `Dispatcher` should
> 1. `select` a `Task` `from the ready queue`
> 2. `enable` the `Task` to run `for a specified number of clock cycles`
> 3. `sleep` until the `Task` thread `completes` its specified `Burst Time`. 
> 4. `wake up and repeat` the process `until all Task` threads `finish` their `Burst` 

> `After all tasks finish`, the main thread will then end the program. 

> The `task selection and burst time` `depends on` `the scheduling algorithm` `given by command line input` <br>
> (see [task 3](#task3))

> The CPU must be represented as a separate function from the dispatcher
> 1. `starts running` `the thread`
> 2. `updates` the `current burst time and allotted burst time` `for the currently running thread`. 

> `The CPU` may be `implemented as` `a separate thread from the dispatcher` <br>
> 
> It is `also acceptable for` `the CPU` to be `implemented as a method` that is run `on the same thread as the 
> dispatcher` <br>
> *(as long as the CPU implementation is clearly separate from the dispatcher itself)*

> As the ready queue will be shared between dispatchers and task threads
> - it `must be protected` with a semaphore or lock, along with any other shared resources.

> Your dispatcher should be able to use the following algorithms for selecting a task from the ready queue:
> - FCFS
> - RR
> - NSJF
> - PSJF
>    > For PSJF:
>    > - `Tasks randomly arrive` `at the ready queue`
>    > - `Tasks arrive` `after` `threads have already started` running on the CPU.
>    > - `Arriving Tasks preempt the current running Task` `if their burst time is shorter` than the burst time 
>       of the current running Task.
> 
> Regardless of the scheduling algorithm being used, `all Task threads` `use the same implementation` <br>
> *(and therefore `run with the same thread function`)*

> As this project is a simulation of scheduling, your program must print output statements denoting important events 
> - the `creation` `of new threads`
> - the `contents` `of the ready queue`
> - the `dispatcher algorithm` `in use`
> - the `Task selected` `each time the Dispatcher runs`
> - the `Burst Time` `of each Task`.

<br>

<h3 id="task1-report"> Report</h3>

[Back to Top](#instructions)

As part of your report, answer the following questions about Task 1:
> 1. **Which scheduling algorithm was fastest?** <br> 
>    - Have your program `create 5 threads` `with burst times of` `18`, `7`, `25`, `42`, and `21`. <br>
>    - Then, `run the program` `with each of the four scheduling algorithms` *(for `RR`, use a `time quantum` of `5`)*. <br>
>    - Provide a table, with columns for algorithm and runtime. 
>    - (Remember: revert to random task creation and burst times before you submit the project)

---
<h2 id="task2"> Task 2: <i>Implementation for a multi-core CPU </i></h2>

[Back to Top](#instructions)

For this task, you will need to implement a simulation of a multicore CPU system. 
> - It will be like [task 1](#task1), in that you’ll need to create and fork a random number of tasks.
> - This time, there will be `four CPUs` on which the tasks can run. 
> - You will have `one dispatcher thread` `per core` to select which task to run.

> - After populating the ready queue fork `one dispatcher thread` `for each CPU`. 
> - Each dispatcher selects a task and allows it to run on that specific CPU. 
> - The `number of cores will be given by command line input`. See the [task 3 description](#task3) for more details. 
> - These CPUs should be able to run `concurrently` so that no one CPU prevents the others from getting a task from the 
>   ready queue and running it.

> As the ready queue will be shared between dispatchers and cores
> - it `must be protected` with a semaphore or lock, along with any other shared resources. 
> - it should be able to use the first-come, first-served; round-robin, and non-preemptive shortest job first algorithms 
>   for selecting a task from the ready queue. 
>   - You do not need to implement the preemptive shortest job first algorithm for this task.

> There should be one general thread function throughout this assignment, as in [task 1](#task1)
> - this function should be `shared with` [task 1](#task1). 
> - Regardless of the scheduling algorithm being used, `all threads` should `fork using a single function`.

> Your program must print output statements denoting important events:
> - the creation of new threads
> - the contents of the ready queue
> - the dispatcher algorithm in use
> - the task being selected each time the dispatcher runs
> - the burst time that each task gets to execute for.

<h3 id="task2-report"> Report</h3>

As part of your report, answer the following questions about Task 2:
> 1. **Which scheduling algorithm was most efficient for use in a multi-core system?** **Why?**

---
<h2 id="task3"> Task 3: <i>Command Line</i></h2>

[Back to Top](#instructions)

The command line requirements for this assignment are a little more involved than in the past two projects. 
> - Allow for multiple arguments, which may themselves take multiple parameters. 
> - The scheduling algorithm should be selected using the argument `-S`, which takes 1 or 2 integer parameters. 
>   - The first parameter is an integer between 1-4, specifying the algorithm to use
>   - when the round-robin algorithm is selected, a second integer parameter between 2-10 is required that specifies the time quantum. 
> - Your program should also accept the argument `-C`, which requires a parameter between 1-4  specifying the number of CPU cores. 
>   - If `-C` is not provided, your program should default to a single core.

| Argument         | Handling                                                            |
|------------------|---------------------------------------------------------------------|
| `-S <algorithm>` | Scheduling Algorithm (`required`) - `<algorithm>:integer, 1-4`      |
| `-S 1`           | First-Come, First-Served                                            |
| `-S 2 <quantum>` | Round-Robin - `<quantum>:integer, 2-10`                             |
| `-S 3`           | Non-Preemptive, Shortest Job First                                  |
| `-S 4`           | Preemptive, Shortest Job First                                      |
| `-C <cores>`     | Number of Cores (`optional`, default:`1`) - `<cores>:integer, 1-4`  |

> Make sure you validate these arguments and parameters for sanity and the correct value range <br>
> (including determining whether the arguments and parameters exist) <br>
> > For example, `-S` without a parameter should not cause your program to crash <br> 
> > Instead, your program should deliver an appropriate error message to the user and exit cleanly.
> 
> Also, note that `-S` and `-C` can potentially be given in any order, so your program should not assume that `-S` will always come first: 
> > “-S 1 -C 1” and “-C 1 -S 1” should do the same thing.
> 
> If you are running your program directly from the command line, add your arguments and parameters to the end of the command you use to run the program. 
> > For example, to run the scheduler on a single core using the first-come, first-served algorithm, you would type something like `./main.exe -C 1 -S 1`
>
> For Java in IntelliJ, you can pass one or more arguments to your program using the Program Arguments field under `Run > Edit Configurations`. 
> > For example, for task 2, using round-robin scheduling with a time quantum of 5, running on 4 cores, you would type `-S 2 5 -C 4` here.

---
<h2 id="task4"> Task 4: <i>Report</i></h2>

[Back to Top](#instructions)

Please submit a detailed report describing your design and implementation. In addition to the questions listed under each task, the report should answer the following questions:
> 1.  Which algorithm was the most difficult to implement for a single-core system and for a multi-core system?

> 2. In your own words, explain how you implemented each task. 
>   - Did you encounter any bugs? If so, how did you fix them? <br>
>   - If you failed to complete any tasks, list them here and briefly explain why.

> 3. What sort of data structures and algorithms did you use for each task?
    
Your report should be in `.pdf`, `.txt`, `.doc`, or `.odt` format.
- Other formats are acceptable, but you run the risk of the TA being unable to open or read it. 
- Such reports will receive `0 points` with `no` opportunity for `resubmission`.
> - Clearly include the name and ULID of all group members in your report. 
> - The questions in the report should be arranged by their associated task, then numbered. 
> - There is no minimum length, although insufficient detail in your answers will result in a penalty.