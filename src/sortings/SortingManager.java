package sortings;
import java.util.HashMap;

import utils.ArrayGenerator;
import visualization.Menu;
import visualization.SortingVisualizer;

public class SortingManager {
    private HashMap<String, Sorter> sortMap = new HashMap<>(); 
    public static final Sorter[] allSorting = {new BubbleSort(), new InsertionSort(), new SelectionSort(),
            new MergeSort(), new QuickSort(), new HeapSort(), new ShellSort(), new RadixSort(), new TournamentSort()};
                                   
    private String currentArrayGenerator;

    public void setArrayGenerator(String currentArrayGenerator){
        this.currentArrayGenerator = currentArrayGenerator;
    }
        
    public SortingManager() {
        for (int i = 0; i < allSorting.length; i++) {
            sortMap.put(allSorting[i].getName(), allSorting[i]);
        }
    }

    public void runSortByName(String name, int delay, Menu menu) {
        int[] array = ArrayGenerator.generateArrayByName(this.currentArrayGenerator, 200, 1000);
        SortingVisualizer visualizer = new SortingVisualizer(array, delay, menu);
        Sorter currentSort = sortMap.get(name);
        currentSort.UpdateVisualizer(visualizer);
        new Thread(() -> currentSort.sort(array)).start();      
    }

    public HashMap<String, Sorter> getSortMap(){
        return sortMap;
    }
}
