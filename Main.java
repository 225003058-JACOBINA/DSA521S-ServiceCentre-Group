import java.util.Scanner;

// Class to hold student info
class Student {
    String studentNo;
    String name;
    String serviceType;
    int estimatedTime;
    Student next;  // for linked queue

    public Student(String studentNo, String name, String serviceType, int estimatedTime) {
        this.studentNo = studentNo;
        this.name = name;
        this.serviceType = serviceType;
        this.estimatedTime = estimatedTime;
        this.next = null;
    }

    public String toString() {
        return "[" + studentNo + ", " + name + ", " + serviceType + ", " + estimatedTime + " min]";
    }
}

// Queue implementation using linked list
class StudentQueue {
    private Student front;
    private Student rear;

    public StudentQueue() {
        front = rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    // Enqueue operation adds to rear
    public void enqueue(Student student) {
        if (rear == null) {
            front = rear = student;
        } else {
            rear.next = student;
            rear = student;
        }
        System.out.println("Student added to queue: " + student);
    }

    // Dequeue operation removes from front
    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, no student to serve.");
            return null;
        }
        Student servedStudent = front;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        System.out.println("Serving student: " + servedStudent);
        return servedStudent;
    }

    // Peek operation returns front student without removing
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return front;
    }

    // Display all students in queue
    public void displayQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        System.out.println("Students currently waiting in queue:");
        Student current = front;
        int position = 1;
        while (current != null) {
            System.out.println(position + ". " + current);
            current = current.next;
            position++;
        }
    }
}
// Node structure for Student Service Records
class ServiceRecordNode {
    Student studentData; 
    ServiceRecordNode next;

    public ServiceRecordNode(Student studentData) {
        this.studentData = studentData;
        this.next = null;
    }
}

// Custom Singly Linked List Implementation
class StudentLinkedList {
    private ServiceRecordNode head;

    public StudentLinkedList() {
        this.head = null;
    }

    // Insert at the End
    public void insertStudent(Student student) {
        ServiceRecordNode newNode = new ServiceRecordNode(student);
        if (head == null) {
            head = newNode;
        } else {
            ServiceRecordNode current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        System.out.println("Record saved: " + student.name);
    }

    // Display all service records (Traversal)
    public void displayStudents() {
        if (head == null) {
            System.out.println("No service records found.");
            return;
        }
        System.out.println("\n--- STUDENT SERVICE RECORDS ---");
        ServiceRecordNode current = head;
        while (current != null) {
            System.out.println(current.studentData);
            current = current.next;
        }
    }

    // Search for a student by ID
    public void searchStudent(String studentNo) {
        ServiceRecordNode current = head;
        int position = 1;
        while (current != null) {
            if (current.studentData.studentNo.equalsIgnoreCase(studentNo)) {
                System.out.println("Record found at position " + position + ": " + current.studentData);
                return;
            }
            current = current.next;
            position++;
        }
        System.out.println("Record not found.");
    }

    // Delete a record by ID
    public void deleteStudent(String studentNo) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        if (head.studentData.studentNo.equalsIgnoreCase(studentNo)) {
            System.out.println("Removed record: " + head.studentData.name);
            head = head.next;
            return;
        }
        ServiceRecordNode current = head;
        ServiceRecordNode prev = null;
        while (current != null && !current.studentData.studentNo.equalsIgnoreCase(studentNo)) {
            prev = current;
            current = current.next;
        }
        if (current == null) {
            System.out.println("Record not found.");
            return;
        }
        System.out.println("Removed record: " + current.studentData.name);
        prev.next = current.next;
    }
}
class DailyStatistics {
    private int[] serviceTimes;
    private int count;

    public DailyStatistics(int maxCapacity) {
        this.serviceTimes = new int[maxCapacity];
        this.count = 0;
    }

    public void addTime(int minutes) {
        if (count < serviceTimes.length) {
            serviceTimes[count] = minutes;
            count++;
        } else {
            System.out.println("Statistics database is full for the day!");
        }
    }

