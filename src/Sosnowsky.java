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
        int[][] neighborCoordinates = {
                {point.getX() - 1, point.getY()},
                {point.getX() + 1, point.getY()},
                {point.getX(), point.getY() + 1},
                {point.getX(), point.getY() - 1}
        };

        for (int[] neighborCoordinate : neighborCoordinates) {
            int neighborX = neighborCoordinate[0];
            int neighborY = neighborCoordinate[1];
            Organism organism = world.GetOrganism(neighborX, neighborY);

            if (organism != null && organism.getInitiative() != 0) {
                if (organism.getSymbol() == '@') {
                    world.setAliveHuman(false);
                }
                world.DeleteOrganism(organism, neighborX, neighborY);
            }
        }
    }

    @Override
    protected int Collision(Point point) {
        return 0;
    }
}
