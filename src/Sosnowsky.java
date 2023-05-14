public class Sosnowsky extends Plant {
    public static final char sosnowskySymbol = 'S';
    public Sosnowsky() {
        this.setStrength(10);
        this.setInitiative(0);
        this.setAnimalName("Sosnowsky");
        this.setSymbol(sosnowskySymbol);
    }

    protected boolean SpecialAttack(Organism organism) {
        if(organism == this){
            return false;
        }
        else{
            if(organism.getSymbol() == '@'){
                world.setAliveHuman(false);
            }
            world.DeleteOrganism(organism, organism.getPoint().getX(), organism.getPoint().getY());
            return true;
        }
    }
    protected void Action() {
        Organism organism1 = world.GetOrganism(point.getX()-1, point.getY());
        Organism organism2 = world.GetOrganism(point.getX()+1, point.getY());
        Organism organism3 = world.GetOrganism(point.getX(), point.getY()+1);
        Organism organism4 = world.GetOrganism(point.getX(), point.getY()-1);

        if(organism1 != null && organism1.getInitiative() != 0){
           if(organism1.getSymbol() == '@')
               world.setAliveHuman(false);
            world.DeleteOrganism(organism1,point.getX()-1, point.getY());

        }
        if(organism2 != null &&  organism2.getInitiative() != 0){
            if(organism2.getSymbol() == '@')
                world.setAliveHuman(false);
            world.DeleteOrganism(organism2,point.getX()+1, point.getY());
        }


        if(organism3 != null &&  organism3.getInitiative() != 0){
            if(organism3.getSymbol() == '@')
                world.setAliveHuman(false);
            world.DeleteOrganism(organism3,point.getX(), point.getY()+1);

        }

        if(organism4 != null && organism4.getInitiative() != 0){
            if(organism4.getSymbol() == '@')
                world.setAliveHuman(false);
            world.DeleteOrganism(organism4,point.getX(), point.getY()-1);
        }
    }
}
