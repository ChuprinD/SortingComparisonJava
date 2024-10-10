package visualization;

import sortings.SortingManager;

import javax.swing.JFrame;
import javax.swing.SwingWorker;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;

public class Menu {
    
    private Button[] sortButtons; 

    public void startMenu() {
        JFrame frame = new JFrame("Menu");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();

        int menuWidth = screenWidth / 3;
        int menuHeight = screenHeight / 2;

        frame.setSize(menuWidth, menuHeight);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SortingManager sortingManager = new SortingManager();
        int buttonWidth = menuWidth / 5;
        int buttonHeight = menuHeight / 9;
        generateSortButtons(buttonWidth, buttonHeight, sortingManager, frame);
        
        int frameX = (screenWidth - menuWidth) / 2;
        int frameY = (screenHeight - menuHeight) / 2;
        frame.setLocation(frameX, frameY);
        frame.getContentPane().setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setVisible(true);
    }
    
    public void generateSortButtons(int buttonWidth, int buttonHeight, SortingManager sortingManager, JFrame frame) {
        sortButtons = new Button[sortingManager.getSortMap().size()];
        int curButtonX = buttonWidth / 2;
        int curButtonY = frame.getHeight() / 3;
        for (int i = 0; i < sortButtons.length; i++) {
            sortButtons[i] = new Button(sortingManager.allSorting[i].getName());
            sortButtons[i].setBounds(curButtonX, curButtonY, buttonWidth, buttonHeight);
            if ((i + 1) % 3 == 0) {
                curButtonX = buttonWidth / 2;
                curButtonY += buttonHeight + buttonHeight / 3;
            } else {
                curButtonX += buttonWidth + buttonWidth / 2;
            }
            

            final int index = i;

            sortButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    frame.dispose();
                    SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                        @Override
                        protected Void doInBackground() throws Exception {
                            
                            sortingManager.runSortByName(sortButtons[index].getLabel(), sortingManager.allSorting[index].getVisualizationDelay());
                            return null;
                        }
                    };
                    worker.execute();
                }
            });

            frame.add(sortButtons[i]);
        }

    }
}
