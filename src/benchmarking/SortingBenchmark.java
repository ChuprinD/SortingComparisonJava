package benchmarking;

import sortings.Sorter;
import sortings.SortingManager;
import utils.ArrayGenerator;
import utils.ArrayUtils;

public class SortingBenchmark {
    private Sorter currentSorter;

    public SortingBenchmark(Sorter sorter) {
        this.currentSorter = sorter;
    }

    public SortingBenchmark() {
        this.currentSorter = null;
    }

    public long runBenchmark(int[] array) {
        int[] arrayCopy = array.clone();

        long startTime = System.nanoTime();
        currentSorter.sort(arrayCopy);
        long endTime = System.nanoTime();

        long spentTime = endTime - startTime;
        //double timeInSeconds = spentTime / 1_000_000_000.0;

        System.out.println("\nSort name: " + this.currentSorter.getName() + /*"\nArray: " + java.util.Arrays.toString(array) + */"\nTime was spent sorting: " + spentTime +
                           "\nIs it sorted: " + ArrayUtils.isArraySorted(arrayCopy));

        return spentTime;
    }
    
    public void runGlobalBenchmark() {

        int[] array = ArrayGenerator.generateRandomArray(200, 1000);
        SortingManager sortingManager = new SortingManager();

        for (int i = 0; i < sortingManager.allSorting.length; i++) {
            this.currentSorter = sortingManager.allSorting[i];
            runBenchmark(array);
        }
    }      
}
