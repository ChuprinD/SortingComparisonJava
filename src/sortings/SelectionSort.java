package sortings;

import utils.ArrayUtils;
import visualization.SortingVisualizer;

public class SelectionSort implements Sorter {

    private static final String name = "SelectionSort";
    public static final int visualizationDelay = 10;
    private SortingVisualizer visualizer = null;

    public SelectionSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public SelectionSort() {}
    
    @Override
    public void sort(int[] array) {
        int arraySize = array.length;
        int indexMinElement;
        for (int i = 0; i < arraySize - 1; i++) {
            indexMinElement = i;

            for (int j = i + 1; j < arraySize; j++) {
                if (array[j] < array[indexMinElement]) {
                    indexMinElement = j;
                }
            }

            ArrayUtils.swap(array, indexMinElement, i, this.visualizer);
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
