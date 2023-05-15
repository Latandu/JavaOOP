import java.util.Random;

public class Antelope extends Animal {
    private static final char antelopeSymbol = 'A';
    public Antelope(){
        this.setStrength(4);
        this.setInitiative(4);
        this.setSymbol(antelopeSymbol);
        this.setAnimalName("Antelope");
    }
    Random rand = new Random();
    @Override
    protected void Action() {
        boolean[] isNotFilled = {false, false, false, false};
        if (super.organismHandler.CheckForFilling(isNotFilled, 2, '"', point, world) != 0) {
            return;
        }
        int positionReturnCode = 0;
        while (true) {
            int randIndex = rand.nextInt(0, 4);
            if (isNotFilled[randIndex]) {
                switch (randIndex) {
                    case 0 -> {
                        positionReturnCode = super.PositionAndCollision(-2, 0);
                        if (positionReturnCode == 0 || positionReturnCode == 4) break;
                        world.AddOrganism(this, point.getX(), point.getY());
                        break;
                    }
                    case 1 -> {
                        positionReturnCode = super.PositionAndCollision(2, 0);
                        if (positionReturnCode == 0 || positionReturnCode == 4) break;
                        world.AddOrganism(this, point.getX(), point.getY());
                    }
                    case 2 -> {
                        positionReturnCode = super.PositionAndCollision(0, -2);
                        if (positionReturnCode == 0 || positionReturnCode == 4) break;
                        world.AddOrganism(this, point.getX(), point.getY());
                    }
                    case 3 -> {
                        positionReturnCode = super.PositionAndCollision(0, 2);
                        if (positionReturnCode == 0 || positionReturnCode == 4) break;
                        world.AddOrganism(this, point.getX(), point.getY());
                    }
                    default -> {
                    }
                }
                break;
            }
        }
    }
    @Override
    protected boolean SpecialAttack(Organism organism) {

        int randomFightReturn = rand.nextInt(0,2);
        if(randomFightReturn == 1){
            boolean[] isNotFilled = {false, false,false ,false};
            if(super.organismHandler.CheckForFilling(isNotFilled, 2, '"', point, world) != 0) {
                return true;
            }
            Organism newOrganismPosition = world.GetOrganism(point.getX(), point.getY());
            while(true){
                int randIndex = rand.nextInt(0,4);
                if(isNotFilled[randIndex]){
                    if(randIndex == 0){
                        world.DeleteOrganism(this, point.getX(), point.getY());
                        world.AddOrganism(newOrganismPosition, point.getX()-1,point.getY());
                        return true;
                    } if(randIndex == 1){
                        world.DeleteOrganism(this, point.getX(), point.getY());
                        world.AddOrganism(newOrganismPosition, point.getX()+1,point.getY());
                        return true;
                    } if(randIndex == 2){
                        world.DeleteOrganism(this, point.getX(), point.getY());
                        world.AddOrganism(newOrganismPosition, point.getX(),point.getY()-1);
                        return true;
                    }
                    world.DeleteOrganism(this, point.getX(), point.getY());
                    world.AddOrganism(newOrganismPosition, point.getX(),point.getY()+1);
                    return true;
                }
            }
        }return false;
    }
}
