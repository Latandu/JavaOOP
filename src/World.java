import javax.swing.*;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class World {
    Random rand = new Random();
    private final int rows, columns;
    private int numberOfRounds;
    private boolean AliveHuman = true;

    public boolean isAliveHuman() {
        return AliveHuman;
    }

    public void setAliveHuman(boolean aliveHuman) {
        AliveHuman = aliveHuman;
    }

    private ArrayList<ArrayList<Organism>> grid;
    private ArrayList<Organism> organismVector;
    public World(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.organismVector = new ArrayList<>();
        initializeGrid(rows,columns);
        JFrame frame = new JFrame("Dynamic Grid Layout Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(rows, columns);
        frame.setLayout(gridLayout);
        JButton[] buttons = new JButton[rows * columns];
        for (int i = 0; i < rows * columns; i++) {
            JButton button = new JButton("Button " + (i + 1));
            buttons[i] = button;
            frame.add(button);
        }
        frame.pack();
        frame.setVisible(true);
    }
    public void initializeGrid(int rows, int columns) {
        grid = new ArrayList<>(rows);

        for (int i = 0; i < rows; i++) {
            ArrayList<Organism> row = new ArrayList<>(columns);
            for (int j = 0; j < columns; j++) {
                row.add(null);
            }
            grid.add(row);
        }
    }
    private void drawWorld(){
        System.out.println("It is now turn: " + numberOfRounds);
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                if(grid.get(i).get(j) == null){
                    System.out.print('.');
                }
                else System.out.print(grid.get(i).get(j).getSymbol());
            }
            System.out.println();
        }
    }
    public char ReturnSymbol(int row, int column){
        if(row < 0 || column < 0 || row >= rows || column >= columns){
            return '"';
        }
        if(grid.get(row).get(column) == null){
            return '*';
        }
        return grid.get(row).get(column).getSymbol();
    }
    public int ReturnAge(int row, int column){
        if(row < 0 || column < 0 || row >= rows || column >= columns){
            return 0;
        }
        if(grid.get(row).get(column) == null){
            return 0;
        }
        return grid.get(row).get(column).getAge();
    }
    public Organism GetOrganism(int row, int column){
        if(row < 0 || column < 0 || row >= rows || column >= columns)
            return null;
        return grid.get(row).get(column);
    }
    public int GetStrength(int row, int column){
        if(row < 0 || column < 0 || row >= rows || column >= columns)
            return 0;
        if(grid.get(row).get(column) == null)
            return 0;
        return grid.get(row).get(column).getStrength();
    }
    private void FillBoardWithOrganisms(){
        /*Organism human = new Human();
        AddRandomlyCharacter(human);*/
        Organism fox = new Fox();
        Organism belladonna = new Belladonna();
        Organism Antelope = new Antelope();
        Organism grass = new Grass();
        Organism guarana = new Guarana();
        Organism sheep = new Sheep();
        Organism sosnowsky = new Sosnowsky();
        Organism sowthistle = new SowThistle();
        Organism turtle = new Turtle();
        Organism wolf = new Wolf();
        for(int i = 0; i < rows * columns / 25; i++){
            AddRandomlyCharacter(fox);
            AddRandomlyCharacter(belladonna);
            AddRandomlyCharacter(Antelope);
            AddRandomlyCharacter(grass);
            AddRandomlyCharacter(guarana);
            AddRandomlyCharacter(sheep);
            AddRandomlyCharacter(sosnowsky);
            AddRandomlyCharacter(sowthistle);
            AddRandomlyCharacter(turtle);
            AddRandomlyCharacter(wolf);
        }
    }
    private void AddRandomlyCharacter(Organism organism){
        int rowRandom = rand.nextInt(rows);
        if(rowRandom == rows) rowRandom--;
        int columnRandom = rand.nextInt(columns);
        if(columnRandom == columns) columnRandom--;
        if(grid.get(rowRandom).get(columnRandom) != null){
            AddRandomlyCharacter(organism);
        }
        if(grid.get(rowRandom).get(columnRandom) == null){
            organism.setPoint(rowRandom, columnRandom);
            organism.setWorld(this);
            grid.get(rowRandom).set(columnRandom, organism);
            organismVector.add(organism);
        }
    }
    public void DeleteOrganism(Organism organism, int row, int column){
        grid.get(row).set(column, null);
        organismVector.remove(organism);
    }
    public void AddOrganism(Organism organism, int row, int column){
        organism.setWorld(this);
        organism.setPoint(row, column);
        grid.get(row).set(column, organism);
        organismVector.add(organism);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private void makeTurn() {
        int organismVectorSize = organismVector.size();
        while (organismVectorSize >= 0) {
            int highestInitiative = 0;
            Organism currentOrganism = null;
            for (Organism organism : organismVector) {
                if (organism.getInitiative() >= highestInitiative && !organism.isRoundDone() && organism.getAge() > 0) {
                    highestInitiative = organism.getInitiative();
                    currentOrganism = organism;
                }
            }
            int highestAge = 0;
            for (Organism organism : organismVector) {
                if (organism != currentOrganism)
                    continue;
                if (highestAge > organism.getAge() && !organism.isRoundDone() && organism.getAge() > 0) {
                    highestAge = organism.getAge();
                    currentOrganism = null;
                    currentOrganism = organism;
                }
            }
            if (currentOrganism != null) {
                currentOrganism.Action();
                currentOrganism.setRoundDone(true);
            }
            organismVectorSize--;
        }
        for(Organism organism: organismVector){
            organism.setRoundDone(false);
            organism.setAge(organism.getAge() +1);
        }
        for (Organism i : organismVector) {
            i.setRoundDone(false);
            i.setAge(i.getAge() + 1);
            if(i.getSymbol() == '@'){
                if(i.getCoolDown() > 0){
                    i.setCoolDown(i.getCoolDown() - 1);
                }
                else if(i.getStrength() > 5){
                    i.setStrength(i.getStrength() - 1);
                    i.setCoolDown(5);

                }
            }
        }
        numberOfRounds++;
    }
    public void WholeGame(){
        FillBoardWithOrganisms();
        drawWorld();
        makeTurn();
        makeTurn();
        System.out.println();
        drawWorld();
        makeTurn();
        System.out.println();
        drawWorld();
        /*while(AliveHuman){

            if(!AliveHuman){
                return;
            }
        }*/
    }
}
