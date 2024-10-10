package sortings;

import visualization.SortingVisualizer;

public interface Sorter {
    void sort(int[] array);

    public void UpdateVisualizer(SortingVisualizer visualizer);

    public String getName();

    public int getVisualizationDelay();
}
