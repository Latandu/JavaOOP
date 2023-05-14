import java.util.Random;

public class Fox extends Animal{
    private static final char foxSymbol = 'F';
    Random rand = new Random();
    public Fox(){
        this.setStrength(3);
        this.setInitiative(7);
        this.setSymbol(foxSymbol);
        this.setAnimalName("Fox");
    }
    @Override
    protected void Action(){
        boolean[] isNotFilled = {false, false, false, false};
        if(super.CheckForFilling(isNotFilled, 1, '"', point) != 0){
            return;
        }
        while(true){
            int randIndex = rand.nextInt(4);
            if(isNotFilled[randIndex]){
                if(randIndex == 0){
                    if(world.GetStrength(point.getX()-1,point.getY()) > getStrength()) return;
                    if(super.PositionAndCollision(  -1, 0) == 0) break;
                    world.AddOrganism(this, point.getX(),point.getY());
                    break;
                } if(randIndex == 1){
                    if(world.GetStrength(point.getX()-1,point.getY()) > getStrength()) return;
                    if(super.PositionAndCollision( 1, 0) == 0) break;
                    world.AddOrganism(this, point.getX(),point.getY());
                    break;
                } if(randIndex == 2){
                    if(world.GetStrength(point.getX()-1,point.getY()) > getStrength()) return;
                    if(super.PositionAndCollision(   0, -1) == 0) break;
                    world.AddOrganism(this, point.getX(),point.getY());
                    break;
                } if(randIndex == 3){
                    if(world.GetStrength(point.getX()-1,point.getY()) > getStrength()) return;
                    if(super.PositionAndCollision(  0, 1) == 0) break;
                    world.AddOrganism(this, point.getX(),point.getY());
                    break;
                }
            }
        }

    }
}
