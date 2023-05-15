public class OrganismHandler {
    public int CheckForFilling(boolean[] isNotFilled, int change, char check, Point pointF, World world){
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
    protected void AddNewOrganism(int randIndex, Point pointF, Organism organism, World world){
        switch(randIndex){
            case 0->{
                pointF.setX(pointF.getX() - 1);
                pointF.setY(pointF.getY());
                world.AddOrganism(organism, pointF.getX(), pointF.getY());
            }
            case 1->{
                pointF.setX(pointF.getX() + 1);
                pointF.setY(pointF.getY());
                world.AddOrganism(organism, pointF.getX(), pointF.getY());
            }
            case 2->{
                pointF.setX(pointF.getX());
                pointF.setY(pointF.getY() - 1);
                world.AddOrganism(organism, pointF.getX(), pointF.getY());
            }
            case 3->{
                pointF.setX(pointF.getX());
                pointF.setY(pointF.getY() + 1);
                world.AddOrganism(organism, pointF.getX(), pointF.getY());
            }
        }
    }
}
