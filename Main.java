import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
// current = the address of the index 
//head = the the first index of the list
//nextTask = the index of the next task 

class Task {// structure intialization
    String name;
    LocalDate dueDate;
    String priority; // urgent or normal
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


class Catagory {
String Category;
Catagory nextCatagory;

public Catagory(String Category){
    this.Category = Category;
    this.nextCatagory = null;
    
}
}

class queue{
    TaskList taskList = new TaskList();
    
public void enqueue(Task task){
    taskList.addTask(task);
}
public void dequeue(String task){
    taskList.remove(task);
}
public boolean isEmpty(){

    if (taskList.head == null) {// check if the list is empty
        return false;
    }else{
        return true;
    }

}
public Task front(){
    return taskList.getHead();
}
public Task rear(Task task){
    taskList.reverseList(task);
    Task head=taskList.head;
    taskList.reverseList(task);
    return head;
}


public void printQueue(){
    //TaskList.printList();
}
}
class stack{
    TaskList taskList = new TaskList();
public void push(Task task){

    taskList.addTask(task);
}

public void pop(Task task, String name){
taskList.reverseList(task);
taskList.remove(name);
taskList.reverseList(task);
}
public Task peek(){
    return taskList.head;
}
public boolean isEmpty(){
    Task head=taskList.getHead();
    if (head == null) {// check if the list is empty
        return false;
    }else{
        return true;
    }
}
}
class TaskList {
    Task head;
    Task headC;
    Catagory firstCat;
    stack TaskStack = new stack();
    queue TaskQueue = new queue();
    queue TaskQueuec = new queue();

    public Task getHead(){
    return head;
    }
    
   


public void addToCompleted(Task task) {//adding a task will trigger the previous task to point to it
    if (TaskQueue.isEmpty()) {
        return; //by testing this is a solution to a run time error
    }
    if (TaskQueuec.isEmpty()) {// check if the list is empty
        TaskQueuec.enqueue(task);//areverseListssignig the head to the first task 
      } else {
        Task current = TaskQueue.front();
        while(current.nextTask != null){
        current = current.nextTask;
        }
        current.nextTask = task ;
        
      }
}


    public void addTask(Task task) {//adding a task will trigger the previous task to point to it 
        if (head == null) {// check if the list is empty
            head = task;//assignig the head to the first task printList
        } else {
            Task current = head;
            while (current.nextTask != null) {//go to the end if the list
                current = current.nextTask;//put current in the end
            }
            current.nextTask = task;//put task in place of current
        }
        savecategories(task.category);
    }
    public void printCompletedTasks() {//print completed tasks that are stored in the queue(fifo) to print them by the order that they were added with
        
        if(headC == null){
            System.out.println("[no tasks are completed]");
            return;
          }else{
        Task current = headC;
        System.out.print("[");
        while (current != null) {
          if (current.nextTask == null) {//search until its true then we reached the end 
            System.out.print(current.name +" "+current.dueDate+" "+current.priority+" "+current.category+ ", ");
            break;
          }
          
          System.out.print(current.name +" "+current.dueDate+" "+current.priority+" "+current.category+ ", ");
          current = current.nextTask;
        
        }
        System.out.println("]");
         }
      }

    public boolean remove(String taskName) {//removing a task and that will lead to the previos task to point to null 
        if (head == null) return false; //list is empty
        if (head.name.equals(taskName)) { //we use equals because "==" compares the addresses not values
            head = head.nextTask; //assigne the next address to link the tasks
            return true;
        }
        //from line 46 to 55 its same as a for loop that move to all the tasks in the list from the head till null
        //to search for the task that we need to remove 
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

    public void printList() {//printing all the tasks as long as the indicater has values 
        Task current = head;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.name +" "+current.dueDate+" "+current.priority+" "+current.category+ ", " + (current.nextTask != null ? ", " : ""));
            //the second part is to type comma if there is next task
            current = current.nextTask;
        }
        System.out.println("]");
    }

    public void printListByDueDate() {//this method prints tasks by due date by using bubble sort 
        if (head == null) {
            System.out.println("The task list is empty.");
            return;
        }
    
        Task copiedList = copyList(head);
        Task sortedList = mergeSort(copiedList);
    
        Task current = sortedList;
        System.out.print("[");
        while (current != null) {
            System.out.print(current.name +" "+current.dueDate+" "+current.priority+" "+current.category+ ", " + (current.nextTask != null ? ", " : ""));
            current = current.nextTask;
        }
        System.out.println("]");
    }

