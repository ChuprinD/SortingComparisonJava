package visualization;

import sortings.SortingManager;
import utils.ArrayGenerator;

import javax.swing.JFrame;
import javax.swing.SwingWorker;
import javax.swing.plaf.FontUIResource;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Font;


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

        createDropList(menuWidth, menuHeight, frame, sortingManager);

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
    
    public void createDropList(int menuWidth, int menuHeight, JFrame frame, SortingManager sortingManager) {
        String[] options = ArrayGenerator.getOptions();
        JComboBox<String> comboBox = new JComboBox<>(options);

        String defaultGenerator = "Random Array";
        comboBox.setSelectedItem(defaultGenerator);
        sortingManager.setArrayGenerator(defaultGenerator);
        
        int comboBoxWidth = menuWidth / 2;
        int comboBoxHeight = menuHeight / 20;
        comboBox.setBounds(menuWidth / 2 - comboBoxWidth / 2, menuHeight / 6 - comboBoxHeight / 2, comboBoxWidth,
                comboBoxHeight);

        comboBox.setFont(new FontUIResource("Arial", Font.BOLD, 16));
        
        DefaultListCellRenderer listRenderer = new DefaultListCellRenderer();
        listRenderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        comboBox.setRenderer(listRenderer);

        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sortingManager.setArrayGenerator((String) comboBox.getSelectedItem());
            }
        });

        frame.add(comboBox);
    }
}
