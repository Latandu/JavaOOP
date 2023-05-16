import java.util.Random;

public abstract class Plant extends Organism{
    protected OrganismHandler organismHandler = new OrganismHandler();
    protected Random rand = new Random();
    @Override
    protected void Action(){
        boolean[] isNotFilled = {false,false,false,false};
        if(organismHandler.CheckForFilling(isNotFilled, 1, '*', point, world) != 0)
            return;
        int randomProbability = rand.nextInt(0,20);
        if(randomProbability == 0){
            while(true){
                int randIndex = rand.nextInt(0,4);
                if(isNotFilled[randIndex]){
                    organismHandler.AddNewOrganism(randIndex, point, CreateNewPlant(getSymbol()), world);
                    Main.setLogs("Reproduction of organisms: " + getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() +"\n");
                    break;
                }
            }
        }
    }
    private Organism CreateNewPlant(char symbol){
        switch(symbol){
            case 'B'->{
                return new Belladonna() {
                };
            }
            case 'S'->{
                return new Sosnowsky();
            }
            case 'G'->{
                return new Guarana() {
                };
            }
            case 'R'->{
                return new Grass() {
                };
            }
            case 'O'->{
                return new SowThistle();
            }
            default -> {}
        }
        return null;
    }

}
