public class Belladonna extends Plant{
    private static final char belladonnaSymbol = 'B';
    public Belladonna(){
        this.setStrength(99);
        this.setInitiative(0);
        this.setAnimalName("Belladonna");
        this.setSymbol(belladonnaSymbol);
    }

    @Override
    protected int Collision(Point point) {
        return 0;
    }

    @Override
    protected boolean SpecialAttack(Organism organism) {
        if(organism == this){
            return false;
        }
        else{
            if(organism.getSymbol() == '@'){
                Main.setLogs("Human killed! Game Lost! \n");
                world.setAliveHuman(false);}

            world.DeleteOrganism(organism, organism.getPoint().getX(), organism.getPoint().getY());
            return true;
        }
    }
}
