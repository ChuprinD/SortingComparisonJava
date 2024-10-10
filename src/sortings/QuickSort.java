package sortings;

import utils.ArrayUtils;
import visualization.SortingVisualizer;

public class QuickSort implements Sorter {

    private static final String name = "QuickSort";
    public static final int visualizationDelay = 20;
    private SortingVisualizer visualizer = null;

    public QuickSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public QuickSort() {}
    
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }

    private void quickSort(int[] array, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(array, left, right);

            quickSort(array, left, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, right);
        }
    }

    private int partition(int[] array, int left, int right) {
        int pivot = array[right];

        int smallerElementIndex = left - 1;

        for (int i = left; i <= right - 1; i++) {
            if (array[i] < pivot) {
                smallerElementIndex++;
                ArrayUtils.swap(array, smallerElementIndex, i, visualizer);
            }
        }
                 
        ArrayUtils.swap(array, smallerElementIndex + 1, right, visualizer);
        return smallerElementIndex + 1;
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
