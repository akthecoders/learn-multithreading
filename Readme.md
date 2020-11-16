# MultiThreading and Concurrency

### Why we need Threads?</h3>
- Responsiveness
- Performance

### What threads are?
- Whenever we start a program then our OS creates a process for it by creating instance of it.
- Each Process can contain many threads under it.
- Each Process have its own context.
- Every process contains Files, Heap (Memory), Code, Main Thread( SubThreads, Stack, Pointer Instructions)
- Every Thread has its own:
    * Stack - Region in memory, where local variables are stored, and passed into functions.
    * Instruction Pointer - Address to the next instruction to be executed.
    
### Context Switching
- An OS has many threads to execute for every core, so it needs to switch between different threads, this stopping of existing thread,
scheduling existing thread out, scheduling next thread in and starting in is called **context switching**.
- It is not cheap, and there some cost for this concurrency.
- Each thread consumes some resources in the CPU and memory.
- When we switch then we need to save the current state and load the next state.
- Thread consumes less resources than process.
- Thread Context switching under one process is easier to switch then in another process.
- Process Context switching is heaver than thread switching.

### Thread Scheduling
- OS divides the time into small EPOCs.
- In Each EPOC threads are given some amount of time.
- Priority to each thread is given based on the dynamic priority variable.
- Dynamic Priority = Static Priority + Bonus (can be negative)
    * Static priority is given by a developer ahead of time
    * Bonus is adjusted by OS.
- This prevents starvation for the threads.

### Threads
- run()
- Thread.currentThread().getName();
- Thread.interrupt
- **Daemon Threads** These are those threads that do not prevent the application from exiting if the main thread terminates.
They also gets terminated along with the main thread.
- To mark a thread as Daemon just use thread.setDaemon(true)