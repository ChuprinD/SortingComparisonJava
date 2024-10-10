package sortings;

import utils.ArrayUtils;
import visualization.SortingVisualizer;

public class InsertionSort implements Sorter {
    
    private static final String name = "InsertionSort";
    public static final int visualizationDelay = 10;
    private SortingVisualizer visualizer = null;

    public InsertionSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public InsertionSort() {}

    @Override
    public void sort(int[] array) {
        int arraySize = array.length;
        int currentElement;
        int j;

        for (int i = 1; i < arraySize; i++) {
            currentElement = array[i];
            j = i - 1;

            while (j >= 0 && array[j] > currentElement) {
                ArrayUtils.swap(array, j, j + 1, this.visualizer);
                j -= 1;
            }

            array[j + 1] = currentElement;
        }
    }

    @Override
    public void UpdateVisualizer(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getVisualizationDelay() {
        return visualizationDelay;
    }
}
