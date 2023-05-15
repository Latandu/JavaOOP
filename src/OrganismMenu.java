import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class OrganismMenu {
    private JComboBox<String> organismDropdown;
    private JButton OK;
    private JFrame frame;

    public OrganismMenu(int row, int column, World world, GUILayout guiLayout, JButton button) {
        frame = new JFrame("Organism Dropdown");
        JPanel panel = new JPanel();

        // Define the organisms
        String[] organisms = {"Fox", "Belladonna", "Antelope", "Grass",
                "Guarana", "Sheep", "Sosnowsky", "SowThistle",
                "Turtle", "Wolf"};

        // Create the dropdown
        organismDropdown = new JComboBox<>(organisms);

        // Add the dropdown to the panel
        panel.add(organismDropdown);

        // Add the panel to the frame
        frame.getContentPane().add(panel);

        // Create OK button
        OK = new JButton("OK");
        OK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selectedOrganism = (String) organismDropdown.getSelectedItem();
                switch(Objects.requireNonNull(selectedOrganism)){
                    case "Fox" ->{
                        Organism fox = new Fox();
                        world.AddOrganism(fox, row, column);
                        guiLayout.setTextArea("Created new Fox at point " + row +", "+ column + "\n");
                        button.setText(String.valueOf(fox.getSymbol()));
                    }
                    case "Belladonna" -> {
                        Organism belladonna = new Belladonna();
                        world.AddOrganism(belladonna, row, column);
                        guiLayout.setTextArea("Created new Belladonna at point " + row +", "+ column + "\n");
                        button.setText(String.valueOf(belladonna.getSymbol()));
                    }
                    case "Antelope" -> {
                        Organism antelope = new Antelope();
                        world.AddOrganism(antelope, row, column);
                        guiLayout.setTextArea("Created new Antelope at point " + row +", "+ column + "\n");
                        button.setText(String.valueOf(antelope.getSymbol()));
                    }
                    case "Grass" -> {
                        Organism grass = new Grass();
                        world.AddOrganism(grass, row, column);
                        guiLayout.setTextArea("Created new Grass at point " + row +", "+ column + "\n");
                        button.setText(String.valueOf(grass.getSymbol()));
                    }
                    case "Guarana" ->{
                        Organism guarana = new Guarana();
                        world.AddOrganism(guarana, row, column);
                        guiLayout.setTextArea("Created new Guarana at point " + row +", "+ column + "\n");
                        button.setText(String.valueOf(guarana.getSymbol()));
                    }
                    case "Sheep" -> {
                        Organism sheep = new Sheep();
                        world.AddOrganism(sheep, row, column);
                        guiLayout.setTextArea("Created new Sheep at point " + row +", "+ column + "\n");
                        button.setText(String.valueOf(sheep.getSymbol()));
                    }
                    case "Sosnowsky" ->{
                        Organism sosnowsky = new Sosnowsky();
                        world.AddOrganism(sosnowsky, row, column);
                        guiLayout.setTextArea("Created new Soswnowsky at point " + row +", "+ column + "\n");
                        button.setText(String.valueOf(sosnowsky.getSymbol()));
                    }
                    case "SowThistle" ->{
                        Organism sowthistle = new SowThistle();
                        world.AddOrganism(sowthistle, row, column);
                        guiLayout.setTextArea("Created new Sow Thistle at point " + row + ", "+column + "\n");
                        button.setText(String.valueOf(sowthistle.getSymbol()));
                    }
                    case "Turtle" -> {
                        Organism turtle = new Turtle();
                        world.AddOrganism(turtle, row, column);
                        guiLayout.setTextArea("Created new Turtle at point " + row + ", "+ column + "\n");
                        button.setText(String.valueOf(turtle.getSymbol()));
                    }
                    case "Wolf" ->{
                        Organism wolf = new Wolf();
                        world.AddOrganism(wolf, row, column);
                        guiLayout.setTextArea("Created new Wolf at point " + row + ", "+ column + "\n");
                        button.setText(String.valueOf(wolf.getSymbol()));
                    }
                }
                frame.dispose();
            }
        });

        // Add the OK button to the panel
        panel.add(OK);

        // Set frame properties
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }
}