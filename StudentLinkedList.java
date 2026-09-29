class StudentLinkedList {
    private ServiceRecordNode head;
 
    public StudentLinkedList() {
        this.head = null;
    }
 
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
 
    public void insertAtBeginning(Student student) {
        ServiceRecordNode newNode = new ServiceRecordNode(student);
        newNode.next = head;
        head = newNode;
        System.out.println("Record inserted at beginning: " + student.name);
    }
 
    public void insertAtPosition(Student student, int position) {
        if (position < 1) {
            System.out.println("Invalid position. Position must be 1 or greater.");
            return;
        }
        if (position == 1) {
            insertAtBeginning(student);
            return;
        }
        ServiceRecordNode current = head;
        for (int i = 1; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) {
            System.out.println("Position out of range. The list is shorter than that.");
            return;
        }
        ServiceRecordNode newNode = new ServiceRecordNode(student);
        newNode.next = current.next;
        current.next = newNode;
        System.out.println("Record inserted at position " + position + ": " + student.name);
    }
 
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
 