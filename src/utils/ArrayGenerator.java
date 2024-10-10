package utils;

import java.util.Random;
import java.util.Arrays;

public class ArrayGenerator{
    public static int[] generateRandomArray(int size, int maxValue) {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(maxValue);
            if (array[i] == 0) {
                array[i]++;
            }
        }

        return array;
    }
    
    public static int[] generateRandomSortedArray(int size, int maxValue) {
        int[] array = generateRandomArray(size, maxValue);

        Arrays.sort(array);

        return array;
    }
    
    public static int[] generateRandomReversedSortedArray(int size, int maxValue){
        int[] array = generateRandomSortedArray(size, maxValue);
        int[] reversedArray = new int[size];

        for (int i = 0; i < size; i++) {
            reversedArray[i] = array[size - i - 1];
        }
        
        return reversedArray;
    }
} 