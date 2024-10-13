package sortings;

import utils.ArrayUtils;
import visualization.SortingVisualizer;

public class BubbleSort implements Sorter {

    private static final String name = "BubbleSort";
    private static final int visualizationDelay = 5;
    private SortingVisualizer visualizer = null;

    public BubbleSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public BubbleSort() {
    }

    @Override
    public void sort(int[] array) {
        int arraySize = array.length;
        boolean swaped = false;
        for (int i = 0; i < arraySize - 1; i++) {
            if (visualizer.isCancelled()) {
                return;
            }
            swaped = false;

            for (int j = 0; j < arraySize - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    ArrayUtils.swap(array, j, j + 1, this.visualizer);
                    swaped = true;
                }
            }

            if (swaped == false) {
                break;
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
