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
                Main.setLogs("Human killed! Game Lost! \n");
                world.setAliveHuman(false);
            }
            Main.setLogs(getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() + " killed by being eaten " + organism.getAnimalName() + "\n");
            world.DeleteOrganism(this, getPoint().getX(), getPoint().getY());
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
                    Main.setLogs("Human killed! Game Lost! \n");
                    world.setAliveHuman(false);
                }
                Main.setLogs(getAnimalName() + " at point " + getPoint().getX() + ", "+ getPoint().getY() + " killed " + organism.getAnimalName() + "\n");
                world.DeleteOrganism(organism, neighborX, neighborY);
            }
        }
    }

    @Override
    protected int Collision(Point point) {
        return 0;
    }
}