    public int calculateTotalTime() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += serviceTimes[i];
        }
        return total;
    }

    public double calculateAverageTime() {
        if (count == 0) return 0.0;
        return (double) calculateTotalTime() / count;
    }

    // Part B: Bubble Sort Algorithm
    public void runBubbleSort() {
        if (count <= 1) {
            System.out.println("Not enough data elements to sort.");
            return;
        }
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (serviceTimes[j] > serviceTimes[j + 1]) {
                    int temp = serviceTimes[j];
                    serviceTimes[j] = serviceTimes[j + 1];
                    serviceTimes[j + 1] = temp;
                }
            }
        }
        System.out.println("Array sorted successfully using Bubble Sort!");
    }

    // Part B: Selection Sort Algorithm
    public void runSelectionSort() {
        if (count <= 1) {
            System.out.println("Not enough data elements to sort.");
            return;
        }
        for (int i = 0; i < count - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < count; j++) {
                if (serviceTimes[j] < serviceTimes[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = serviceTimes[minIndex];
            serviceTimes[minIndex] = serviceTimes[i];
            serviceTimes[i] = temp;
        }
        System.out.println("Array sorted successfully using Selection Sort!");
    }
    public void runPerformanceExperiment() {
        System.out.println("\n--- RUNNING SORTING PERFORMANCE EXPERIMENT ---");
        int testSize = 1000;
        int[] bubbleTestData = new int[testSize];
        int[] selectionTestData = new int[testSize];

        java.util.Random random = new java.util.Random();
        for (int i = 0; i < testSize; i++) {
            int randomTime = random.nextInt(60) + 1;
            bubbleTestData[i] = randomTime;
            selectionTestData[i] = randomTime;
        }

        System.out.println("Generated " + testSize + " random student service records for testing.");

        long startTime = System.nanoTime();
        for (int i = 0; i < testSize - 1; i++) {
            for (int j = 0; j < testSize - i - 1; j++) {
                if (bubbleTestData[j] > bubbleTestData[j + 1]) {
                    int temp = bubbleTestData[j];
                    bubbleTestData[j] = bubbleTestData[j + 1];
                    bubbleTestData[j + 1] = temp;
                }
            }
        }
        long bubbleDuration = System.nanoTime() - startTime;

        startTime = System.nanoTime();
        for (int i = 0; i < testSize - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < testSize; j++) {
                if (selectionTestData[j] < selectionTestData[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = selectionTestData[minIndex];
            selectionTestData[minIndex] = selectionTestData[i];
            selectionTestData[i] = temp;
        }
        long selectionDuration = System.nanoTime() - startTime;

        System.out.println("Bubble Sort execution time:    " + bubbleDuration + " nanoseconds");
        System.out.println("Selection Sort execution time: " + selectionDuration + " nanoseconds");
        
        if (bubbleDuration < selectionDuration) {
            System.out.println("Result: Bubble Sort performed faster on this dataset.");
        } else {
            System.out.println("Result: Selection Sort performed faster on this dataset.");
        }
    }

    public void displayStats() {
        System.out.println("\n--- DAILY PERFORMANCE METRICS ---");
        System.out.println("Total Students Served Today: " + count);
        System.out.println("Total Service Time Accumulation: " + calculateTotalTime() + " minutes");
        System.out.printf("Average Service Time Per Student: %.2f minutes\n", calculateAverageTime());
        
        System.out.print("Service Time Array Data: [ ");
        for (int i = 0; i < count; i++) {
            System.out.print(serviceTimes[i] + " min ");
        }
        System.out.println("]");
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentQueue queue = new StudentQueue();
        StudentLinkedList recordsList = new StudentLinkedList(); 
        DailyStatistics stats = new DailyStatistics(100);

        while (true) {
            System.out.println("\n========================================");
            System.out.println("         CAMPUS SERVICE CENTRE");
            System.out.println("========================================");
            System.out.println("1. Add student to waiting queue");
            System.out.println("2. Serve next student (remove from queue)");
            System.out.println("3. Display waiting students");
            System.out.println("4. Add student service record (Linked List)");
            System.out.println("5. Display student service records");
            System.out.println("6. Search for student record");
            System.out.println("7. Remove student record");
            System.out.println("8. Display daily statistics (Array)");
            System.out.println("9. Sort times using Bubble Sort");
            System.out.println("10. Sort times using Selection Sort");
            System.out.println("11. Run Performance Experiment");
            System.out.println("12. Exit");
            System.out.print("Select option: ");
            
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    System.out.print("Enter Student No: ");
                    String studentNo = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Service Type: ");
                    String serviceType = scanner.nextLine();
                    System.out.print("Enter Estimated Service Time (min): ");
                    int estTime = scanner.nextInt();
                    scanner.nextLine();

                    Student newStudent = new Student(studentNo, name, serviceType, estTime);
                    queue.enqueue(newStudent);
                    break;

                case 2:
                    Student served = queue.dequeue();
                    if (served != null) {
                        recordsList.insertStudent(served);
                        stats.addTime(served.estimatedTime);
                    }
                    break;

                case 3:
                    queue.displayQueue();
                    break;

                case 4:
                    System.out.println("\n--- Add Service Record ---");
                    System.out.print("Enter Student No: ");
                    String recordNo = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String recordName = scanner.nextLine();
                    System.out.print("Enter Service Type: ");
                    String recordService = scanner.nextLine();
                    System.out.print("Enter Estimated Service Time (min): ");
                    int recordTime = scanner.nextInt();
                    scanner.nextLine();

                    Student manualStudent = new Student(recordNo, recordName, recordService, recordTime);
                    recordsList.insertStudent(manualStudent);
                    stats.addTime(recordTime);
                    break;

                case 5:
                    recordsList.displayStudents();
                    break;

                case 6:
                    System.out.print("Enter Student No to search: ");
                    String searchNo = scanner.nextLine();
                    recordsList.searchStudent(searchNo);
                    break;

                case 7:
                    System.out.print("Enter Student No to remove: ");
                    String removeNo = scanner.nextLine();
                    recordsList.deleteStudent(removeNo);
                    break;

                case 8:
                    stats.displayStats();
                    break;

                case 9:
                    stats.runBubbleSort();
                    break;

                case 10:
                    stats.runSelectionSort();
                    break;

                case 11:
                    stats.runPerformanceExperiment();
                    break;

                case 12:
                    System.out.println("Exiting program.");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please select 1-12.");
            }
        }
    }
}
