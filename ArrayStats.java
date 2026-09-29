public class ArrayStats {
 
    private double[] serviceTimes;
    private int count;
 
    public ArrayStats(int capacity) {
        serviceTimes = new double[capacity];
        count = 0;
    }
 
    public void addServiceTime(double time) {
        if (count >= serviceTimes.length) {
            throw new RuntimeException("Array is full, cannot add more service times");
        }
        serviceTimes[count] = time;
        count++;
    }
 
    public int getTotalStudentsServed() {
        return count;
    }
 
    public double getTotalServiceTime() {
        double total = 0;
        for (int i = 0; i < count; i++) {
            total = total + serviceTimes[i];
        }
        return total;
    }
 
    public double getAverageServiceTime() {
        if (count == 0) {
            return 0;
        }
        return getTotalServiceTime() / count;
    }
 
    public double getHighestServiceTime() {
        if (count == 0) {
            throw new RuntimeException("No service times recorded");
        }
        double highest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] > highest) {
                highest = serviceTimes[i];
            }
        }
        return highest;
    }
 
    public double getLowestServiceTime() {
        if (count == 0) {
            throw new RuntimeException("No service times recorded");
        }
        double lowest = serviceTimes[0];
        for (int i = 1; i < count; i++) {
            if (serviceTimes[i] < lowest) {
                lowest = serviceTimes[i];
            }
        }
        return lowest;
    }
 
    public int getServicesLongerThanTenMinutes() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            if (serviceTimes[i] > 10) {
                total = total + 1;
            }
        }
        return total;
    }
 
    public int[] toIntArray() {
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = (int) serviceTimes[i];
        }
        return result;
    }
 
    public void displayStatistics() {
        System.out.println("Total students served: " + getTotalStudentsServed());
        System.out.println("Total service time: " + getTotalServiceTime());
        System.out.println("Average service time: " + getAverageServiceTime());
        System.out.println("Highest service time: " + getHighestServiceTime());
        System.out.println("Lowest service time: " + getLowestServiceTime());
        System.out.println("Services longer than 10 minutes: " + getServicesLongerThanTenMinutes());
    }
    
    public static void main(String[] args) {
        ArrayStats stats = new ArrayStats(10);
        stats.addServiceTime(12); // Maria - Registration
        stats.addServiceTime(5);  // Tomas - Student Card
        stats.addServiceTime(8);  // Ndapewa - Fees
        stats.addServiceTime(4);  // Simon - Documents
 
        stats.displayStatistics();
    }
}