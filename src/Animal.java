import java.util.Random;
public class Animal extends Organism {
    protected OrganismHandler organismHandler = new OrganismHandler();
    private static final int error = 4;
    private static final int killedAnimal = 0;
    private static final int killedAnimalAttacker = 1;
    private static final int reproduciton = 2;
    private static final int noCollision = 3;
   protected Random rand = new Random();
    @Override
    protected void Action() {
        boolean[] isNotFilled = {false, false, false, false};
        if (organismHandler.CheckForFilling(isNotFilled, 1, '"', point, world) != 0) {
            return;
        }
        int positionReturnCode = 0;
        while (true) {
            int randIndex = rand.nextInt(0, 4);
            if (isNotFilled[randIndex]) {
                switch (randIndex) {
                    case 0 -> {
                        positionReturnCode = PositionAndCollision(-1, 0);
                        if (positionReturnCode == 0) break;

                        world.AddOrganism(this, point.getX(), point.getY());
                    }
                    case 1 -> {
                        positionReturnCode = PositionAndCollision(1, 0);
                        if (positionReturnCode == 0) break;
                        world.AddOrganism(this, point.getX(), point.getY());
                    }
                    case 2 -> {
                        positionReturnCode = PositionAndCollision(0, -1);
                        if (positionReturnCode == 0) break;
                        world.AddOrganism(this, point.getX(), point.getY());
                    }
                    case 3 -> {
                        positionReturnCode = PositionAndCollision(0, 1);
                        if (positionReturnCode == 0) break;
                        world.AddOrganism(this, point.getX(), point.getY());
                    }
                    default -> {
                    }
                }
                break;
            }
        }
    }
    private Organism CreateNewAnimal(char symbol){
        switch(symbol){
            case 'F'->{
                return new Fox();
            }
            case 'A'->{
                return new Antelope();
            }
            case 'E'->{
                return new Sheep();
            }
            case 'T'->{
                return new Turtle();
            }
            case 'W'->{
                return new Wolf();
            }
            default -> {}
        }
        return null;
    }
    protected int PositionAndCollision(int row, int column){
        Point pointDefender = new Point();
        int varX = super.point.getX() + row;
        int varY = super.point.getY() + column;
        if(varX < 0 || varY < 0 || varX > world.getRows() || varY > world.getColumns())
            return 1;
        pointDefender.setX(varX);
        pointDefender.setY(varY);
        int collisionReturn = Collision(pointDefender);
        if(collisionReturn == noCollision || collisionReturn== killedAnimal){
            super.point.setX(varX);
            super.point.setY(varY);
            return 1;
        }
        else if(collisionReturn == killedAnimalAttacker || collisionReturn == reproduciton|| collisionReturn == error){
            return 0;
        }
        return 1;
    }
    @Override
    protected int Collision(Point pointDefender){
        char worldSymbol = world.ReturnSymbol(pointDefender.getX(), pointDefender.getY());
        int getAgeDefender = world.ReturnAge(pointDefender.getX(), pointDefender.getY());
        if(worldSymbol == getSymbol() && getAgeDefender >= 2 && getSymbol() != '@'){
            boolean[] isNotFilled = {false,false,false,false};
            if(organismHandler.CheckForFilling(isNotFilled, 1, '*', point, world) != 0){
                boolean[] isNotFilled2 = {false, false, false, false};
                if(organismHandler.CheckForFilling(isNotFilled2, 1, '*', pointDefender, world) != 0) return error;
                while(true){
                    int randIndex2 = rand.nextInt(0, 4);
                    if(isNotFilled2[randIndex2]){
                        organismHandler.AddNewOrganism(randIndex2, pointDefender, CreateNewAnimal(getSymbol()), world);
                        Main.setLogs("Reproduction of organisms: " + getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() +"\n");
                        return reproduciton;
                    }
                }
            }
            while(true){
                int randIndex2 = rand.nextInt(0,4);
                if(isNotFilled[randIndex2]){
                    organismHandler.AddNewOrganism(randIndex2, point, CreateNewAnimal(getSymbol()),world);
                    Main.setLogs("Reproduction of organisms: " + getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() +"\n");
                    return reproduciton;
                }
            }
        }
        else if(worldSymbol != getSymbol() && worldSymbol != '*' && worldSymbol != '"'){
            Organism newFightingOrganism = world.GetOrganism(pointDefender.getX(), pointDefender.getY());
            if(newFightingOrganism.SpecialAttack(this) || newFightingOrganism.SpecialAttack(newFightingOrganism)){
                return error;
            }
            if(newFightingOrganism.getStrength() > getStrength()){
               if(this.getSymbol() == '@'){
                   Main.setLogs("Human killed! Game Lost! \n");
                   world.setAliveHuman(false);}
                world.DeleteOrganism(this, point.getX(), point.getY());
                Main.setLogs("Killed organism: " + getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() +"\n");
                return killedAnimalAttacker;
            }
            if(newFightingOrganism.getStrength() <= getStrength()){
                if(newFightingOrganism.getSymbol() == '@'){
                    Main.setLogs("Human killed! Game Lost! \n");
                    world.setAliveHuman(false);}
                world.DeleteOrganism(this, point.getX(), point.getY());
                world.DeleteOrganism(newFightingOrganism, pointDefender.getX(), pointDefender.getY());
                Main.setLogs("Killed organism: " + newFightingOrganism.getAnimalName() + " at point " + newFightingOrganism.getPoint().getX() + ", "+ newFightingOrganism.getPoint().getY() +"\n");
                return killedAnimal;
            }
        }
        else{
            world.DeleteOrganism(this, point.getX(), point.getY());
            Main.setLogs("Moved organism: " + getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() +"\n");
            return noCollision;
        }
        return error;
    }
}
