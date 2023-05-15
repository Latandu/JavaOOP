import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartingWindow {
   private int rows, columns;

    public boolean isReadingFile() {
        return readingFile;
    }

    public void setReadingFile(boolean readingFile) {
        this.readingFile = readingFile;
    }

    boolean readingFile;

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public StartingWindow(){
        while(true){
        JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JTextField xField = new JTextField(10);
        JTextField yField = new JTextField(10);
        JButton loadFile = new JButton("Load from file");
        JLabel confirmation = new JLabel(" ");
        panel.add(new JLabel("Enter value x:"));
        panel.add(xField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(new JLabel("Enter value y:"));
        panel.add(yField);
        panel.add(Box.createVerticalStrut(15));
        panel.add(loadFile);
        panel.add(Box.createVerticalStrut(7));
        panel.add(confirmation);
        panel.add(Box.createVerticalStrut(7));
        loadFile.addActionListener(actionEvent -> {
            readingFile = true;
            confirmation.setText("File will be loaded. Click OK.");
        });
        int result = JOptionPane.showConfirmDialog(null, panel, "Input Values", JOptionPane.OK_CANCEL_OPTION);
        if(readingFile)
            break;
        if (result == JOptionPane.OK_OPTION) {
            try {
                int x = Integer.parseInt(xField.getText());
                int y = Integer.parseInt(yField.getText());
                this.rows = x;
                this.columns = y;
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter valid integer values.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(result == JOptionPane.CANCEL_OPTION){
            System.exit(2);
        }
    }
    }

}
