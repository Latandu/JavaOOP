import java.util.Random;
public class Animal extends Organism {
    private static final int error = 4;
    private static final int killedAnimal = 0;
    private static final int killedAnimalAttacker = 1;
    private static final int reproduciton = 2;
    private static final int noCollision = 3;
    Random rand = new Random();
    @Override
    protected void Action() {
        boolean[] isNotFilled = {false, false, false, false};
        if (CheckForFilling(isNotFilled, 1, '"', point) != 0) {
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
        int varX = point.getX() + row;
        int varY = point.getY() + column;
        if(varX < 0 || varY < 0 || varX > world.getRows() || varY > world.getColumns())
            return 1;
        pointDefender.setX(varX);
        pointDefender.setY(varY);
        int collisionReturn = Collision(pointDefender);
        if(collisionReturn == noCollision || collisionReturn== killedAnimal){
            point.setX(varX);
            point.setY(varY);
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
        if(worldSymbol == getSymbol() && getAgeDefender >= 3 && getSymbol() != '@'){
            boolean[] isNotFilled = {false,false,false,false};
            if(CheckForFilling(isNotFilled, 1, '*', point) != 0){
                boolean[] isNotFilled2 = {false, false, false, false};
                if(CheckForFilling(isNotFilled2, 1, '*', pointDefender) != 0) return error;
                while(true){
                    int randIndex2 = rand.nextInt(0, 4);
                    if(isNotFilled2[randIndex2]){
                        AddNewOrganism(randIndex2, pointDefender, CreateNewAnimal(getSymbol()));
                        return reproduciton;
                    }
                }
            }
            while(true){
                int randIndex2 = rand.nextInt(0,4);
                if(isNotFilled[randIndex2]){
                    AddNewOrganism(randIndex2, point, CreateNewAnimal(getSymbol()));
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
               if(this.getSymbol() == '@')
                   world.setAliveHuman(false);
                world.DeleteOrganism(this, point.getX(), point.getY());
                return killedAnimalAttacker;
            }
            if(newFightingOrganism.getStrength() <= getStrength()){
                if(newFightingOrganism.getSymbol() == '@')
                    world.setAliveHuman(false);
                world.DeleteOrganism(this, point.getX(), point.getY());
                world.DeleteOrganism(newFightingOrganism, pointDefender.getX(), pointDefender.getY());
                return killedAnimal;
            }
        }
        else{
            world.DeleteOrganism(this, point.getX(), point.getY());
            return noCollision;
        }
        return error;
    }
    public int CheckForFilling(boolean[] isNotFilled, int change, char check, Point pointF){
        int x = pointF.getX();
        int y = pointF.getY();
        if (check == '"') {
            if(world.ReturnSymbol(x - change, y) != check) isNotFilled[0] = true;
            if(world.ReturnSymbol(x + change, y) != check) isNotFilled[1] = true;
            if(world.ReturnSymbol(x, y - change) != check) isNotFilled[2] = true;
            if(world.ReturnSymbol(x, y + change) != check) isNotFilled[3] = true;
        }
        else if(check == '*'){
            if(world.ReturnSymbol(x - change, y) == check) isNotFilled[0] = true;
            if(world.ReturnSymbol(x + change, y) == check) isNotFilled[1] = true;
            if(world.ReturnSymbol(x, y - change) == check) isNotFilled[2] = true;
            if(world.ReturnSymbol(x, y + change) == check) isNotFilled[3] = true;
        }
        for(int i = 0; i < 4; i++){
            if(isNotFilled[i]) return 0;
        }
        return 1;
    }
    protected void AddNewOrganism(int randIndex, Point pointF, Organism organism){
        switch(randIndex){
            case 0->{
                pointF.setX(point.getX() - 1);
                pointF.setY(point.getY());
                world.AddOrganism(organism, pointF.getX(), pointF.getY());
            }
            case 1->{
                pointF.setX(point.getX() + 1);
                pointF.setY(point.getY());
                world.AddOrganism(organism, pointF.getX(), pointF.getY());
            }
            case 2->{
                pointF.setX(point.getX());
                pointF.setY(point.getY() - 1);
                world.AddOrganism(organism, pointF.getX(), pointF.getY());
            }
            case 3->{
                pointF.setX(point.getX());
                pointF.setY(point.getY() + 1);
                world.AddOrganism(organism, pointF.getX(), pointF.getY());
            }
        }
    }
}
