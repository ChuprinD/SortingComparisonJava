package sortings;

import utils.ArrayUtils;
import visualization.SortingVisualizer;

public class BubbleSort implements Sorter {

    public static final String name = "BubbleSort";
    public static final int visualizationDelay = 2;
    private SortingVisualizer visualizer = null;

    public BubbleSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public BubbleSort() {}

    @Override
    public void sort(int[] array) {
        int arraySize = array.length;
        boolean swaped = false;
        for (int i = 0; i < arraySize - 1; i++) {
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
