class Student {
    String studentNo;
    String name;
    String serviceType;
    int estimatedTime;
    Student next;
 
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
