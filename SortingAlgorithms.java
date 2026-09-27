import java.util.Arrays;

public class SortingAlgorithms {

    public static void selectionSort(int[] arr) {
        int comparisons = 0;
        int swaps = 0;

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                comparisons++;
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                int temp = arr[minIndex];
                arr[minIndex] = arr[i];
                arr[i] = temp;
                swaps++;
            }

            if (i < 3) { // show state after 1st, 2nd, 3rd pass
                System.out.println("After pass " + (i + 1) + ": " + Arrays.toString(arr));
            }
        }
        System.out.println("Selection Sort -> Comparisons: " + comparisons + ", Swaps: " + swaps);
        System.out.println("Final sorted array: " + Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        int comparisons = 0;
        int shifts = 0;

        for (int i = 1; i < arr.length; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0) {
                comparisons++;
                if (arr[j] > key) {
                    arr[j + 1] = arr[j];
                    shifts++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = key;

            if (i <= 3) { // show state after first three passes
                System.out.println("After pass " + i + ": " + Arrays.toString(arr));
            }
        }
        System.out.println("Insertion Sort -> Comparisons: " + comparisons + ", Shifts: " + shifts);
        System.out.println("Final sorted array: " + Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right) {
            // BASE CASE: a sub-array of size 1 (or 0) is already sorted
            return;
        }
        int mid = (left + right) / 2;

        System.out.println("Dividing: " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));

        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        int[] leftArr = Arrays.copyOfRange(arr, left, mid + 1);
        int[] rightArr = Arrays.copyOfRange(arr, mid + 1, right + 1);

        int i = 0, j = 0, k = left;

        while (i < leftArr.length && j < rightArr.length) {
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

        System.out.println("Merging into: " + Arrays.toString(Arrays.copyOfRange(arr, left, right + 1)));
    }

    public static void quickSort(int[] arr, int low, int high, int[] stageCounter) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high, stageCounter);
            quickSort(arr, low, pivotIndex - 1, stageCounter);
            quickSort(arr, pivotIndex + 1, high, stageCounter);
        }
    }

    private static int partition(int[] arr, int low, int high, int[] stageCounter) {
        int pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
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

        if (stageCounter[0] < 2) {
            stageCounter[0]++;
            System.out.println("Partition stage " + stageCounter[0] + " -> Pivot: " + pivot);
            System.out.println("  Left partition:  " + Arrays.toString(Arrays.copyOfRange(arr, low, i + 1)));
            System.out.println("  Right partition: " + Arrays.toString(Arrays.copyOfRange(arr, i + 2, high + 1)));
        }
        return i + 1;
    }

    public static void main(String[] args) {
        int[] original = {17, 5, 23, 8, 14, 3, 11, 20, 6, 9};

        System.out.println("===== SELECTION SORT =====");
        int[] arr1 = original.clone();
        selectionSort(arr1);

        System.out.println("\n===== INSERTION SORT =====");
        int[] arr2 = original.clone();
        insertionSort(arr2);

        System.out.println("\n===== MERGE SORT =====");
        int[] arr3 = original.clone();
        mergeSort(arr3, 0, arr3.length - 1);
        System.out.println("Final sorted array: " + Arrays.toString(arr3));

        System.out.println("\n===== QUICK SORT =====");
        int[] arr4 = original.clone();
        int[] stageCounter = {0};
        quickSort(arr4, 0, arr4.length - 1, stageCounter);
        System.out.println("Final sorted array: " + Arrays.toString(arr4));
    }
}