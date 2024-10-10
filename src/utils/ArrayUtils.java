package utils;

import visualization.SortingVisualizer;

public class ArrayUtils{
    public static void swap(int[] array, int index1, int index2) {
        int temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void swap(int[] array, int index1, int index2, SortingVisualizer visualizer) {
        if (visualizer == null) {
            swap(array, index1, index2);
        } else {
            visualizer.highlightSwap(index1, index2);
            swap(array, index1, index2);
            visualizer.updateArray(array);
            visualizer.clearHighlight();
        }
    }

    public static boolean checkArraySize(int[] array) {
        if (array.length == 0) {
            System.out.println("The array is empty and cannot be sorted");
            return false;
        }

        return true;
    }

    public static boolean isArraySorted(int[] array) {
        boolean result = true;
        int arraySize = array.length;
        for (int i = 0; i < arraySize - 1; i++) {
            if (array[i] > array[i + 1]) {
                result = false;
            }
        }

        return result;
    }

    public static int getMax(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }
} 