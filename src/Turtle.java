import java.util.Random;

public class Turtle extends Animal{
    public static final char turtleSymbol = 'T';
    Random rand = new Random();
    public Turtle(){
        this.setStrength(2);
        this.setInitiative(1);
        this.setSymbol(turtleSymbol);
        this.setAnimalName("Turtle");
    }
    @Override
    protected void Action(){
        int randNum = rand.nextInt(4);
        if(randNum < 3){
            return;
        }
        super.Action();
    }

    @Override
    protected boolean SpecialAttack(Organism organism) {
        if(this == organism) {
            return false;
        }
        Main.setLogs(getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() + " avoided attack" + "\n");
        return organism.getStrength() > 5;
    }
}
