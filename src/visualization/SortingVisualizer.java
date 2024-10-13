package visualization;

import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SortingVisualizer extends JPanel{

    private int[] array;
    private int arraySize;
    private int highlightIndex1 = -1; 
    private int highlightIndex2 = -1;
    private int delay = 5;
    private Menu menu;
    private volatile boolean isSortingCancelled = false; 

    public SortingVisualizer(int[] array, int delay, Menu menu) {
        this.array = array;
        this.arraySize = array.length;
        this.menu = menu;
        this.delay = delay;
        createWindow();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        double width = getWidth();
        double height = getHeight();
        int maxElement = this.array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > maxElement) {
                maxElement = array[i];
            }
        }

        double barWidth = width / this.arraySize;

        for (int i = 0; i < this.arraySize; i++) {
            double barHeight = (double) array[i] / maxElement * (height - 50);

            if (i == highlightIndex1 || i == highlightIndex2) {
                g2d.setColor(Color.RED);
            } else {
                g2d.setColor(Color.WHITE);
            }
            Rectangle2D.Double rectangle = new Rectangle2D.Double((double) i * barWidth - 1, height - barHeight,
                    barWidth - 1, barHeight);
            
            g2d.fill(rectangle);
        }
    }
    
    public void createWindow() {
        JFrame frame = new JFrame("Sorting Visualizer");

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                isSortingCancelled = true;
                frame.dispose();
                menu.start();
            }
        });

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        
        frame.setSize(screenWidth, screenHeight);
        frame.add(this);
        frame.setVisible(true);
        setBackground(Color.BLACK);
    }

    public void highlightSwap(int i, int j) {
        highlightIndex1 = i;
        highlightIndex2 = j;
        repaint();
    }

    public void clearHighlight() {
        highlightIndex1 = -1;
        highlightIndex2 = -1;
        repaint();
    }

    public void setDelay(int delay) {
        this.delay = delay;
    }

    public void updateArray(int[] array) {
        this.array = array;
        repaint();
        try {
            Thread.sleep(this.delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean isCancelled() {
        return isSortingCancelled;
    }

}
