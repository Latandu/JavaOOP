public class Guarana extends Plant{
    public static final char guaranaSymbol = 'G';
    public Guarana(){
        this.setSymbol(guaranaSymbol);
        this.setAnimalName("Guarana");
        this.setInitiative(0);
        this.setStrength(0);
    }

    @Override
    protected boolean SpecialAttack(Organism organism) {
        organism.setStrength(organism.getStrength() + 3);
        world.DeleteOrganism(this,point.getX(), point.getY());
        world.DeleteOrganism(organism, organism.getPoint().getX(), organism.getPoint().getY());
        world.AddOrganism(organism, point.getX(), point.getY());
        return true;
    }
}
