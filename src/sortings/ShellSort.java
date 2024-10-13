package sortings;

import utils.ArrayUtils;
import visualization.SortingVisualizer;

public class ShellSort implements Sorter {

    private static final String name = "ShellSort";
    private static final int visualizationDelay = 10;
    private SortingVisualizer visualizer = null;

    public ShellSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public ShellSort() {}
    
    @Override
    public void sort(int[] array) {
        int arraySize = array.length;
        for (int gap = arraySize / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arraySize; i++) {
                for (int j = i; j >= gap && array[j - gap] > array[j]; j -= gap) {
                    if (visualizer.isCancelled()) {
                        return;
                    }
                    ArrayUtils.swap(array, j, j - gap, this.visualizer);
                }
            }
        }
        if (this.visualizer != null) {
            this.visualizer.clearHighlight();
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
