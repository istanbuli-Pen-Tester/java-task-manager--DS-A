# Java Task Manager – DS&A Assignment

**Java console Task Manager – custom ADTs, sorting & graph algorithms, performance benchmarking.**

## What It Is
A console‐based task management system where users can:
- Add, delete & complete tasks (filterable by date, priority, category)
- View tasks sorted via merge-sort on a cloned list
- Enqueue completed tasks in FIFO order
- Get a “recommended next task” via a weighted directed graph

## Tech Stack
- **Language:** Java  
- **Data Structures:** LinkedList, Queue, Stack  
- **Algorithms:** Merge Sort, Enhanced Selection Sort, Dijkstra’s, Bellman-Ford  
- **Analysis:** Big-O complexity, memory/stack profiling

## Key Highlights
- **Custom ADTs:**  
  - `TaskList` using a singly linked list for active tasks  
  - `CompletedQueue` for finished tasks (FIFO)  
- **Algorithm Benchmarks:**  
  - Merge Sort vs. Selection Sort on 100K vs. 1M tasks  
- **Graph-Based Recommendations:**  
  - Built a weighted graph to suggest next tasks (Dijkstra & Bellman-Ford)  
- **Performance Analysis:**  
  - Documented runtime & stack usage for recursive methods

## What I Learned
- Designing robust OOP models & interfaces  
- Implementing and tuning classic DS&A algorithms  
- Applying graph theory to a real-world recommendation problem  
- Profiling Java apps for time & memory efficiency
