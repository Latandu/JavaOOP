import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class World {
    protected GUILayout guiLayout;
   protected Random rand = new Random();
    private int rows;
    private int columns;
    private int numberOfRounds;

    public int getNumberOfRounds() {
        return numberOfRounds;
    }

    public void setNumberOfRounds(int numberOfRounds) {
        this.numberOfRounds = numberOfRounds;
    }

    public boolean isPowerUP() {
        return powerUP;
    }

    public void setPowerUP(boolean powerUP) {
        this.powerUP = powerUP;
    }

    private boolean powerUP = false;
    private boolean AliveHuman = true;
    private int setDirection = 0;

    public int getSetDirection() {
        return setDirection;
    }

    public void setSetDirection(int setDirection) {
        this.setDirection = setDirection;
    }

    public boolean isAliveHuman() {
        return AliveHuman;
    }

    public void setAliveHuman(boolean aliveHuman) {
        AliveHuman = aliveHuman;
    }

    private ArrayList<ArrayList<Organism>> grid;
    private ArrayList<Organism> organismVector;

    public ArrayList<ArrayList<Organism>> getGrid() {
        return grid;
    }

    public void setGrid(ArrayList<ArrayList<Organism>> grid) {
        this.grid = grid;
    }

    public World(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.organismVector = new ArrayList<>();
        initializeGrid(rows,columns);
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
    protected void FillBoardWithOrganisms(){
        Organism human = new Human();
        AddRandomlyCharacter(human);
        for(int i = 0; i < rows * columns * 0.15 / 10 + 1; i++){
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
            grid.get(organism.getPoint().getX()).set(organism.getPoint().getY(), organism);
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
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("data.txt"))) {
            writer.println(columns + " " + rows);
            writer.println(numberOfRounds + " " + AliveHuman);
            writer.println(powerUP);
            for (Organism organism : organismVector) {
                writer.println(organism.getAge() + " " + organism.getStrength() + " " + organism.getSymbol() + " " +
                        organism.getCoolDown() + " " + organism.getPoint().getX() + " " + organism.getPoint().getY() + " " +
                        organism.getAnimalName() + " " + organism.getInitiative() + " " + organism.isRoundDone());
            }
        } catch (IOException e) {
            System.out.println("Failed to write to file! Exiting...");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void readFromFile() {

        try (Scanner scanner = new Scanner(new File("data.txt"))) {
            rows = scanner.nextInt();
            columns = scanner.nextInt();
            numberOfRounds = scanner.nextInt();
            AliveHuman = scanner.nextBoolean();
            powerUP = scanner.nextBoolean();
            initializeGrid(rows, columns);
            while (scanner.hasNextInt()) {
                int age = scanner.nextInt();
                int strength = scanner.nextInt();
                char symbol = scanner.next().charAt(0);
                int coolDown = scanner.nextInt();
                int pointX = scanner.nextInt();
                int pointY = scanner.nextInt();
                String animalName = scanner.next();
                int initiative = scanner.nextInt();
                boolean roundDone = scanner.nextBoolean();
                initializeOrganism(age, strength, coolDown, initiative, pointX, pointY, symbol, roundDone, animalName);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Failed to retrieve from file! Exiting...");
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void initializeOrganism(int age, int strength, int coolDown, int initiative, int pointX, int pointY, char symbol,
                                   boolean roundDone, String animalName) {
        Organism organism = switch (symbol) {
            case 'W' -> new Wolf();
            case 'A' -> new Antelope();
            case 'E' -> new Sheep();
            case 'T' -> new Turtle();
            case 'F' -> new Fox();
            case 'B' -> new Belladonna();
            case 'S' -> new Sosnowsky();
            case 'G' -> new Guarana();
            case 'R' -> new Grass();
            case 'O' -> new SowThistle();
            case '@' -> new Human();
            default -> null;
        };
        if (organism == null) return;
        organism.setAge(age);
        organism.setStrength(strength);
        organism.setInitiative(initiative);
        organism.setSymbol(symbol);
        organism.setCoolDown(coolDown);
        organism.setPoint(pointX, pointY);
        organism.setRoundDone(roundDone);
        organism.setAnimalName(animalName);
        organism.setWorld(this);
        grid.get(pointX).set(pointY, organism);;
        organismVector.add(organism);
    }
    protected void makeTurn() {
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
    public void WholeGame(GUILayout guiLayoutMain){
        this.guiLayout = guiLayoutMain;
        guiLayout.drawWorld();
        while(isAliveHuman()){
            makeTurn();
            guiLayout.drawWorld();
            guiLayout.waitForButtonPress();
        }

        }
}
