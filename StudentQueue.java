class StudentQueue {
    private Student front;
    private Student rear;
 
    public StudentQueue() {
        front = rear = null;
    }
 
    public boolean isEmpty() {
        return front == null;
    }
 
    public void enqueue(Student student) {
        if (rear == null) {
            front = rear = student;
        } else {
            rear.next = student;
            rear = student;
        }
        System.out.println("Student added to queue: " + student);
    }
 
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
 
    public Student peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        return front;
    }
 
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
 