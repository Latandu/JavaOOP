import java.util.Random;

public class Plant extends Organism{
    Animal animalClass = new Animal();
    Random rand = new Random();
    @Override
    protected void Action(){
        boolean[] isNotFilled = {false,false,false,false};
        if(CheckForFilling(isNotFilled, 1, '*', point) != 0)
            return;
        int randomProbability = rand.nextInt(0,20);
        if(randomProbability == 0){
            while(true){
                int randIndex = rand.nextInt(0,4);
                if(isNotFilled[randIndex]){
                    AddNewOrganism(randIndex, point, CreateNewPlant(getSymbol()));
                    break;
                }
            }
        }
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
    private Organism CreateNewPlant(char symbol){
        switch(symbol){
            case 'B'->{
                return new Belladonna();
            }
            case 'S'->{
                return new Sosnowsky();
            }
            case 'G'->{
                return new Guarana();
            }
            case 'R'->{
                return new Grass();
            }
            case 'O'->{
                return new SowThistle();
            }
            default -> {}
        }
        return null;
    }

}
