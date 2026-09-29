import java.util.Arrays;
import java.util.Random;
 
public class AlgorithmExperiment {
 
    private static int selectionSortCount(int[] arr) {
        int comparisons = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[minIndex];
            arr[minIndex] = arr[i];
            arr[i] = temp;
        }
        return comparisons;
    }
 
    private static int insertionSortCount(int[] arr) {
        int comparisons = 0;
        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;
        }
        return comparisons;
    }
 
    private static int mergeComparisons; 
    private static void mergeSortCount(int[] arr, int left, int right) {
        if (left >= right) return;
        int mid = (left + right) / 2;
        mergeSortCount(arr, left, mid);
        mergeSortCount(arr, mid + 1, right);
        mergeCount(arr, left, mid, right);
    }
 
    private static void mergeCount(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);
        int i = 0, j = 0, k = left;
 
        while (i < leftArr.length && j < rightArr.length) {
            mergeComparisons++;
            if (leftArr[i] <= rightArr[j]) {
                arr[k] = leftArr[i];
                i++;
            } else {
                arr[k] = rightArr[j];
                j++;
            }
            k++;
        }
        while (i < leftArr.length) {
            arr[k] = leftArr[i];
            i++;
            k++;
        }
        while (j < rightArr.length) {
            arr[k] = rightArr[j];
            j++;
            k++;
        }
    }
 
    private static int quickComparisons; 
 
    private static void quickSortCount(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionCount(arr, low, high);
            quickSortCount(arr, low, pivotIndex - 1);
            quickSortCount(arr, pivotIndex + 1, high);
        }
    }
 
    private static int partitionCount(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            quickComparisons++;
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }
 
    private static int[] generateRandomArray(int size, Random random) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = random.nextInt(1000);
        }
        return arr;
    }
 
    private static int[] generateAlmostSorted(int size, Random random) {
        int[] arr = generateRandomArray(size, random);
        int[] sorted = arr.clone();
        selectionSortCount(sorted); 
 
        Random localRandom = new Random(42);
        for (int s = 0; s < 5; s++) {
            int idx = localRandom.nextInt(sorted.length - 1);
            int temp = sorted[idx];
            sorted[idx] = sorted[idx + 1];
            sorted[idx + 1] = temp;
        }
        return sorted;
    }
 
    private static void runTrial(String label, int size, int[] original) {
        int[] a1 = original.clone();
        long start1 = System.nanoTime();
        int comp1 = selectionSortCount(a1);
        long end1 = System.nanoTime();
        System.out.println(label + " | Selection Sort | Size: " + size
                + " | Comparisons: " + comp1 + " | Time: " + (end1 - start1) + " ns");
 
        int[] a2 = original.clone();
        long start2 = System.nanoTime();
        int comp2 = insertionSortCount(a2);
        long end2 = System.nanoTime();
        System.out.println(label + " | Insertion Sort | Size: " + size
                + " | Comparisons: " + comp2 + " | Time: " + (end2 - start2) + " ns");
 
        int[] a3 = original.clone();
        mergeComparisons = 0;
        long start3 = System.nanoTime();
        mergeSortCount(a3, 0, a3.length - 1);
        long end3 = System.nanoTime();
        System.out.println(label + " | Merge Sort     | Size: " + size
                + " | Comparisons: " + mergeComparisons + " | Time: " + (end3 - start3) + " ns");

        int[] a4 = original.clone();
        quickComparisons = 0;
        long start4 = System.nanoTime();
        quickSortCount(a4, 0, a4.length - 1);
        long end4 = System.nanoTime();
        System.out.println(label + " | Quick Sort     | Size: " + size
                + " | Comparisons: " + quickComparisons + " | Time: " + (end4 - start4) + " ns");
 
        System.out.println();
    }
 
    public static void main(String[] args) {
        Random random = new Random(123); 
 
        int[] sizes = {20, 50, 100, 500};
 
        System.out.println("===== RANDOM ARRAY EXPERIMENT =====\n");
        for (int size : sizes) {
            int[] original = generateRandomArray(size, random);
            runTrial("Random", size, original);
        }
 
        System.out.println("===== ALMOST-SORTED ARRAY EXPERIMENT (size 100) =====\n");
        int[] almostSorted = generateAlmostSorted(100, random);
        runTrial("Almost-Sorted", 100, almostSorted);
    }
}
 