import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

class Task {
    String name;
    LocalDate dueDate;
    String priority;
    String category;
    Task nextTask;

    public Task(String name, LocalDate dueDate, String priority, String category) {
        this.name = name;
        this.dueDate = dueDate;
        this.priority = priority;
        this.category = category;
        this.nextTask = null;
    }
}

class queue {
    Task rear;
    Task front;

    public void enqueue(Task task) {
        if (rear == null) {
            rear = task;
            front = task;
        } else {
            rear.nextTask = task;
            rear = task;
        }
    }

    public void dequeue(String taskName) {
        if (isEmpty()) {
            return;
        }
        if (front.name.equals(taskName)) {
            front = front.nextTask;
            return;
        }
        Task current = front;
        while (current.nextTask != null) {
            if (current.nextTask.name.equals(taskName)) {
                current.nextTask = current.nextTask.nextTask;
                if (current.nextTask == null) {
                    rear = current;
                }
                return;
            }
            current = current.nextTask;
        }
    }

    public boolean isEmpty() {
        return front == null;
    }

    public Task front() {
        return front;
    }

    public Task rear() {
        return rear;
    }
}

class stack {
    TaskList taskList;

    public stack() {
        this.taskList = new TaskList();
    }
    Task top;

    public void push(Task task) {
        if (top == null) {
            top = task;
        } else {
            task.nextTask = top;
            top = task;
        }
    }

    public void pop(String taskName) {
        if (isEmpty()) {
            return;
        }
        if (top.name.equals(taskName)) {
            top = top.nextTask;
            return;
        }
        Task current = top;
        while (current.nextTask != null) {
            if (current.nextTask.name.equals(taskName)) {
                current.nextTask = current.nextTask.nextTask;
                return;
            }
            current = current.nextTask;
        }
    }

    public boolean isEmpty() {
        return top == null;
    }

    public Task peek() {
        return top;
    }
}

class TaskList {
    Task head;
    Catagory firstCat;
    stack TaskStack = new stack();
    queue TaskQueue = new queue();
    queue TaskQueuec = new queue();

    public Task getHead() {
        return head;
    }

    public void addToCompleted(Task task) {
        if (TaskQueue.isEmpty()) {
            return;
        }
        if (TaskQueuec.isEmpty()) {
            TaskQueuec.enqueue(task);
        } else {
            Task current = TaskQueue.front();
            while (current.nextTask != null) {
                current = current.nextTask;
            }
            current.nextTask = task;
        }
    }

    public void addTask(Task task) {
        if (head == null) {
            head = task;
        } else {
            Task current = head;
            while (current.nextTask != null) {
                current = current.nextTask;
            }
            current.nextTask = task;
        }
        savecategories(task.category);
    }

    public void printCompletedTasks() {
        Task current = TaskQueuec.front();
        System.out.print("[");
        while (current != null) {
            System.out.print(current.name + " " + current.dueDate + " " + current.priority + " " + current.category + ", ");
            current = current.nextTask;
        }
        System.out.println("]");
    }

    public boolean remove(String taskName) {
        if (head == null) return false;
        if (head.name.equals(taskName)) {
            head = head.nextTask;
            return true;
        }
        Task current = head;
        while (current.nextTask != null) {
            if (current.nextTask.name.equals(taskName)) {
                current.nextTask = current.nextTask.nextTask;
                return true;
            }
            current = current.nextTask;
        }
        return false;
    }

