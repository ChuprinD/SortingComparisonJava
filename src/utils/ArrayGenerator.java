package utils;

import java.util.Random;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ArrayGenerator {
    private static final Map<String, Generator> Generators = new HashMap<>() {
        {
            put("Sorted Array", ArrayGenerator::generateSortedArray);
            put("Random Array", ArrayGenerator::generateRandomArray); 
            put("Reversed Sorted Array", ArrayGenerator::generateReversedSortedArray);
        }
    };

    public static int[] generateArrayByName(String option, int size, int maxValue) {
        Generator generator = Generators.get(option);
        return generator.generate(size, maxValue);
    }

    public static String[] getOptions() {
        return Generators.keySet().toArray(new String[0]);
    }
    
    @FunctionalInterface
    interface Generator {
        int[] generate(int size, int maxValue);
    }

    
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
    
    public static int[] generateSortedArray(int size, int maxValue) {
        int[] array = generateRandomArray(size, maxValue);

        Arrays.sort(array);

        return array;
    }
    
    public static int[] generateReversedSortedArray(int size, int maxValue){
        int[] array = generateSortedArray(size, maxValue);
        int[] reversedArray = new int[size];

        for (int i = 0; i < size; i++) {
            reversedArray[i] = array[size - i - 1];
        }
        
        return reversedArray;
    }
} 