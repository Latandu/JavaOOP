import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;

public class GUILayout {
    private JPanel mainPanel;
    private int rows, columns;
    private JFrame frame;
    JLabel turnNumber;
    private CountDownLatch buttonLatch;
    private JButton[][] buttonsArray;
    private JButton nextTurn, saveFile;

    public String getTextArea() {
        return textArea.getText();
    }

    public void setTextArea(String string) {
        this.textArea.append(string);
    }

    private JTextArea textArea;
    private JScrollPane scrollPanel;
    private World world;

    public GUILayout(int rowsGUI, int columnsGUI, World worldGUI){
        this.rows = rowsGUI;
        this.columns = columnsGUI;
        this.world = worldGUI;
        frame = new JFrame("Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel savedFileLabel = new JLabel();

        JPanel gridPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(rows, columns);
        gridPanel.setLayout(gridLayout);
        this.buttonsArray = new JButton[rows][columns];
        for (int i = 0; i <rows; i++) {
            for(int j = 0; j < columns; j++){
                JButton button = new JButton("xx");
                button.setPreferredSize(new Dimension(50,50));
                buttonsArray[i][j] = button;
                gridPanel.add(button);
                int finalI = i;
                int finalJ = j;
                GUILayout gui = this;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if(button.getText().equals(".")){
                            OrganismMenu menu = new OrganismMenu(finalI, finalJ, world, gui, button);
                        }
                        frame.requestFocus();
                    }
                });

            }
        }
        buttonLatch = new CountDownLatch(1);
        // Create a JTextArea and put it inside a JScrollPane
        textArea = new JTextArea(rows + 20, columns + 20);
        textArea.setEditable(false);
        scrollPanel = new JScrollPane(textArea);
         nextTurn = new JButton("Next Turn");
         saveFile = new JButton("Save File");
         turnNumber = new JLabel("Round number: " + world.getNumberOfRounds());
        // Create a JPanel to hold the text container
        JPanel groupPanel = new JPanel();
        GroupLayout groupLayout = new GroupLayout(groupPanel);
        groupPanel.setLayout(groupLayout);
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addComponent(turnNumber)
                        .addComponent(nextTurn)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addComponent(saveFile)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED))
                        .addComponent(scrollPanel)
        );
        groupLayout.setVerticalGroup(
                groupLayout.createSequentialGroup()
                        .addComponent(turnNumber)
                        .addComponent(nextTurn)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(saveFile))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(scrollPanel)
        );
        mainPanel = new JPanel(new BorderLayout()){
            @Override
            public boolean isFocusable() {
                return true;
            }
        };;
        mainPanel.add(gridPanel, BorderLayout.WEST);
        mainPanel.add(groupPanel, BorderLayout.EAST);
        nextTurn.addActionListener(e -> {buttonLatch.countDown(); savedFileLabel.setText(" ");});
        saveFile.addActionListener(actionEvent -> {
            textArea.append("Filed saved at round: " + world.getNumberOfRounds() + "\n");
            world.saveToFile();
        });
        InputMap inputMap = mainPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);

        ActionMap actionMap = mainPanel.getActionMap();

        int upKey = KeyEvent.VK_UP;
        int downKey = KeyEvent.VK_DOWN;
        int leftKey = KeyEvent.VK_LEFT;
        int rightKey = KeyEvent.VK_RIGHT;
        int powerUp = KeyEvent.VK_P;
        inputMap.put(KeyStroke.getKeyStroke(upKey, 0), "moveUp");
        inputMap.put(KeyStroke.getKeyStroke(downKey, 0), "moveDown");
        inputMap.put(KeyStroke.getKeyStroke(leftKey, 0), "moveLeft");
        inputMap.put(KeyStroke.getKeyStroke(rightKey, 0), "moveRight");
        inputMap.put(KeyStroke.getKeyStroke(powerUp, 0), "powerUP");
        actionMap.put("moveUp", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setSetDirection(1);
            }
        });
        actionMap.put("moveDown", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setSetDirection(2);
            }
        });
        actionMap.put("moveLeft", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setSetDirection(3);
            }
        });
        actionMap.put("moveRight", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setSetDirection(4);
            }
        });
        actionMap.put("powerUP", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                world.setPowerUP(true);
            }
        });
        frame.getContentPane().add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }
    public void waitForButtonPress() {
        try {
            turnNumber.setText("Round number: " + world.getNumberOfRounds());
            buttonLatch.await();
            buttonLatch = new CountDownLatch(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void drawWorld(){
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                Organism organism = world.getGrid().get(i).get(j);
                if(organism == null){
                    buttonsArray[i][j].setText(".");
                }
                else{
                    buttonsArray[i][j].setText(String.valueOf(organism.getSymbol()));
                }
            }
        }
    }
}
