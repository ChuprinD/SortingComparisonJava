package sortings;

import java.util.Arrays;

import visualization.SortingVisualizer;

public class TournamentSort implements Sorter{
    private static final String name = "TournamentSort";
    private static final int visualizationDelay = 10;
    private SortingVisualizer visualizer = null;

    public TournamentSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public TournamentSort() {}
    
    @Override
    public void sort(int[] array) {
        int arraySize = array.length;
        int[] tree = new int[2 * arraySize - 1];
        Arrays.fill(tree, Integer.MAX_VALUE);

        for (int i = 0; i < arraySize; i++) {
            tree[arraySize - 1 + i] = array[i];
        }

        for (int i = arraySize - 2; i >= 0; i--) {
            tree[i] = Math.min(tree[2 * i + 1], tree[2 * i + 2]);
        }

        for (int i = 0; i < arraySize; i++) {
            if (visualizer.isCancelled()) {
                return;
            }
            array[i] = tree[0];

            int index = arraySize - 1;
            while (tree[index] != array[i]) {
                index++;
            }
            tree[index] = Integer.MAX_VALUE;

            while (index > 0) {
                index = (index - 1) / 2;
                tree[index] = Math.min(tree[2 * index + 1], tree[2 * index + 2]);
            }

            if (this.visualizer != null) {
                visualizer.highlightSwap(i, -1);
                visualizer.updateArray(array);
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
