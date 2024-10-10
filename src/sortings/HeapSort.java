package sortings;

import utils.ArrayUtils;
import visualization.SortingVisualizer;

public class HeapSort implements Sorter {

    private static final String name = "HeapSort";
    public static final int visualizationDelay = 10;
    private SortingVisualizer visualizer = null;

    public HeapSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public HeapSort() {}
    
    @Override
    public void sort(int[] array) {
        int arraySize = array.length;

        for (int i = arraySize / 2 - 1; i >= 0; i--) {
            heapify(array, arraySize, i);
        }

        for (int i = arraySize - 1; i > 0; i--) {
            ArrayUtils.swap(array, 0, i, visualizer);
            heapify(array, i, 0);
        }

    }
    
    public void heapify(int[] array, int length, int rootIndex) {
        int largest = rootIndex;
        int left = 2 * rootIndex + 1;
        int right = 2 * rootIndex + 2;

        if (left < length && array[left] > array[largest]) {
            largest = left;
        }

        if (right < length && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != rootIndex) {
            ArrayUtils.swap(array, rootIndex, largest, visualizer);
            heapify(array, length, largest);
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
