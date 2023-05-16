public class Guarana extends Plant{
    public static final char guaranaSymbol = 'G';
    public Guarana(){
        this.setSymbol(guaranaSymbol);
        this.setAnimalName("Guarana");
        this.setInitiative(0);
        this.setStrength(0);
    }

    @Override
    protected int Collision(Point point) {
        return 0;
    }

    @Override
    protected boolean SpecialAttack(Organism organism) {
        organism.setStrength(organism.getStrength() + 3);
        Main.setLogs(getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() + " got additional 3 strength " + "\n");
        int x = point.getX();
        int y = point.getY();
        world.DeleteOrganism(this, x, y);
        world.DeleteOrganism(organism, organism.getPoint().getX(), organism.getPoint().getY());
        world.AddOrganism(organism, x, y);
        return true;
    }
}
