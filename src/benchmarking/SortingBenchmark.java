package benchmarking;

import sortings.BubbleSort;
import sortings.HeapSort;
import sortings.InsertionSort;
import sortings.MergeSort;
import sortings.QuickSort;
import sortings.SelectionSort;
import sortings.Sorter;
import utils.ArrayGenerator;
import utils.ArrayUtils;

public class SortingBenchmark {
    private Sorter currentSorter;
    private Sorter[] allSorting = {new BubbleSort(), new InsertionSort(), new SelectionSort(),
                                   new MergeSort(), new QuickSort(), new HeapSort() };

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

        int[] array = ArrayGenerator.generateSortedArray(100, 1000);

        for (int i = 0; i < allSorting.length; i++) {
            this.currentSorter = allSorting[i];
            runBenchmark(array);
        }
    }      
}
