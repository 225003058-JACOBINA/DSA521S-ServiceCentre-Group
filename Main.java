import java.util.Arrays;
import java.util.Scanner;
 
// Part D - Integrated Service-Centre System
// Queue (StudentQueue), Singly Linked List (StudentLinkedList), Array (ArrayStats)
// and Sorting (SortingAlgorithms / AlgorithmExperiment) all run from this one menu.
// The postfix Stack task (LinkedStack / PostfixEvaluator) is a separate exercise.
public class Main {
 
    // Reads a whole number safely so that typing letters does not crash the program
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a whole number.");
            }
        }
    }
 
    // Reads the four student fields and builds a Student
    private static Student readStudent(Scanner scanner) {
        System.out.print("Enter Student No: ");
        String studentNo = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Service Type: ");
        String serviceType = scanner.nextLine();
        int estTime = readInt(scanner, "Enter Estimated Service Time (min): ");
        return new Student(studentNo, name, serviceType, estTime);
    }
 
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentQueue queue = new StudentQueue();               // Part A1
        StudentLinkedList recordsList = new StudentLinkedList(); // Part A2
        ArrayStats stats = new ArrayStats(100);                // Part A4
 
        while (true) {
            System.out.println("\n========================================");
            System.out.println("         CAMPUS SERVICE CENTRE");
            System.out.println("========================================");
            System.out.println("1. Add student to waiting queue");
            System.out.println("2. Serve next student (remove from queue)");
            System.out.println("3. Display waiting students");
            System.out.println("4. Add student service record (Linked List - insertStudent())");
            System.out.println("5. Display student service records");
            System.out.println("6. Search for student record");
            System.out.println("7. Remove student record");
            System.out.println("8. Display daily statistics");
            System.out.println("9. Sort service times");
            System.out.println("10. Run sorting experiment");
            System.out.println("11. Exit");
            int option = readInt(scanner, "Select option: ");
 
            switch (option) {
                case 1: // Queue - enqueue
                    queue.enqueue(readStudent(scanner));
                    break;
 
                case 2: // Queue - dequeue; served student is recorded in the list and the array
                    Student served = queue.dequeue();
                    if (served != null) {
                        recordsList.insertStudent(served);
                        stats.addServiceTime(served.estimatedTime);
                    }
                    break;
 
                case 3: // Queue - traversal/display
                    queue.displayQueue();
                    break;
 
                case 4: // Linked List - insertion (end, beginning or specified position)
                    System.out.println("\n--- Add Service Record ---");
                    System.out.println("a) Insert at the end");
                    System.out.println("b) Insert at the beginning");
                    System.out.println("c) Insert at a specified position");
                    System.out.print("Choose a, b or c: ");
                    String where = scanner.nextLine().trim().toLowerCase();
                    if (where.equals("a")) {
                        recordsList.insertStudent(readStudent(scanner));
                    } else if (where.equals("b")) {
                        recordsList.insertAtBeginning(readStudent(scanner));
                    } else if (where.equals("c")) {
                        int position = readInt(scanner, "Enter position (1 = first): ");
                        recordsList.insertAtPosition(readStudent(scanner), position);
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
 
                case 5: // Linked List - traversal/display
                    recordsList.displayStudents();
                    break;
 
                case 6: // Linked List - search
                    System.out.print("Enter Student No to search: ");
                    recordsList.searchStudent(scanner.nextLine());
                    break;
 
                case 7: // Linked List - deletion
                    System.out.print("Enter Student No to remove: ");
                    recordsList.deleteStudent(scanner.nextLine());
                    break;
 
                case 8: // Array processing
                    if (stats.getTotalStudentsServed() == 0) {
                        System.out.println("No students have been served yet, so there are no statistics.");
                    } else {
                        System.out.println("\n--- DAILY STATISTICS ---");
                        stats.displayStatistics();
                    }
                    break;
 
                case 9: // Sorting algorithms
                    int[] times = stats.toIntArray();
                    if (times.length == 0) {
                        System.out.println("No service times recorded yet. Serve some students first.");
                        break;
                    }
                    System.out.println("\n1. Selection Sort");
                    System.out.println("2. Insertion Sort");
                    System.out.println("3. Merge Sort");
                    System.out.println("4. Quick Sort");
                    int choice = readInt(scanner, "Choose a sorting algorithm: ");
                    System.out.println("Service times before sorting: " + Arrays.toString(times));
                    if (choice == 1) {
                        SortingAlgorithms.selectionSort(times);
                    } else if (choice == 2) {
                        SortingAlgorithms.insertionSort(times);
                    } else if (choice == 3) {
                        SortingAlgorithms.mergeSort(times, 0, times.length - 1);
                        System.out.println("Final sorted array: " + Arrays.toString(times));
                    } else if (choice == 4) {
                        SortingAlgorithms.quickSort(times, 0, times.length - 1, new int[]{0});
                        System.out.println("Final sorted array: " + Arrays.toString(times));
                    } else {
                        System.out.println("Invalid choice.");
                    }
                    break;
 
                case 10: // Sorting experiment
                    AlgorithmExperiment.main(new String[0]);
                    break;
 
                case 11:
                    System.out.println("Exiting program.");
                    scanner.close();
                    return;
 
                default:
                    System.out.println("Invalid option. Please select 1-11.");
            }
        }
    }
}
 