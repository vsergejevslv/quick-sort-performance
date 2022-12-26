package sorting;

import java.util.Arrays;
import java.util.Random;

public class QuickSortPerformance {

    public static void main(String[] args) {
        int[] sizes = {20, 50, 100, 10000, 100000, 1000000};
        int numTests = 10;

        //performance test
        QuickSortPerformance.test(sizes, numTests);

        //Sorting demo
        int[] arr = {17, 15, 15, 10, 7, 8, 9, 1, 5, 0};
        quickSort(arr, 0, arr.length - 1);

        // prints [0, 1, 5, 7, 8, 9, 10, 15, 15, 17]
        System.out.println("Sorting result: " + Arrays.toString(arr));
    }

    public static void test(int[] sizes, int numTests) {
        for (int size : sizes) {
            long totalTime = 0;
            for (int i = 0; i < numTests; i++) {
                int[] arr = generateTestData(size);
                long startTime = System.nanoTime();
                quickSort(arr, 0, arr.length - 1);
                long endTime = System.nanoTime();
                long elapsedTime = (endTime - startTime) / 1000;  // convert to microseconds
                totalTime += elapsedTime;
                System.out.println("Array size: " + size + " - Time: " + elapsedTime + " microseconds");
            }
            long averageTime = totalTime / numTests;
            System.out.println("Array size: " + size + " - Average time: " + averageTime + " microseconds");
        }
    }

    private static int[] generateTestData(int size) {
        Random rand = new Random();
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rand.nextInt();
        }
        return arr;
    }

    public static void quickSort(int[] arr, int low, int high) {
        // If the array has more than one element, we need to partition and sort
        if (low < high) {
            // Partition the array and get the pivot index
            int pivotIndex = partition(arr, low, high);

            // Recursively sort the elements before and after the pivot
            quickSort(arr, low, pivotIndex - 1);
            // To sort last elements
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    // Partitions the given array around the pivot (last element)
    // and returns the pivot index
    private static int partition(int[] arr, int low, int high) {
        // Choose the last element as the pivot
        int pivot = arr[high];

        // Index of the "smaller" elements
        int i = low - 1;

        // Iterate through the array and partition the elements
        for (int j = low; j < high; j++) {
            // If the current element is smaller than the pivot, swap it with the
            // element at index i+1 and increment i
            if (arr[j] < pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Swap the pivot with the element at index i+1
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        // Return the pivot index
        return i + 1;
    }
}