    private Task copyList(Task head) {//making a copy of the list to sorted because if we didnt we will ruin the queue and stack code 
        if (head == null) return null;
    
        Task newHead = new Task(head.name, head.dueDate, head.priority, head.category);
        Task currentOriginal = head.nextTask;
        Task currentNew = newHead;
    
        while (currentOriginal != null) {
            currentNew.nextTask = new Task(currentOriginal.name, currentOriginal.dueDate, currentOriginal.priority, currentOriginal.category);
            currentOriginal = currentOriginal.nextTask;
            currentNew = currentNew.nextTask;
        }
    
        return newHead;
    }
    private Task mergeSort(Task head) {
        if (head == null || head.nextTask == null) return head;
    
        Task middle = getMiddle(head);
        Task nextOfMiddle = middle.nextTask;
        middle.nextTask = null;
    
        Task left = mergeSort(head);
        Task right = mergeSort(nextOfMiddle);
    
        return sortedMerge(left, right);
    }
    
    private Task getMiddle(Task head) {
        if (head == null) return head;
    
        Task slow = head, fast = head.nextTask;
    
        while (fast != null) {
            fast = fast.nextTask;
            if (fast != null) {
                slow = slow.nextTask;
                fast = fast.nextTask;
            }
        }
    
        return slow;
    }
    
    private Task sortedMerge(Task left, Task right) {
        if (left == null) return right;
        if (right == null) return left;
    
        Task result;
        if (left.dueDate.isBefore(right.dueDate)) {
            result = left;
            result.nextTask = sortedMerge(left.nextTask, right);
        } else {
            result = right;
            result.nextTask = sortedMerge(left, right.nextTask);
        }
    
        return result;
    }
    

    public void printUrgentTasks() {//printing urgent tasks with a built in function to ignore case 
        
        if (head == null) {
            System.out.println("The task list is empty.");
            return;
        }
    
        head = reverseList(head);//reverse list to achieve FILO = stack 
        Task current = head;
        System.out.print("[");
        while (current != null) {
            if ("urgent".equalsIgnoreCase(current.priority)) {
                
                System.out.print(current.name +" "+current.dueDate+" "+current.priority+" "+current.category+ ", ");
            }
            current = current.nextTask;
        }
        System.out.println("]");
        head=reverseList(head);//restore to its original order
    }
    Task reverseList(Task head) {
        Task prev = null;
        Task current = head;
        while (current != null) {
            Task next = current.nextTask;//assign the next node to the variable "next"
            current.nextTask = prev;//put the address of the next node in prev
            prev = current;current = next;//i++
        }
        return prev;
    }

 
    public void savecategories(String category){
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
    public void printTasksByCategory(){

        Catagory currentCategory = firstCat;
        if (currentCategory == null) {
            System.out.println("there arent any tasks.");
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
public class Main {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        queue TaskQueue = new queue();
        stack TaskStack = new stack();
        TaskList taskList = new TaskList();
        //testing
        
        
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
                LocalDate date=null;//every thing in the try is a local varrbile so i have to difine here 

                try {
                    String dateString = scanner.nextLine();
                    date = LocalDate.parse(dateString, DATE_FORMAT);
                } catch (Exception e) {
                    System.out.println("there was an error"+e);
                    break;
                }
                
                
                System.out.print("Enter priority urgent or normal only : ");// urgent or normal
                String priority;
                while (true) {
                    System.out.print("Enter priority (urgent or normal): ");
                    priority = scanner.nextLine();
                    if ("urgent".equalsIgnoreCase(priority) || "normal".equalsIgnoreCase(priority)) {
                        break;
                    } else {
                        System.out.println("Invalid priority. Please enter either 'urgent' or 'normal'.");
                    }
                }
                System.out.print("Enter category: ");
                String category = scanner.nextLine();
                
                //taskList.addTask(new Task(name, date, priority, category));
                TaskQueue.enqueue(new Task(name, date, priority, category));
                if (priority.equals("urgent")) {
                    TaskStack.push(new Task(name, date, priority, category));
                }
                System.out.println("Task added successfully.");
                break;

                case 2:
    System.out.print("Enter task name to complete: ");
    String taskName = scanner.nextLine();
    Task current = taskList.head;
    Task prev = null;
    boolean found = false;
    while (current != null) {
        if (current.name.equals(taskName)) {//search for the task
            if (prev == null) { //if it doesn't have a previous task then its the first task
                taskList.head = current.nextTask;//the head will be the next task
            } else {
                prev.nextTask = current.nextTask;//assign the prev node to go to next node by giving it the address of the next 
            }
            //
            
            Task completedTask = current;
            //current=current.nextTask;//move to next
            completedTask.nextTask = null;//remove from list
            taskList.addToCompleted(completedTask);//add it to the queue
            //block added to avoid run time error

            //current.nextTask = null;//remove current
            //taskList.addToCompleted(current);//add it to the queue
             
            found = true;//to make sure it exists 
            System.out.println("Task completed successfully.");
            break;
        }
        prev = current;
        current = current.nextTask;
    }
    if (!found) {
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