    public void printList() {
        Task current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.name + " " + current.dueDate + " " + current.priority + " " + current.category + ", ");
            current = current.nextTask;
        }
        System.out.println("]");
    }

    public void printListByDueDate() {
        Task current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.name + " " + current.dueDate + " " + current.priority + " " + current.category + ", ");
            current = current.nextTask;
        }
        System.out.println("]");
    }

    public void printUrgentTasks() {
        Task current = head;
        System.out.print("[");
        while (current != null) {
            if ("urgent".equalsIgnoreCase(current.priority)) {
                System.out.print(current.name + " " + current.dueDate + " " + current.priority + " " + current.category + ", ");
            }
            current = current.nextTask;
        }
        System.out.println("]");
    }

    public void savecategories(String category) {
        if (firstCat == null) {
            firstCat = new Catagory(category);
        } else {
            Catagory currentC = firstCat;
            boolean exists = false;
            while (currentC != null) {
                if (currentC.Category.equals(category)) {
                    exists = true;
                    break;
                }
                if (currentC.nextCatagory == null) {
                    break;
                }
                currentC = currentC.nextCatagory;
            }
            if (!exists) {
                currentC.nextCatagory = new Catagory(category);
            }
        }
    }

    public void printTasksByCategory() {
        Catagory currentCategory = firstCat;
        if (currentCategory == null) {
            System.out.println("No tasks found.");
            return;
        }
        while (currentCategory != null) {
            System.out.println("Category: " + currentCategory.Category);
            Task currentTask = head;
            while (currentTask != null) {
                if (currentTask.category.equals(currentCategory.Category)) {
                    System.out.println("  Task: " + currentTask.name + ", Due Date: " + currentTask.dueDate + ", Priority: " + currentTask.priority);
                }
                currentTask = currentTask.nextTask;
            }
            currentCategory = currentCategory.nextCatagory;
        }
    }
}

class Catagory {
    String Category;
    Catagory nextCatagory;

    public Catagory(String Category) {
        this.Category = Category;
        this.nextCatagory = null;
    }
}

public class tast1 {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskList taskList = new TaskList();

        taskList.addTask(new Task("buy", LocalDate.parse("30/05/2024", DATE_FORMAT), "urgent", "Shopping"));
        taskList.addTask(new Task("assignment", LocalDate.parse("01/06/2024", DATE_FORMAT), "normal", "Education"));
        taskList.addTask(new Task("appointment", LocalDate.parse("29/05/2024", DATE_FORMAT), "urgent", "Health"));
        taskList.addTask(new Task("clean", LocalDate.parse("05/06/2024", DATE_FORMAT), "normal", "Chores"));
        taskList.addTask(new Task("pay bill", LocalDate.parse("31/05/2024", DATE_FORMAT), "urgent", "Finance"));

        while (true) {
            System.out.println("1. Add Task");
            System.out.println("2. Complete Task");
            System.out.println("3. View Tasks");
            System.out.println("4. View Tasks by Due Date");
            System.out.println("5. View Urgent Tasks");
            System.out.println("6. View Tasks by Category");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter due date (dd/mm/yyyy): ");
                    LocalDate date;
                    try {
                        String dateString = scanner.nextLine();
                        date = LocalDate.parse(dateString, DATE_FORMAT);
                    } catch (Exception e) {
                        System.out.println("Invalid date format.");
                        break;
                    }

                    System.out.print("Enter priority (urgent or normal): ");
                    String priority;
                    while (true) {
                        priority = scanner.nextLine();
                        if ("urgent".equalsIgnoreCase(priority) || "normal".equalsIgnoreCase(priority)) {
                            break;
                        } else {
                            System.out.println("Invalid priority. Please enter either 'urgent' or 'normal'.");
                        }
                    }

                    System.out.print("Enter category: ");
                    String category = scanner.nextLine();

                    taskList.addTask(new Task(name, date, priority, category));
                    if ("urgent".equalsIgnoreCase(priority)) {
                        taskList.TaskStack.push(new Task(name, date, priority, category));
                    }
                    System.out.println("Task added successfully.");
                    break;

                case 2:
                    System.out.print("Enter task name to complete: ");
                    String taskName = scanner.nextLine();
                    if (taskList.remove(taskName)) {
                        System.out.println("Task completed successfully.");
                    } else {
                        System.out.println("Task not found.");
                    }
                    break;

                case 3:
                    System.out.println("Current Tasks:");
                    taskList.printList();
                    System.out.println("Completed Tasks:");
                    taskList.printCompletedTasks();
                    break;

                case 4:
                    taskList.printListByDueDate();
                    break;

                case 5:
                    taskList.printUrgentTasks();
                    break;

                case 6:
                    taskList.printTasksByCategory();
                    break;

                case 7:
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }
}
