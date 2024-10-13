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
    private JFrame frame;
    private int menuWidth;
    private int menuHeight;
    private int screenWidth;
    private int screenHeight;

    public Menu() {
        this.frame = new JFrame("Menu");

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = (int) screenSize.getWidth();
        this.screenHeight = (int) screenSize.getHeight();

        this.menuWidth = screenWidth / 3;
        this.menuHeight = screenHeight / 2;
    }

    public void start() {
        this.frame.setSize(menuWidth, menuHeight);
        this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       
        SortingManager sortingManager = new SortingManager();

        createDropList(sortingManager);

        generateSortButtons(sortingManager);
        
        int frameX = (this.screenWidth - this.menuWidth) / 2;
        int frameY = (this.screenHeight - this.menuHeight) / 2;
        this.frame.setLocation(frameX, frameY);
        this.frame.getContentPane().setLayout(null);
        this.frame.getContentPane().setBackground(Color.BLACK);
        this.frame.setVisible(true);
    }
    
    public void generateSortButtons(SortingManager sortingManager) {
        int buttonWidth = this.menuWidth / 5;
        int buttonHeight = this.menuHeight / 9;

        sortButtons = new Button[sortingManager.getSortMap().size()];
        int curButtonX = buttonWidth / 2;
        int curButtonY = this.frame.getHeight() / 3;
        for (int i = 0; i < sortButtons.length; i++) {
            sortButtons[i] = new Button(SortingManager.allSorting[i].getName());
            sortButtons[i].setBounds(curButtonX, curButtonY, buttonWidth, buttonHeight);
            if ((i + 1) % 3 == 0) {
                curButtonX = buttonWidth / 2;
                curButtonY += buttonHeight + buttonHeight / 3;
            } else {
                curButtonX += buttonWidth + buttonWidth / 2;
            }

            final int index = i;

            sortButtons[i].addActionListener(e -> {
                this.frame.dispose();
                sortingManager.runSortByName(sortButtons[index].getLabel(), SortingManager.allSorting[index].getVisualizationDelay(), this);
            });

            this.frame.add(sortButtons[i]);
        }

    }
    
    public void createDropList(SortingManager sortingManager) {
        String[] options = ArrayGenerator.getOptions();
        JComboBox<String> comboBox = new JComboBox<>(options);
        
        String defaultGenerator = "Random Array";
        comboBox.setSelectedItem(defaultGenerator);
        sortingManager.setArrayGenerator(defaultGenerator);
        int comboBoxWidth = this.menuWidth / 2;
        int comboBoxHeight = this.menuHeight / 20;
        comboBox.setBounds(this.menuWidth / 2 - comboBoxWidth / 2, this.menuHeight / 6 - comboBoxHeight / 2, comboBoxWidth,
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

        this.frame.add(comboBox);
    }
}
