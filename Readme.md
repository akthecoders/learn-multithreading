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

### Performance in Multithreading
- Latency: The time to completion of a task. Measured in time units.
- Throughput: The amount of tasks completed in a given period . MEasured in tasks/time unit.

### Latency
- Break the task to N subtasks
- If #threads==#cores is optimal only if all the threads are runnable and can run without interruption(no IO/ blocking calls/ sleep etc) <- Very rare condition to achieve.

### Thread Pooling
- Creating thread once and using them again instead of creating new thread each time.
- Fixed Thread Pool Executor

        int no_of_threads = 4;
        Executor executor = Executors.newFixedThreadPoll(no_of_threads);
        Runnable task = ...;
        executor.execute(task);
        
### Stack & Heap Memory Region
- Stack: Every thread has its own stack. Stack is a memory region where methods are called, arguments are passed, local variables are stored.
- Stack + Instruction Pointer = State of each thread execution.
- All Variables belong to the thread executing on that thread.
- Statically allocated when the thread is created.
- Stack size is fixed & relatively small(platform specific)
- If our calling hierarchy is too deep, we may get an stack overflow exception.

### Heap
- Shared with all threads under that process.
- What is allocated to Heap ?
    * Object(anything created with new operator)
    * Strings
    * Collection
    * Members of classes
    * Static variables
- Heap is governed & managed by garbage collector
- Object -> stays as long as we have a reference to them
- Static variables -> stays forever until terminated.

Heap(shared) | Stack(exclusive)
-------------|-----------------
Objects | Local primitive types
Class Members | 
Static Variables | Local references

### Resources sharing b/w threads
- What are resources ?
    * Variables(String, int..)
    * Data Structures
    * File or connection handlers
    * Any Object
    
### Synchronized Keyword
- To protect a method from executing parallely by two threads.

        public static synchronized int methodname() {...}
        
        public static int xyz() {
            synchronized(this) {
                ...
            }
        }
        
        These are two types of ways to use synchronized, by using synchronized , we are making it a critical section which only a single thread can access

### Atomic Operations
- All references are atomic( getters, setters etc)
- All assignment to primitive types are safe except long and double(because they are of 64 bit).
    * To make them atomic use volatile keyword. Example: volatile double x = 1.0;

### Race Condition
- Condition where multiple threads are accessing a shared resources.
- At least one thread is modifying the resource.
- The timing of threads scheduling may cause incorrect results.
- **Solution**: Identify the non-atomic operation and protect that block by synchronized

### DeadLock
When there is some resource needed by a thread and the another thread need a resource which is help by the other thread, So both threads are deadlocked.
- Type of Locking
    * Coarse Graned Locking -> Lock Everything (full method synchronized)
    * File graned lock -> Locking small part of code which need to be protected.
    
### Reentrant Lock
- lockInterruptibly()
- tryLock()
- Reentrant Read/Write Lock- >new ReentrantReadWriteLock()

### Semaphore
Semaphore can make us define how many threads can access a particular resource at one.
It takes no of permits to be given to a particular resource.
        
        Semaphore semaphore = new Semaphore(No of Permits)
        semaphore.accquire(5)
        useResource()...//All logic
- Any thread can release the lock and any thread can accquire it.

### Wait, Notify, NotifyAll


