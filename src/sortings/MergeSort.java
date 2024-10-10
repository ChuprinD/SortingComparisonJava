package sortings;

import visualization.SortingVisualizer;

public class MergeSort implements Sorter {

    private static final String name = "MergeSort";
    public static final int visualizationDelay = 10;
    private SortingVisualizer visualizer = null;

    public MergeSort(SortingVisualizer visualizer) {
        this.visualizer = visualizer;
    }

    public MergeSort() {}
    
    
    @Override
    public void sort(int[] array) {
        mergeSort(array, 0, array.length - 1);
    }
    
    private void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right); 

            merge(array, left, mid, right);
        }
    }
    
    private void merge(int[] array, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        int[] leftArray = new int[leftSize];
        int[] rightArray = new int[rightSize];

        for (int i = 0; i < leftSize; i++) {
            leftArray[i] = array[left + i];
        }

        for (int j = 0; j < rightSize; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int indexLeftArray = 0;

        int indexRightArray = 0;
        int indexMergedArray = left;
        if (this.visualizer != null) {
            visualizer.highlightSwap(indexMergedArray, -1);
        }

        while (indexLeftArray < leftSize && indexRightArray < rightSize) {
            if (leftArray[indexLeftArray] <= rightArray[indexRightArray]) {
                array[indexMergedArray] = leftArray[indexLeftArray];
                indexLeftArray++;      
            } else {
                array[indexMergedArray] = rightArray[indexRightArray];
                indexRightArray++;
            }
            indexMergedArray++;
            if (this.visualizer != null) {
                visualizer.updateArray(array);
                visualizer.highlightSwap(indexMergedArray, -1);
            }
        }

        while (indexLeftArray < leftSize) {
            array[indexMergedArray] = leftArray[indexLeftArray];
            indexLeftArray++;
            indexMergedArray++;
            if (this.visualizer != null) {
                visualizer.highlightSwap(indexMergedArray, -1);
                visualizer.updateArray(array);
            }
        }

        while (indexRightArray < rightSize) {
            array[indexMergedArray] = rightArray[indexRightArray];
            indexRightArray++;
            indexMergedArray++;
            if (this.visualizer != null) {
                visualizer.highlightSwap(indexMergedArray, -1);
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
