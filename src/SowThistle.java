import java.util.Random;

public class SowThistle extends Plant{
    Random rand = new Random();
    public static final char sowThistleSymbol = 'O';
    public SowThistle(){
        this.setStrength(0);
        this.setSymbol(sowThistleSymbol);
        this.setAnimalName("SowThistle");
        this.setInitiative(0);
    }

    @Override
    protected void Action() {
        int randNum = rand.nextInt(10);
        if(randNum == 0){
            for(int i = 0; i < 3; i++){
              super.Action();
            }
        } else {
           super.Action();
        }
    }

    @Override
    protected int Collision(Point point) {
        return 0;
    }
}
