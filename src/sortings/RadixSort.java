package sortings;

import utils.ArrayUtils;
import visualization.SortingVisualizer;

public class RadixSort implements Sorter{

    private static final String name = "RadixSort";
    private static final int visualizationDelay = 10;
    private SortingVisualizer visualizer = null;

    public RadixSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public RadixSort() {}
    
    @Override
    public void sort(int[] array) {
        int maxElement = ArrayUtils.getMax(array);
        for (int exp = 1; maxElement / exp > 0; exp *= 10) {
            countingSortByDigit(array, exp, visualizer);
        }
        if (this.visualizer != null) {
            this.visualizer.clearHighlight();
        }
    }

    private void countingSortByDigit(int[] array, int exp, SortingVisualizer visualizer) {
        int arraySize = array.length;
        int[] output = new int[arraySize];
        int[] count = new int[10];
    
        for (int i = 0; i < arraySize; i++) {
            int digit = (array[i] / exp) % 10;
            count[digit]++;
        }
    
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
    
        for (int i = arraySize - 1; i >= 0; i--) {
            int digit = (array[i] / exp) % 10;
            output[count[digit] - 1] = array[i];
            count[digit]--;
        }
    
        for (int i = 0; i < arraySize; i++) {
            if (visualizer.isCancelled()) {
                return;
            }
            array[i] = output[i];
            if (this.visualizer != null) {
                visualizer.highlightSwap(i, -1);
                visualizer.updateArray(array);
            }     
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